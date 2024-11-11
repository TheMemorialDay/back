// package com.korit.thememorialday.controller.keyword;

// import com.korit.thememorialday.dto.response.keyword.GetKeywordListResponseDto;
// import com.korit.thememorialday.service.keyword.KeywordService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// public class KeywordController {

//     private final KeywordService keywordService;

//     public KeywordController(KeywordService keywordService) {
//         this.keywordService = keywordService;
//     }

//     @GetMapping("/popular-keyword")
//     public ResponseEntity<? super GetKeywordListResponseDto> getPopularKeywords() {
//         return keywordService.getPopularKeywords();
//     }
// }
