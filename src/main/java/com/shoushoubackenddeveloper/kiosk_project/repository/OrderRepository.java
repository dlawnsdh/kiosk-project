package com.shoushoubackenddeveloper.kiosk_project.repository;

import com.shoushoubackenddeveloper.kiosk_project.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    Optional<Orders> findByOrderNo(Integer orderNo);

}
