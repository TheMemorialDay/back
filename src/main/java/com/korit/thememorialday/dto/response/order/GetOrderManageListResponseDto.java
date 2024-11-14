package com.korit.thememorialday.dto.response.order;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.order.OrderManage;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GetOrderManageListResponseDto extends ResponseDto{
    private List<OrderManage> orderManages;
    
    private GetOrderManageListResponseDto(List<OrderManage> orderManages) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.orderManages = orderManages;
    }

    public static ResponseEntity<GetOrderManageListResponseDto> success(List<OrderManage> orderManages) {
        GetOrderManageListResponseDto responseBody = new GetOrderManageListResponseDto(orderManages);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
