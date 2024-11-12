package com.korit.thememorialday.dto.response.store;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.repository.resultSet.GetKeywordResultSet;

import lombok.Getter;

//* 인기 키워드 응답 dto */

@Getter
public class GetPopularKeywordResponseDto extends ResponseDto {

	private List<String> keyword;

	private GetPopularKeywordResponseDto(List<GetKeywordResultSet> resultSets) {

		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		
		List<String> keyword = new ArrayList<>();
		for (GetKeywordResultSet resultSet: resultSets) {
			keyword.add(resultSet.getKeyword());
		}
		this.keyword = keyword;

	};

	public static ResponseEntity<GetPopularKeywordResponseDto> success(List<GetKeywordResultSet> resultSets) {

		GetPopularKeywordResponseDto responseBody = new GetPopularKeywordResponseDto(resultSets);
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);

	};
	
}
