package com.korit.thememorialday.dto.response.support;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.support.QnA;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.repository.resultSet.GetQnAListResultSet;

import lombok.Getter;

@Getter
public class GetQnAListResponseDto extends ResponseDto{
    private List<QnA> qnas;

    private GetQnAListResponseDto(List<GetQnAListResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.qnas = QnA.getList(resultSets);
    }

    public static ResponseEntity<GetQnAListResponseDto> success(List<GetQnAListResultSet> resultSets) {
        GetQnAListResponseDto responseBody = new GetQnAListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
