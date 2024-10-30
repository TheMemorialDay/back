package com.korit.thememorialday.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.thememorialday.dto.request.mypage_user_info.PatchUserInfoRequestDto;
import com.korit.thememorialday.dto.request.mypage_user_info.UserUpdatePasswordCheckRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.mypage_user_info.GetUserInfoResponseDto;
import com.korit.thememorialday.entity.UserEntity;
import com.korit.thememorialday.provider.JwtProvider;
import com.korit.thememorialday.repository.UserRepository;
import com.korit.thememorialday.service.MypageService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageServiceImplement implements MypageService {

	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	// * 회원정보 수정 시 비밀번호 확인 후 개인 정보 불러오기
	// (애초에 로그인 되어있기 때문에 토큰 있을 것임)
	@Override
	public ResponseEntity<? super GetUserInfoResponseDto> userUpdatePasswordCheck(
		UserUpdatePasswordCheckRequestDto dto,
		String userId) {
		
		UserEntity userEntity = null;

		try {

			String pickUserId = dto.getUserId();
			String password = dto.getPassword();

			userEntity = userRepository.findByUserId(pickUserId);
			if (userEntity == null) return ResponseDto.noExistInfo();

			String encodedPassword = userEntity.getPassword();
			boolean isMatched = passwordEncoder.matches(password, encodedPassword);
			if (!isMatched) return ResponseDto.noPermission();

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return GetUserInfoResponseDto.success(userEntity);
	}

	// * 회원 개인 정보 보기
	// @Override
	// public ResponseEntity<? super GetUserInfoResponseDto> getUserInfo(String userId) {

	// 	UserEntity userEntity = null;

	// 	try {
	// 		userEntity = userRepository.findByUserId(userId);
	// 		if (userEntity == null)
	// 			return ResponseDto.noExistInfo();
	// 	} catch (Exception exception) {
	// 		exception.printStackTrace();
	// 		return ResponseDto.databaseError();
	// 	}

	// 	return GetUserInfoResponseDto.success(userEntity);

	// }

	// * 회원 개인 정보 수정
	@Override
	public ResponseEntity<ResponseDto> patchUserInfo(PatchUserInfoRequestDto dto, String userId) {

		try {

			String password = dto.getPassword();
			String name = dto.getName();
			String birth = dto.getBirth();
			String gender = dto.getGender();
			String telNumber = dto.getTelNumber();

			UserEntity userEntity = userRepository.findByUserId(userId);
			if (userEntity == null)
				return ResponseDto.noExistUserId();

			userEntity.setPassword(password);
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
	
}
