package com.korit.thememorialday.dto.response.keyword;

import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Getter
public class GetKeywordListResponseDto extends ResponseDto {
    private List<Map<String, Object>> keywords;

    private GetKeywordListResponseDto(List<Map<String, Object>> keywords) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.keywords = keywords;
    }

    public static ResponseEntity<GetKeywordListResponseDto> success(List<Map<String, Object>> keywords) {
        GetKeywordListResponseDto responseBody = new GetKeywordListResponseDto(keywords);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
