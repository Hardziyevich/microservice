package com.hardziyevich.order.userorder;

import com.hardziyevich.order.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOrderRepository extends JpaRepository<UserOrder,Long> {

    List<UserOrder> findUserOrderByGroomerId(Long id);
}
