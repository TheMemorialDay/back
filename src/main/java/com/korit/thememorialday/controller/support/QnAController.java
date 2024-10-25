package com.korit.thememorialday.controller.support;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.support.PostQnARequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.support.GetQnADetailResponseDto;
import com.korit.thememorialday.dto.response.support.GetQnAListResponseDto;

import com.korit.thememorialday.service.support.QnAService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/support/notice/question")
@RequiredArgsConstructor
public class QnAController {
    private final QnAService qnaService;

    @PostMapping("/write")
    public ResponseEntity<ResponseDto> postQnA(
        @RequestBody @Valid PostQnARequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = qnaService.postQnA(requestBody);
        return response;
    }

    @GetMapping(value={"", "/"})
    public ResponseEntity<? super GetQnAListResponseDto> getQnAList() {
        ResponseEntity<? super GetQnAListResponseDto> response = qnaService.GetQnAList();
        return response;
    }

    @GetMapping("/{questionNumber}")
    public ResponseEntity<? super GetQnADetailResponseDto> getNotice(
        @PathVariable("questionNumber") Integer questionNumber
    ) {
        ResponseEntity<? super GetQnADetailResponseDto> response = qnaService.getQnADetail(questionNumber);
        return response;
    }

    @DeleteMapping("/{questionNumber}")
    public ResponseEntity<ResponseDto> deleteQnA(
        @PathVariable("questionNumber") Integer questionNumber
    ) {
        ResponseEntity<ResponseDto> response = qnaService.deleteQnA(questionNumber);
        return response;
    }
}
