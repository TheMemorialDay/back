package com.korit.thememorialday.dto.response.store;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.PreviewProduct;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;


import lombok.Getter;

@Getter
public class GetProductPreviewListResponseDto extends ResponseDto{
    List<PreviewProduct> previewProducts;

    private GetProductPreviewListResponseDto(List<PreviewProduct> previewProducts) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.previewProducts = previewProducts;
    }

    public static ResponseEntity<GetProductPreviewListResponseDto> success(List<PreviewProduct> previewProducts) {
        GetProductPreviewListResponseDto responseBody = new GetProductPreviewListResponseDto(previewProducts);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
