package com.korit.thememorialday.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.thememorialday.common.util.AuthNumberCreator;
import com.korit.thememorialday.dto.request.auth.TelAuthCheckRequestDto;
import com.korit.thememorialday.dto.request.auth.TelAuthRequestDto;
import com.korit.thememorialday.dto.request.mypage_user_info.PatchUserInfoRequestDto;
import com.korit.thememorialday.dto.request.mypage_user_info.UserUpdatePasswordCheckRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.mypage_user_info.GetUserInfoResponseDto;
import com.korit.thememorialday.dto.response.mypage_user_info.GetUserUpdateInfoResponseDto;
import com.korit.thememorialday.entity.TelAuthEntity;
import com.korit.thememorialday.entity.UserEntity;
import com.korit.thememorialday.provider.SmsProvider;
import com.korit.thememorialday.repository.TelAuthRepository;
import com.korit.thememorialday.repository.UserRepository;
import com.korit.thememorialday.service.MypageService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageServiceImplement implements MypageService {

	private final UserRepository userRepository;
	private final TelAuthRepository telAuthRepository;

	private final SmsProvider smsProvider;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	// * 회원정보 수정 시 비밀번호 확인 후 개인 정보 불러오기
	@Override
	public ResponseEntity<? super GetUserInfoResponseDto> userUpdatePasswordCheck(
		UserUpdatePasswordCheckRequestDto dto,
		String userId) {
		
		UserEntity userEntity = null;

		try {

			String password = dto.getPassword();

			userEntity = userRepository.findByUserId(userId);
			if (userEntity == null) return ResponseDto.noExistInfo();

			String prePassword = userEntity.getPassword();
			boolean isMatched = passwordEncoder.matches(password, prePassword);
			if (!isMatched) return ResponseDto.noPermission();

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return GetUserInfoResponseDto.success(userEntity);
	}

	// * 회원 개인 정보 수정
	@Override
	public ResponseEntity<ResponseDto> patchUserInfo(
		PatchUserInfoRequestDto dto, 
		String userId) {

		try {

			String name  = dto.getName();
			String birth = dto.getBirth();
			String gender = dto.getGender();
			String telNumber = dto.getTelNumber();

			UserEntity userEntity = userRepository.findByUserId(userId);
			if (userEntity == null) return ResponseDto.noExistUserId();

			userEntity.setName(name);
			userEntity.setBirth(birth);
			userEntity.setGender(gender);
			userEntity.setTelNumber(telNumber);

			userRepository.save(userEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			ResponseDto.databaseError();
		}

		return ResponseDto.success();

	}

	//* 정보 수정 때 전화번호 확인
	@Override
	public ResponseEntity<ResponseDto> userUpdateTelAuth(
		TelAuthRequestDto dto, 
		String userId) {

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
		if (!isSendSuccess) return ResponseDto.messageSendFail();

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

	//* 정보 수정 때 전화번호 + 인증번호 확인
	@Override
	public ResponseEntity<ResponseDto> userUpdateTelAuthCheck(
		TelAuthCheckRequestDto dto, 
		String userId) {
			
		String telNumber = dto.getTelNumber();
		String telAuthNumber = dto.getTelAuthNumber();

		try {
			UserEntity userEntity = userRepository.findByUserId(userId);
			if (userEntity == null) return ResponseDto.noExistUserId();
			
			boolean isMatched = telAuthRepository.existsByTelNumberAndTelAuthNumber(telNumber, telAuthNumber);
			if (!isMatched) return ResponseDto.telAuthFail();

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return ResponseDto.success();
	}

	@Override
	public ResponseEntity<? super GetUserUpdateInfoResponseDto> getUpdateInfo(String userId) {
		UserEntity userEntity = null;
		try {
			userEntity = userRepository.findByUserId(userId);
			if(userEntity == null) return ResponseDto.noExistUserId();
			
		} catch (Exception e) {
			return ResponseDto.databaseError();
		}
		return GetUserUpdateInfoResponseDto.success(userEntity);
	}
	
}
