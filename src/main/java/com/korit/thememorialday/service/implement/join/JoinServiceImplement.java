package com.korit.thememorialday.service.implement.join;


import com.korit.thememorialday.dto.request.join.PatchJoinRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;

import com.korit.thememorialday.entity.UserEntity;
import com.korit.thememorialday.repository.UserRepository;
import com.korit.thememorialday.service.join.JoinService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinServiceImplement implements JoinService{
    
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<ResponseDto> patchUserPermission(String userId, PatchJoinRequestDto dto) {
        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.noExistUserId();

            userEntity.patch(dto);
            userRepository.save(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }
}
