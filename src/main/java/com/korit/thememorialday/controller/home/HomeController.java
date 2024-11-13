package com.korit.thememorialday.controller.home;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.response.store.GetPopularKeywordResponseDto;
import com.korit.thememorialday.service.home.HomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

	private final HomeService mainService;

	// * 인기키워드 조회 */
	@GetMapping("/hot-keyword")
	public ResponseEntity<? super GetPopularKeywordResponseDto> getKeyword() {
		ResponseEntity<? super GetPopularKeywordResponseDto> response = mainService.getKeyword();
		return response;
	};
	
}
