package com.korit.thememorialday.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//# 전화번호 인증 엔터티

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="telAuth")
@Table(name="telAuth")
public class TelAuthEntity {
	@Id
	private String telNumber;
	private String telAuthNumber;
}
