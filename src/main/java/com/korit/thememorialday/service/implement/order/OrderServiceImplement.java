package com.korit.thememorialday.service.implement.order;

import com.korit.thememorialday.common.object.order.FullOrder;
import com.korit.thememorialday.common.object.order.OrderSelectOption;
import com.korit.thememorialday.dto.request.order.PatchOrderStatusDto;
import com.korit.thememorialday.dto.request.order.PostOrderRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.order.GetOrderListResponseDto;
import com.korit.thememorialday.entity.order.OrderEntity;
import com.korit.thememorialday.entity.order.OrderSelectOptionEntity;
import com.korit.thememorialday.repository.ProductRepository;
import com.korit.thememorialday.repository.StoreRepository;
import com.korit.thememorialday.repository.order.OrderRepository;
import com.korit.thememorialday.repository.order.OrderSelectOptionRepository;
import com.korit.thememorialday.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImplement implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderSelectOptionRepository orderSelectOptionRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    @Override
    public ResponseEntity<ResponseDto> postOrder(PostOrderRequestDto dto, String userId, Integer storeNumber,
            Integer productNumber) {
        try {

            OrderEntity orderEntity = new OrderEntity(dto, userId, storeNumber, productNumber);
            orderRepository.save(orderEntity);
            String orderCode = orderEntity.getOrderCode();

            List<OrderSelectOption> orderSelectOptions = dto.getOptions();
            List<OrderSelectOptionEntity> optionEntities = new ArrayList<>();
            for (OrderSelectOption orderSelectOption : orderSelectOptions) {

                // Integer optionNumber = orderSelectOption.getOptionNumber();
                Integer optionCategoryNumber = orderSelectOption.getOptionCategoryNumber();

                OrderSelectOptionEntity optionEntity = new OrderSelectOptionEntity(orderCode, optionCategoryNumber); // optionNumber
                                                                                                                     // 안
                                                                                                                     // 받기로
                                                                                                                     // 해당
                                                                                                                     // 줄에서
                                                                                                                     // 지웠습니다
                optionEntities.add(optionEntity);
            }
            orderSelectOptionRepository.saveAll(optionEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    // 주문내역
    @Override
    public ResponseEntity<GetOrderListResponseDto> getOrderList(String userId) {
        List<OrderEntity> orders = orderRepository.findByUserIdOrderByOrderTimeDesc(userId);
        List<FullOrder> fullOrders = new ArrayList<>();

        for (OrderEntity order : orders) {
            String storeName = storeRepository.findStoreNameByStoreNumber(order.getStoreNumber());
            String productName = productRepository.findProductNameByProductNumber(order.getProductNumber());
            String productImageUrl = productRepository.findFirstImageUrlByProductNumber(order.getProductNumber());

            // 옵션 정보 조회
            List<OrderSelectOptionEntity> optionEntities = orderSelectOptionRepository
                    .findByOrderCode(order.getOrderCode());
            List<OrderSelectOption> options = optionEntities.stream()
                    .map(optionEntity -> {
                        String productCategory = productRepository
                                .findProductCategoryByOptionCategoryNumber(optionEntity.getOptionCategoryNumber());
                        return new OrderSelectOption(optionEntity, productCategory); // productCategory 추가
                    })

                    .collect(Collectors.toList());

            // FullOrder 객체 생성 후 리스트에 추가
            fullOrders.add(new FullOrder(order, options, storeName, productName, productImageUrl));
        }

        return GetOrderListResponseDto.success(fullOrders);
    }

    @Override
    public ResponseEntity<ResponseDto> patchOrderStatus(String orderCode, PatchOrderStatusDto dto) {

        try {
            OrderEntity orderEntity = orderRepository.findByOrderCode(orderCode);
            if (orderEntity == null)
                return ResponseDto.noExistOrder();

            orderEntity.patch(dto);
            orderRepository.save(orderEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<GetOrderListResponseDto> getOrderManageList(Integer storeNumber) {
        List<OrderEntity> orders = orderRepository.findByStoreNumberOrderByOrderTimeDesc(storeNumber);
        List<FullOrder> fullOrders = new ArrayList<>();

        for (OrderEntity order : orders) {
            String storeName = storeRepository.findStoreNameByStoreNumber(order.getStoreNumber());
            String productName = productRepository.findProductNameByProductNumber(order.getProductNumber());
            String productImageUrl = productRepository.findFirstImageUrlByProductNumber(order.getProductNumber());

            // 옵션 정보 조회
            List<OrderSelectOptionEntity> optionEntities = orderSelectOptionRepository
                    .findByOrderCode(order.getOrderCode());
            List<OrderSelectOption> options = optionEntities.stream()
                    .map(optionEntity -> {
                        String productCategory = productRepository
                                .findProductCategoryByOptionCategoryNumber(optionEntity.getOptionCategoryNumber());
                        return new OrderSelectOption(optionEntity, productCategory); // productCategory 추가
                    })

                    .collect(Collectors.toList());

            // FullOrder 객체 생성 후 리스트에 추가
            fullOrders.add(new FullOrder(order, options, storeName, productName, productImageUrl));
        }

        return GetOrderListResponseDto.success(fullOrders);
    }
}
