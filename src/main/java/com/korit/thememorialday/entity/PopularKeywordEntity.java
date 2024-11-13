package com.korit.thememorialday.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//* 인기 키워드 테이블 엔터티 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="popular_keyword")
@Table(name="popular_keyword")
public class PopularKeywordEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer keywordId;
	private String keyword;
	private String keywordDate;

	// 검색어 저장을 하기 위한 생성자
	public PopularKeywordEntity(String keyword) {
		this.keyword = keyword;

		Date now = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String keywordDate = simpleDateFormat.format(now);

		this.keywordDate = keywordDate;
	}
	
}
