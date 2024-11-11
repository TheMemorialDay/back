package com.korit.thememorialday.dto.response.product;

import lombok.Getter;
import lombok.ToString;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// import com.korit.thememorialday.dto.response.ResponseCode;
// import com.korit.thememorialday.dto.response.ResponseDto;
// import com.korit.thememorialday.dto.response.ResponseMessage;
// import com.korit.thememorialday.entity.ProductEntity;

// import java.util.List;
// import java.util.stream.Collectors;
import com.korit.thememorialday.common.object.FullProduct;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;

import java.util.List;

@Getter
@ToString
public class GetProductListResponseDto extends ResponseDto {
    
//     private List<GetProductResponseDto> products;
    private List<FullProduct> products;

    private GetProductListResponseDto(List<FullProduct> products) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.products = products;
    }

    public static ResponseEntity<GetProductListResponseDto> success(List<FullProduct> products) {
        GetProductListResponseDto responseBody = new GetProductListResponseDto(products);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
