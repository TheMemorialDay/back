package com.korit.thememorialday.controller.join;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.join.PatchJoinRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.service.join.JoinService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/join")
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;

    @PatchMapping("/{userId}")
    public ResponseEntity<ResponseDto> patchUserPermission(
        @PathVariable("userId") String userId,
        @RequestBody @Valid PatchJoinRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = joinService.patchUserPermission(userId, requestBody);
        return response;
    }
}
