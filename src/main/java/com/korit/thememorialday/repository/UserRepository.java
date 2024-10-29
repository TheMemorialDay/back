package com.korit.thememorialday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.UserEntity;

//# user 리포지토리

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	// 아이디 존재 확인
	boolean existsByUserId(String userId);
	// 전화번호 존재 확인
	boolean existsByTelNumber(String telNumber);

	// 로그인을 위한 유저 아이디 찾기
	// + 회원 개인 정보 불러오기를 위한 아이디 찾기
	// + 비밀번호 재설정을 위한 아이디 찾기
	UserEntity findByUserId(String userId);
	// 로그인을 위한 snsId & 가입 경로 찾기
	UserEntity findBySnsIdAndJoinPath(String snsId, String joinPath);

	// 아이디 찾기 (이름, 전화번호)
	UserEntity findByNameAndTelNumber(String name, String telNumber);
	// 아이디 찾기 (전화번호, 인증번호(tel_auth 리포지토리))
	UserEntity findByTelNumber(String telNumber);

	// 비밀번호 찾기 (아이디 & 전화번호 & 비밀번호)
	boolean existsByUserIdAndTelNumber(String userId, String telNumber);
	// 회원 정보 수정 시 비밀번호 확인
	UserEntity findByPassword(String password);
}
