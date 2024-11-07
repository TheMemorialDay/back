package com.korit.thememorialday.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.order.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String>{

    List<OrderEntity> findByUserIdOrderByOrderTimeDesc(String userId);
    
    OrderEntity findByOrderCode(String orderCode);
    
}
