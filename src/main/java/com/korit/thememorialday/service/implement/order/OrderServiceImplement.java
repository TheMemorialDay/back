package com.korit.thememorialday.service.implement.order;

import com.korit.thememorialday.common.object.order.FullOrder;
import com.korit.thememorialday.common.object.order.OrderManage;
import com.korit.thememorialday.common.object.order.OrderSelectOption;
import com.korit.thememorialday.dto.request.order.PatchOrderStatusDto;
import com.korit.thememorialday.dto.request.order.PostOrderRequestDto;
import com.korit.thememorialday.dto.request.order.PostSendPaymentMsgRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.order.GetOrderListResponseDto;
import com.korit.thememorialday.dto.response.order.GetOrderManageListResponseDto;
import com.korit.thememorialday.dto.response.sales.GetSalesResponseDto;
import com.korit.thememorialday.entity.UserEntity;
import com.korit.thememorialday.entity.order.OrderEntity;
import com.korit.thememorialday.entity.order.OrderSelectOptionEntity;
import com.korit.thememorialday.provider.SmsProvider;
import com.korit.thememorialday.repository.ProductRepository;
import com.korit.thememorialday.repository.StoreRepository;
import com.korit.thememorialday.repository.UserRepository;
import com.korit.thememorialday.repository.order.OrderRepository;
import com.korit.thememorialday.repository.order.OrderSelectOptionRepository;
import com.korit.thememorialday.service.order.OrderService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImplement implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderSelectOptionRepository orderSelectOptionRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final SmsProvider smsProvider;

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

                Integer optionNumber = orderSelectOption.getOptionNumber();
                Integer optionCategoryNumber = orderSelectOption.getOptionCategoryNumber();

                OrderSelectOptionEntity optionEntity = new OrderSelectOptionEntity(orderCode, optionNumber, optionCategoryNumber); 
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
    public ResponseEntity<GetOrderManageListResponseDto> getOrderList(String userId) {
        List<OrderEntity> orders = orderRepository.findByUserIdOrderByOrderTimeDesc(userId);
        List<OrderManage> fullOrders = new ArrayList<>();

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

            UserEntity userEntity = userRepository.findByUserId(userId);
            String name = userEntity.getName();
            String telNumber = userEntity.getTelNumber();

            // FullOrder 객체 생성 후 리스트에 추가
            fullOrders.add(new OrderManage(order, options, storeName, productName, productImageUrl, telNumber, name));
        }

        return GetOrderManageListResponseDto.success(fullOrders);
    }

    @Override
    @Transactional
    public ResponseEntity<GetSalesResponseDto> getSales(Integer storeNumber) {
        List<OrderEntity> orders = orderRepository.findByStoreNumber(storeNumber);
        
        if (orders.isEmpty()) {
            return GetSalesResponseDto.success(Collections.emptyList());
        }

        List<String> orderCodes = orders.stream().map(OrderEntity::getOrderCode).collect(Collectors.toList());

        List<OrderSelectOptionEntity> optionEntities = orderSelectOptionRepository.findByOrderCodeIn(orderCodes);

        Map<Integer, String> productCategoryMap = optionEntities.stream()
            .collect(Collectors.toMap(
                OrderSelectOptionEntity::getOptionCategoryNumber,
                option -> productRepository.findProductCategoryByOptionCategoryNumber(option.getOptionCategoryNumber()),
                (existing, replacement) -> existing // 중복 키 발생 시 기존 값을 유지
            ));

        Map<String, List<OrderSelectOption>> optionsMap = optionEntities.stream()
            .collect(Collectors.groupingBy(
                OrderSelectOptionEntity::getOrderCode,
                Collectors.mapping(
                    option -> new OrderSelectOption(
                        option.getOptionCategoryNumber(),
                        option.getOptionNumber(),
                        productCategoryMap.get(option.getOptionCategoryNumber())),
                    Collectors.toList())));

        List<FullOrder> fullOrders = orders.stream().map(order -> {
            String productName = productRepository.findProductNameByProductNumber(order.getProductNumber());
            String productImageUrl = productRepository.findFirstImageUrlByProductNumber(order.getProductNumber());
            List<OrderSelectOption> options = optionsMap.getOrDefault(order.getOrderCode(), new ArrayList<>());

            return new FullOrder(
                order,
                options,
                storeRepository.findStoreNameByStoreNumber(order.getStoreNumber()),
                productName,
                productImageUrl);
        }).collect(Collectors.toList());

        return GetSalesResponseDto.success(fullOrders);
    }
    


    @Transactional
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

    @Override
    public ResponseEntity<? super GetOrderManageListResponseDto> getOrderManageListUser(Integer storeNumber) {
        List<OrderManage> orderManages = new ArrayList<>();
        try {
            List<OrderEntity> orders = orderRepository.findByStoreNumberOrderByOrderTimeDesc(storeNumber);
            
            for (OrderEntity order : orders) {
                String storeName = storeRepository.findStoreNameByStoreNumber(order.getStoreNumber());
                String productName = productRepository.findProductNameByProductNumber(order.getProductNumber());
                String productImageUrl = productRepository.findFirstImageUrlByProductNumber(order.getProductNumber());
                
                UserEntity userEntity = userRepository.findByUserId(order.getUserId());
                String telNumber = userEntity.getTelNumber();
                String name = userEntity.getName();

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
                orderManages.add(new OrderManage(order, options, storeName, productName, 
                productImageUrl, telNumber, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetOrderManageListResponseDto.success(orderManages);
    }

    @Override
    public ResponseEntity<ResponseDto> postSendPaymentMsg(PostSendPaymentMsgRequestDto dto) {
        String telNumber = dto.getTelNumber();
        try {
            boolean isExistedTelNumber = userRepository.existsByTelNumber(telNumber);
			if (!isExistedTelNumber) return ResponseDto.noExistUserId();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        boolean isSendSuccess = smsProvider.sendPaymentMsg(dto.getName(), dto.getStoreName(), telNumber, dto.getTotalPrice(), dto.getProductName());
        if(!isSendSuccess) return ResponseDto.messageSendFail();

        return ResponseDto.success();
    }

}
