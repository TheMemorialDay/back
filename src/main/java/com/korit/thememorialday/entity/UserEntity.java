package com.korit.thememorialday.entity;


import com.korit.thememorialday.dto.request.auth.SignUpRequestDto;
import com.korit.thememorialday.dto.request.join.PatchJoinRequestDto;
import com.korit.thememorialday.dto.request.mypage_user_info.PatchUserInfoRequestDto;
import com.korit.thememorialday.dto.request.auth.SignUpRequestDto;
import com.korit.thememorialday.dto.request.join.PatchJoinRequestDto;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//# user 엔터티

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
@Table(name="user")
@ToString
public class UserEntity {
	@Id
	private String userId;
	private String password;
	private String name;
	private String telNumber;
	private String birth;
	private String gender;
	private String joinPath;
	private String snsId;
	private String businessNumber;
	private String businessUrl;
	private String permission;
	private String businessOpendate;

	// 회원가입 시 데이터베이스에 데이터를 삽입하기 위한 생성자
	public UserEntity(SignUpRequestDto dto) {
		this.userId = dto.getUserId();
		this.password = dto.getPassword();
		this.name = dto.getName();
		this.telNumber = dto.getTelNumber();
		this.birth = dto.getBirth();
		this.gender = dto.getGender();
		this.joinPath = dto.getJoinPath();
		this.snsId = dto.getSnsId();
		this.permission = "일반";
	}

	// join patch 를 위한 생성자
	public void patch(PatchJoinRequestDto dto) {
        this.businessNumber = dto.getBusinessNumber();
		this.businessOpendate = dto.getBusinessOpendate();
		this.permission = "사장";
    }

	// 회원 수정을 위한 생성자
	public void patch(PatchUserInfoRequestDto dto) {
		this.password = dto.getPassword();
		this.name = dto.getName();
		this.birth = dto.getBirth();
		this.gender = dto.getGender();
		this.telNumber = dto.getTelNumber();
	}
}
