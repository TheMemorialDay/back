package com.korit.thememorialday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.TelAuthEntity;

//# 전화번호 인증 리포지토리

@Repository
public interface TelAuthRepository extends JpaRepository<TelAuthEntity, String> {
	// 전화번호 + 인증번호 확인
	boolean existsByTelNumberAndTelAuthNumber(String telNumber, String telAuthNumber);

	// 아이디 찾기 (인증번호 확인)
	boolean existsByTelAuthNumber(String telAuthNumber);
}
