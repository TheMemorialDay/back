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
	UserEntity findByUserId(String userId);
	// 로그인을 위한 snsId & 가입 경로 찾기
	UserEntity findBySnsIdAndJoinPath(String snsId, String joinPath);

	//* 아이디 찾기 */
	// 아이디 찾기 (이름, 전화번호)
	UserEntity findByNameAndTelNumber(String name, String telNumber);

	//* 비밀번호 재설정*/
	// 비밀번호 재설정 시 (아이디 & 전화번호) 확인
	UserEntity findByUserIdAndTelNumber(String userId, String telNumber);

	// 회원 정보 수정 시 비밀번호 확인
	UserEntity findByPassword(String password);
}
