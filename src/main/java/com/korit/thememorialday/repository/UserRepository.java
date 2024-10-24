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
	UserEntity findByUserId(String userId);
	// 로그인을 위한 snsId & 가입 경로 찾기
	UserEntity findBySnsIdAndJoinPath(String snsId, String joinPath);
}
