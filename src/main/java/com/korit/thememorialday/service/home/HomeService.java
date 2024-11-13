package com.korit.thememorialday.service.home;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.response.store.GetPopularKeywordResponseDto;

//* 메인화면 서비스 인터페이스 */

public interface HomeService {

	//* 인기 검색어 인기순 조회 */
  ResponseEntity<? super GetPopularKeywordResponseDto> getKeyword();
	
}
