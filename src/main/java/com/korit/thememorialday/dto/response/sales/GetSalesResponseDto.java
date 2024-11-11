package com.korit.thememorialday.dto.response.sales;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.order.FullOrder;
import com.korit.thememorialday.common.object.sales.SalesData;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetSalesResponseDto extends ResponseDto {
    private List<SalesData> salesData;
    private List<FullOrder> fullOrders;

    public GetSalesResponseDto(List<FullOrder> fullOrders) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.fullOrders = fullOrders;
    }

    public static ResponseEntity<GetSalesResponseDto> success(List<FullOrder> fullOrders) {
        GetSalesResponseDto responseBody = new GetSalesResponseDto(fullOrders);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    // public static ResponseEntity<GetOrderListResponseDto> success(List<FullOrder>
    // orders) {
    // GetOrderListResponseDto responseBody = new GetOrderListResponseDto(orders);
    // return ResponseEntity.status(HttpStatus.OK).body(responseBody);
}
