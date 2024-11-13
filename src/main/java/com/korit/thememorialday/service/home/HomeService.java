package com.korit.thememorialday.service.home;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.response.home.GetHotThemeResponseDto;
import com.korit.thememorialday.dto.response.home.GetPopularKeywordResponseDto;

//* 메인화면 서비스 인터페이스 */

public interface HomeService {

	// * 인기 검색어 인기순 조회 */
	ResponseEntity<? super GetPopularKeywordResponseDto> getKeyword();

	//* 인기 테마 조회 */
	ResponseEntity<? super GetHotThemeResponseDto> getTheme();
	
}
