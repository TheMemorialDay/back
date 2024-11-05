package com.korit.thememorialday.service.implement.order;

import com.korit.thememorialday.common.object.order.OrderSelectOption;
import com.korit.thememorialday.dto.request.order.PostOrderRequestDto;
import com.korit.thememorialday.dto.request.order.PostOrderSelectOptionRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.entity.order.OrderEntity;
import com.korit.thememorialday.entity.order.OrderSelectOptionEntity;
import com.korit.thememorialday.repository.ProductOptionRepository;
import com.korit.thememorialday.repository.ProductRepository;
import com.korit.thememorialday.repository.StoreRepository;
import com.korit.thememorialday.repository.UserRepository;
import com.korit.thememorialday.repository.order.OrderRepository;
import com.korit.thememorialday.repository.order.OrderSelectOptionRepository;
import com.korit.thememorialday.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImplement implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderSelectOptionRepository orderSelectOptionRepository;

    @Override                                                       
    public ResponseEntity<ResponseDto> postOrder(PostOrderRequestDto dto, String userId, Integer storeNumber, Integer productNumber) {
        try {

            OrderEntity orderEntity = new OrderEntity(dto, userId, storeNumber, productNumber);
            orderRepository.save(orderEntity);
            String orderCode = orderEntity.getOrderCode();
    
            List<OrderSelectOption> orderSelectOptions = dto.getOptions();
            List<OrderSelectOptionEntity> optionEntities = new ArrayList<>();
            for (OrderSelectOption orderSelectOption : orderSelectOptions) {

                Integer optionNumber = orderSelectOption.getOptionNumber();
                Integer orderCategoryNumber = orderSelectOption.getOrderCategoryNumber();
                
                OrderSelectOptionEntity optionEntity = new OrderSelectOptionEntity(orderCode, optionNumber, orderCategoryNumber);
                optionEntities.add(optionEntity);
            }
            orderSelectOptionRepository.saveAll(optionEntities);
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }


    // mypage에서 get 할 때 사용할 예정
    // @Override
    // public ResponseEntity<GetOrderListResponseDto> getOrderList(String userId) {
    //     try {
    //         // 사용자 ID로 주문 목록 조회
    //         List<OrderEntity> orderEntities = orderRepository.findByUserId(userId);
    //         List<Order> orders = Order.getList(orderEntities);

    //         // 주문에 포함된 옵션 정보 추가
    //         for (Order order : orders) {
    //             List<OrderSelectOptionEntity> optionEntities = orderSelectOptionRepository.findByOrderCode(order.getOrderCode());
    //             List<OrderSelectOption> options = new ArrayList<>();
    //             for (OrderSelectOptionEntity optionEntity : optionEntities) {
    //                 options.add(new OrderSelectOption(optionEntity));
    //             }
    //             order.setOptions(options);
    //         }

    //         return GetOrderListResponseDto.success(orders);

    //     } catch (Exception exception) {
    //         exception.printStackTrace();
    //         return ResponseDto.databaseError();
    //     }
    // }


    @Transactional
    @Override
    public ResponseEntity<ResponseDto> cancelOrder(String orderCode) {
        try {
            OrderEntity orderEntity = orderRepository.findByOrderCode(orderCode);
            if (orderEntity == null) return ResponseDto.noExistOrder();

            // 삭제 대신 상태를 '취소됨'으로 업데이트
            orderEntity.setOrderStatus("취소됨");
            orderRepository.save(orderEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }
}
