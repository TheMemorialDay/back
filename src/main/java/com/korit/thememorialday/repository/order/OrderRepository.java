package com.korit.thememorialday.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.order.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    List<OrderEntity> findByUserId(String userId);
    OrderEntity findByOrderCode(String orderCode);
    List<OrderEntity> findByStoreNumber(Integer storeNumber);
    List<OrderEntity> findByUserIdOrderByOrderTimeDesc(String userId);

    List<OrderEntity> findByStoreNumberOrderByOrderTimeDesc(Integer storeNumber);
}
