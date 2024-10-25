package com.korit.thememorialday.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.korit.thememorialday.common.util.AuthNumberCreator;
import com.korit.thememorialday.dto.request.auth.IdCheckRequestDto;
import com.korit.thememorialday.dto.request.auth.IdSearchAuthRequestDto;
import com.korit.thememorialday.dto.request.auth.IdSearchRequestDto;
import com.korit.thememorialday.dto.request.auth.SignInRequestDto;
import com.korit.thememorialday.dto.request.auth.SignUpRequestDto;
import com.korit.thememorialday.dto.request.auth.TelAuthCheckRequestDto;
import com.korit.thememorialday.dto.request.auth.TelAuthRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.auth.IdSearchResponseDto;
import com.korit.thememorialday.dto.response.auth.SignInResponseDto;
import com.korit.thememorialday.entity.TelAuthEntity;
import com.korit.thememorialday.entity.UserEntity;
import com.korit.thememorialday.provider.JwtProvider;
import com.korit.thememorialday.provider.SmsProvider;
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

	// 암호화 방식 주입
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	//* 아이디 중복 확인
	@Override
	public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {
		String userId = dto.getUserId();

		try {
			boolean isExistedId = userRepository.existsByUserId(userId);
			if (isExistedId) return ResponseDto.duplicatedUserId();
		} catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return ResponseDto.success();
	}

	//* 전화번호 확인
	@Override
	public ResponseEntity<ResponseDto> telAuth(TelAuthRequestDto dto) {
		String telNumber = dto.getTelNumber();

		try {
			boolean isExistedTelNumber = userRepository.existsByTelNumber(telNumber);
			if (isExistedTelNumber) return ResponseDto.duplicatedaTelNumber();
		} catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		// 인증번호 생성 메서드 만든 후 작성
		String telAuthNumber = AuthNumberCreator.number4();

		// smsProvider 만든 후 작성
		boolean isSendSuccess = smsProvider.sendMessage(telNumber, telAuthNumber);
		if (!isSendSuccess) return ResponseDto.messageSendFail();

		try {
			// 전화번호인증 엔터티 & 리포지토리 생성 먼저
			TelAuthEntity telAuthNumberEntity = new TelAuthEntity(telNumber, telAuthNumber);
			telAuthRepository.save(telAuthNumberEntity);
			
		} catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return ResponseDto.success();
	

	}

	//* 전화번호 + 인증번호 확인
	@Override
	public ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto dto) {
		String telNumber = dto.getTelNumber();
		String telAuthNumber = dto.getTelAuthNumber();

		try {
			boolean isMatched = telAuthRepository.existsByTelNumberAndTelAuthNumber(telNumber, telAuthNumber);
			if (!isMatched) return ResponseDto.telAuthFail();
		} catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return ResponseDto.success();
	}

	//* 회원가입
	@Override
	public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
		String userId = dto.getUserId();
		String telNumber = dto.getTelNumber();
		String telAuthNumber = dto.getTelAuthNumber();
		String password = dto.getPassword();

		try {
			boolean isExistedId = userRepository.existsByUserId(userId);
			if (isExistedId) return ResponseDto.duplicatedUserId();

			boolean isExistedTelNumber = userRepository.existsByTelNumber(telNumber);
			if (isExistedTelNumber) return ResponseDto.duplicatedaTelNumber();

			boolean isMatched = telAuthRepository.existsByTelNumberAndTelAuthNumber(telNumber, telAuthNumber);
			if (!isMatched) return ResponseDto.telAuthFail();

			// 비밀번호 암호화
			String encodedPassword = passwordEncoder.encode(password);
			dto.setPassword(encodedPassword);

			UserEntity userEntity = new UserEntity(dto);
			userRepository.save(userEntity);
			
		} catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return ResponseDto.success();
	}

	//* 로그인
	@Override
	public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
		String userId = dto.getUserId();
		String password = dto.getPassword();

		String accessToken = null;

		try {
			UserEntity userEntity = userRepository.findByUserId(userId);
			if (userEntity == null) return ResponseDto.signInFail();
			
			String encodedPassword = userEntity.getPassword();
			boolean isMatched = passwordEncoder.matches(password, encodedPassword);
			if (!isMatched) return ResponseDto.signInFail();

			// 확인 성공하면 토큰 생성
			accessToken = jwtProvider.create(userId);
			if (accessToken == null) return ResponseDto.tokenCreateFail();
			
		} catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return SignInResponseDto.success(accessToken);
	}

	//* 첫단계 아이디 찾기
	@Override
	public ResponseEntity<ResponseDto> beforeIdSearch(IdSearchRequestDto dto) {
		String name = dto.getName();
		String telNumber = dto.getTelNumber();

		try {
			UserEntity userEntity = userRepository.findByName(name);
			if (userEntity == null) return ResponseDto.noExistInfo();

			UserEntity userEntity2 = userRepository.findByTelNumber(telNumber);
			if (userEntity2 == null) return ResponseDto.telAuthFail();

		} catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return ResponseDto.success();
	}

	//* 아이디 찾기
	@Override
	public ResponseEntity<? super IdSearchResponseDto> IdSearch(IdSearchAuthRequestDto dto) {
		String telNumber = dto.getTelNumber();
		String telAuthNumber = dto.getTelAuthNumber();

		try {
			UserEntity userEntity = userRepository.findByTelNumber(telNumber);
			if (userEntity == null) return ResponseDto.telAuthFail();

			boolean telAuthEntity = telAuthRepository.existsByTelAuthNumber(telAuthNumber);
			if (!telAuthEntity) return ResponseDto.telAuthFail();

		} catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return IdSearchResponseDto.success();
	}
	
}
