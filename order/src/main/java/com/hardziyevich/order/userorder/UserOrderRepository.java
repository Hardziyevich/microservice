package com.hardziyevich.order.userorder;

import com.hardziyevich.order.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

    @Query("SELECT uo FROM UserOrder uo WHERE uo.groomerId =?1 AND uo.day=?2")
    List<UserOrder> findUserOrderByGroomerIdAndDay(Long groomerId, LocalDate day);
}
