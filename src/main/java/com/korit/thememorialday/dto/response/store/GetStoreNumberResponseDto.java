package com.korit.thememorialday.dto.response.store;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;

import com.korit.thememorialday.entity.StoreEntity;


import lombok.Getter;

@Getter
public class GetStoreNumberResponseDto extends ResponseDto{

    private Integer storeNumber;
    
    public GetStoreNumberResponseDto(StoreEntity storeEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.storeNumber = storeEntity.getStoreNumber();
    }

    public static ResponseEntity<GetStoreNumberResponseDto> success(StoreEntity storeEntity) {
        GetStoreNumberResponseDto responseBody = new GetStoreNumberResponseDto(storeEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    
}
