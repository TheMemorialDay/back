package com.korit.thememorialday.controller.home;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.response.home.GetHotThemeResponseDto;
import com.korit.thememorialday.dto.response.home.GetPopularKeywordResponseDto;
import com.korit.thememorialday.service.home.HomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

	private final HomeService mainService;

	// * 인기 키워드 조회 */
	@GetMapping("/hot-keyword")
	public ResponseEntity<? super GetPopularKeywordResponseDto> getKeyword() {
		ResponseEntity<? super GetPopularKeywordResponseDto> response = mainService.getKeyword();
		return response;
	};

	// * 인기 테마 조회 */
	@GetMapping("/hot-theme")
	public ResponseEntity<? super GetHotThemeResponseDto> getTheme() {
		ResponseEntity<? super GetHotThemeResponseDto> response = mainService.getTheme();
		return response;
	};
	
}
