package com.korit.thememorialday.dto.response.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.home.Thema;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.repository.resultSet.GetThemeResultSet;

import lombok.Getter;

//* 인기 테마 응답 dto */

@Getter
public class GetHotThemeResponseDto extends ResponseDto {

	// private List<String> thema;
	private List<Thema> themas;

	private GetHotThemeResponseDto(List<GetThemeResultSet> resultSets) {

		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

		// List<String> thema = new ArrayList<>();
		// for (GetThemeResultSet resultSet: resultSets) {
		// 	thema.add(resultSet.getThema());
		// }
		// this.thema = thema;

		this.themas = Thema.getList(resultSets);

	};

	public static ResponseEntity<GetHotThemeResponseDto> success(List<GetThemeResultSet> resultSets) {

		GetHotThemeResponseDto responseBody = new GetHotThemeResponseDto(resultSets);
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);

	};
	
}
