package com.korit.thememorialday.repository.order;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.korit.thememorialday.entity.order.OrderSelectOptionEntity;

public interface OrderSelectOptionRepository extends JpaRepository<OrderSelectOptionEntity, Integer> {
        List<OrderSelectOptionEntity> findByOrderCode(String orderCode);
}
