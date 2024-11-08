package com.korit.thememorialday.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.order.OrderSelectOptionEntity;

@Repository
public interface OrderSelectOptionRepository extends JpaRepository<OrderSelectOptionEntity, Integer> {
        List<OrderSelectOptionEntity> findByOrderCode(String orderCode);
        List<OrderSelectOptionEntity> findByOrderCodeIn(List<String> orderCodes); // 여러 주문 코드로 조회
}
