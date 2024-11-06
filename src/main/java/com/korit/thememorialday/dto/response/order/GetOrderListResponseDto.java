package com.korit.thememorialday.dto.response.order;

import lombok.Getter;
import lombok.ToString;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.order.FullOrder;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import java.util.List;

@Getter
@ToString
public class GetOrderListResponseDto extends ResponseDto {

    private List<FullOrder> orders;

    private GetOrderListResponseDto(List<FullOrder> orders) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.orders = orders;
    }

    public static ResponseEntity<GetOrderListResponseDto> success(List<FullOrder> orders) {
        GetOrderListResponseDto responseBody = new GetOrderListResponseDto(orders);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}


// mypage에서 get 할 때 사용할 예정