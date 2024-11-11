package com.korit.thememorialday.service.implement.keyword;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.thememorialday.dto.response.keyword.GetKeywordListResponseDto;
import com.korit.thememorialday.repository.KeywordRepository;
import com.korit.thememorialday.service.keyword.KeywordService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KeywordServiceImplement implements KeywordService {

    private final KeywordRepository keywordRepository;

    @Override
    public ResponseEntity<GetKeywordListResponseDto> getPopularKeywords() {
        List<Map<String, Object>> popularKeywords = keywordRepository.findPopularKeywords();
        return GetKeywordListResponseDto.success(popularKeywords);
    }
}
