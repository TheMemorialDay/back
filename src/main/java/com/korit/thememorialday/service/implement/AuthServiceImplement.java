package com.korit.thememorialday.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.korit.thememorialday.common.util.AuthNumberCreator;
import com.korit.thememorialday.dto.request.auth.IdCheckRequestDto;
import com.korit.thememorialday.dto.request.auth.IdSearchTelNumberAuthRequestDto;
import com.korit.thememorialday.dto.request.auth.IdSearchNameTelNumberRequestDto;
import com.korit.thememorialday.dto.request.auth.PasswordAuthRequestDto;
import com.korit.thememorialday.dto.request.auth.PasswordResettingFinalRequestDto;
import com.korit.thememorialday.dto.request.auth.PasswordResettingRequestDto;
import com.korit.thememorialday.dto.request.auth.PasswordResettingIdAndTelNumberRequestDto;
import com.korit.thememorialday.dto.request.auth.SignInRequestDto;
import com.korit.thememorialday.dto.request.auth.SignUpRequestDto;
import com.korit.thememorialday.dto.request.auth.TelAuthCheckRequestDto;
import com.korit.thememorialday.dto.request.auth.TelAuthRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;

import com.korit.thememorialday.dto.response.auth.GetSignInResponseDto;
import com.korit.thememorialday.dto.response.auth.IdSearchResponseDto;
import com.korit.thememorialday.dto.response.auth.SignInResponseDto;
import com.korit.thememorialday.entity.StoreEntity;
import com.korit.thememorialday.entity.TelAuthEntity;
import com.korit.thememorialday.entity.UserEntity;
import com.korit.thememorialday.provider.JwtProvider;
import com.korit.thememorialday.provider.SmsProvider;
import com.korit.thememorialday.repository.StoreRepository;
import com.korit.thememorialday.repository.TelAuthRepository;
import com.korit.thememorialday.repository.UserRepository;
import com.korit.thememorialday.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {
	private final JwtProvider jwtProvider;
	private final SmsProvider smsProvider;

	private final UserRepository userRepository;
	private final TelAuthRepository telAuthRepository;
	private final StoreRepository storeRepository;

	// 암호화 방식 주입
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	// * 아이디 중복 확인
	@Override
	public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {
		String userId = dto.getUserId();

		try {
			boolean isExistedId = userRepository.existsByUserId(userId);
			if (isExistedId)
				return ResponseDto.duplicatedUserId();
		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return ResponseDto.success();
	}

	// * 전화번호 확인
	@Override
	public ResponseEntity<ResponseDto> telAuth(TelAuthRequestDto dto) {
		String telNumber = dto.getTelNumber();

		try {
			boolean isExistedTelNumber = userRepository.existsByTelNumber(telNumber);
			if (isExistedTelNumber) return ResponseDto.duplicatedaTelNumber();

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		// 인증번호 생성 메서드 만든 후 작성
		String telAuthNumber = AuthNumberCreator.number4();

		// smsProvider 만든 후 작성
		boolean isSendSuccess = smsProvider.sendMessage(telNumber, telAuthNumber);
		if (!isSendSuccess)
			return ResponseDto.messageSendFail();

		try {
			// 전화번호인증 엔터티 & 리포지토리 생성 먼저
			TelAuthEntity telAuthNumberEntity = new TelAuthEntity(telNumber, telAuthNumber);
			telAuthRepository.save(telAuthNumberEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return ResponseDto.success();

	}

	// * 전화번호 + 인증번호 확인
	@Override
	public ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto dto) {
		String telNumber = dto.getTelNumber();
		String telAuthNumber = dto.getTelAuthNumber();

		try {
			boolean isMatched = telAuthRepository.existsByTelNumberAndTelAuthNumber(telNumber, telAuthNumber);
			if (!isMatched) return ResponseDto.telAuthFail();
		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return ResponseDto.success();
	}

	// * 회원가입
	@Override
	public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
		String userId = dto.getUserId();
		String telNumber = dto.getTelNumber();
		String telAuthNumber = dto.getTelAuthNumber();
		String password = dto.getPassword();

		try {
			boolean isExistedId = userRepository.existsByUserId(userId);
			if (isExistedId)
				return ResponseDto.duplicatedUserId();

			boolean isExistedTelNumber = userRepository.existsByTelNumber(telNumber);
			if (isExistedTelNumber)
				return ResponseDto.duplicatedaTelNumber();

			boolean isMatched = telAuthRepository.existsByTelNumberAndTelAuthNumber(telNumber, telAuthNumber);
			if (!isMatched)
				return ResponseDto.telAuthFail();

			// 비밀번호 암호화
			String encodedPassword = passwordEncoder.encode(password);
			dto.setPassword(encodedPassword);

			UserEntity userEntity = new UserEntity(dto);
			userRepository.save(userEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return ResponseDto.success();
	}

	// * 로그인
	@Override
	public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
		String userId = dto.getUserId();
		String password = dto.getPassword();

		String accessToken = null;

		try {
			UserEntity userEntity = userRepository.findByUserId(userId);
			if (userEntity == null)
				return ResponseDto.signInFail();

			String encodedPassword = userEntity.getPassword();
			boolean isMatched = passwordEncoder.matches(password, encodedPassword);
			if (!isMatched)
				return ResponseDto.signInFail();

			// 확인 성공하면 토큰 생성
			accessToken = jwtProvider.create(userId);
			if (accessToken == null)
				return ResponseDto.tokenCreateFail();

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return SignInResponseDto.success(accessToken);
	}

	// * 첫단계 아이디 찾기 (이름 + 전화번호)
	@Override
	public ResponseEntity<ResponseDto> idSearchNameTelCheck(IdSearchNameTelNumberRequestDto dto) {
		String name = dto.getName();
		String telNumber = dto.getTelNumber();

		try {

			UserEntity userEntity = userRepository.findByNameAndTelNumber(name, telNumber);
			if (userEntity == null) return ResponseDto.noExistInfo();

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		// 이름 + 전화번호 일치하면 인증번호 전송
		String telAuthNumber = AuthNumberCreator.number4();

		boolean isSendSuccess = smsProvider.sendMessage(telNumber, telAuthNumber);
		if (!isSendSuccess)
			return ResponseDto.messageSendFail();

		try {
			// 전화번호인증 엔터티 & 리포지토리 생성 먼저
			TelAuthEntity telAuthNumberEntity = new TelAuthEntity(telNumber, telAuthNumber);
			telAuthRepository.save(telAuthNumberEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return ResponseDto.success();
	}

	//* 아이디 찾기 (전화번호 + 인증번호)
	@Override
	public ResponseEntity<ResponseDto> idSearchTelAuthCheck(IdSearchTelNumberAuthRequestDto dto) {
		String telNumber = dto.getTelNumber();
		String telAuthNumber = dto.getTelAuthNumber();

		try {
			boolean isMatched = telAuthRepository.existsByTelNumberAndTelAuthNumber(telNumber, telAuthNumber);
			if (!isMatched) return ResponseDto.telAuthFail();
		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return ResponseDto.success();
	}

	// * 아이디 찾기 (정보 결과 나오는)
	@Override
	public ResponseEntity<? super IdSearchResponseDto> getIdSearch(IdSearchNameTelNumberRequestDto dto) {
		String name = dto.getName();
		String telNumber = dto.getTelNumber();

		UserEntity userEntity = null;

		try {
			userEntity = userRepository.findByNameAndTelNumber(name, telNumber);
			if (userEntity == null) return ResponseDto.noExistInfo();

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return IdSearchResponseDto.success(userEntity);
	}

	// * 비밀번호 찾기 (아이디 & 전화번호로 존재 확인)
	@Override
	public ResponseEntity<ResponseDto> passwordResettingIdTelCheck(PasswordResettingIdAndTelNumberRequestDto dto) {
		String userId = dto.getUserId();
		String telNumber = dto.getTelNumber();

		try {
			UserEntity IdAndTelNumber = userRepository.findByUserIdAndTelNumber(userId, telNumber);
			if (IdAndTelNumber == null) return ResponseDto.noExistInfo();

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		// 아이디 + 전화번호 일치하면 인증번호 전송
		String telAuthNumber = AuthNumberCreator.number4();

		boolean isSendSuccess = smsProvider.sendMessage(telNumber, telAuthNumber);
		if (!isSendSuccess)
			return ResponseDto.messageSendFail();

		try {
			// 전화번호인증 엔터티 & 리포지토리 생성 먼저
			TelAuthEntity telAuthNumberEntity = new TelAuthEntity(telNumber, telAuthNumber);
			telAuthRepository.save(telAuthNumberEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return ResponseDto.success();
	}

	// * 비밀번호를 위한 인증 확인 (전화번호 + 인증번호)
	@Override
	public ResponseEntity<ResponseDto> passwordResettingAuthCheck(PasswordAuthRequestDto dto) {
		String telNumber = dto.getTelNumber();
		String telAuthNumber = dto.getTelAuthNumber();

		try {
			boolean isMatched = telAuthRepository.existsByTelNumberAndTelAuthNumber(telNumber, telAuthNumber);
			if (!isMatched)
				return ResponseDto.telAuthFail();

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return ResponseDto.success();
	}

	//* 비밀번호 재설정 하기 전 최종 통합 확인
	@Override
	public ResponseEntity<ResponseDto> passwordResettingFinalCheck(
		PasswordResettingFinalRequestDto dto) {
		
		String userId = dto.getUserId();
		String telNumber = dto.getTelNumber();
		String telAuthNumber = dto.getTelAuthNumber();

		try {

			UserEntity IdAndTelNumber = userRepository.findByUserIdAndTelNumber(userId, telNumber);
			if (IdAndTelNumber == null) return ResponseDto.noExistInfo();

			boolean isMatched = telAuthRepository.existsByTelNumberAndTelAuthNumber(telNumber, telAuthNumber);
			if (!isMatched) return ResponseDto.telAuthFail();

		} catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return ResponseDto.success();
	}

	// * 비밀번호 재설정
	@Override
	public ResponseEntity<ResponseDto> passwordResetting(PasswordResettingRequestDto dto) {

		try {

			String userId = dto.getUserId();
			String telNumber = dto.getTelNumber();
			String telAuthNumber = dto.getTelAuthNumber();
			String password = dto.getPassword();

			boolean isAuth = telAuthRepository.existsByTelNumberAndTelAuthNumber(telNumber, telAuthNumber);
			if (!isAuth) return ResponseDto.telAuthFail();

			UserEntity userEntity = userRepository.findByUserId(userId);
			if (userEntity == null) return ResponseDto.noExistUserId();

			// 기존 패스워드와 새 패스워드 비교
			String prePassword = userEntity.getPassword();
			boolean isEquals = passwordEncoder.matches(password, prePassword);
			if (isEquals) return ResponseDto.validationFail();

			String encodedPassword = passwordEncoder.encode(password);
			userEntity.setPassword(encodedPassword);

			userRepository.save(userEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return ResponseDto.success();

	}

	@Override
	public ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId) {
		UserEntity userEntity = null;
		Integer storeNumber = null;

		try {
			userEntity = userRepository.findByUserId(userId);
			if (userEntity == null)
				return ResponseDto.noExistUserId();

			boolean isBoss = userEntity.getPermission().equals("사장");
			if (isBoss) {
				StoreEntity storeEntity = storeRepository.findByUserId(userId);
				storeNumber = storeEntity.getStoreNumber();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}
		return GetSignInResponseDto.success(userEntity, storeNumber);
	}

}
