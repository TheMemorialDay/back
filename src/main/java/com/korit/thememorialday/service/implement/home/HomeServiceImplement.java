package com.korit.thememorialday.service.implement.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.home.GetHotThemeResponseDto;
import com.korit.thememorialday.dto.response.home.GetPopularKeywordResponseDto;
import com.korit.thememorialday.repository.PopularKeywordRepository;
import com.korit.thememorialday.repository.ThemaRepostiroy;
import com.korit.thememorialday.repository.resultSet.GetKeywordResultSet;
import com.korit.thememorialday.repository.resultSet.GetThemeResultSet;
import com.korit.thememorialday.service.home.HomeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeServiceImplement implements HomeService {

	private final PopularKeywordRepository popularKeywordRepository;
	private final ThemaRepostiroy themaRepostiroy;

	// * 인기 키워드 가져오기 */
	@Override
	public ResponseEntity<? super GetPopularKeywordResponseDto> getKeyword() {

		List<GetKeywordResultSet> resultSets = new ArrayList<>();

		try {

			resultSets = popularKeywordRepository.getKeyword();

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}
		;

		return GetPopularKeywordResponseDto.success(resultSets);

	}

	//* 인기 테마 가져오기 */
	@Override
	public ResponseEntity<? super GetHotThemeResponseDto> getTheme() {
		
		List<GetThemeResultSet> resultSets = new ArrayList<>();

		try {

			resultSets = themaRepostiroy.getTheme();

		} catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		};

		return GetHotThemeResponseDto.success(resultSets);

	}
	
}
