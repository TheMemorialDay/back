package com.korit.thememorialday.service.implement.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.thememorialday.dto.request.support.PostQnARequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.support.GetQnADetailResponseDto;
import com.korit.thememorialday.dto.response.support.GetQnAListResponseDto;
import com.korit.thememorialday.entity.support.QnAEntity;
import com.korit.thememorialday.repository.support.QnARepository;
import com.korit.thememorialday.service.support.QnAService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnAServiceImplement implements QnAService {

    private final QnARepository qnaRepository;

    @Override
    public ResponseEntity<? super GetQnAListResponseDto> GetQnAList() {
        List<QnAEntity> resultSets = new ArrayList<>();
        try {
            resultSets = qnaRepository.findByOrderByQuestionNumber();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetQnAListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetQnADetailResponseDto> getQnADetail(Integer questionNumber) {
        QnAEntity qnaEntity = new QnAEntity();
        try {
            qnaEntity = qnaRepository.findByQuestionNumber(questionNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetQnADetailResponseDto.success(qnaEntity);
    }

    @Override
    public ResponseEntity<ResponseDto> postQnA(PostQnARequestDto dto) {
        try {
            QnAEntity qnaEntity = new QnAEntity(dto);
            qnaRepository.save(qnaEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteQnA(Integer questionNumber) {
        try {
            QnAEntity qnaEntity = qnaRepository.findByQuestionNumber(questionNumber);
            if (qnaEntity == null)
                return ResponseDto.noExistQuestion();
            qnaRepository.delete(qnaEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

}
