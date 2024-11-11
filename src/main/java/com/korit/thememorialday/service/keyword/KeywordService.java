package com.korit.thememorialday.service.keyword;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.response.keyword.GetKeywordListResponseDto;

public interface KeywordService {
    ResponseEntity<? super GetKeywordListResponseDto> getPopularKeywords();
}
