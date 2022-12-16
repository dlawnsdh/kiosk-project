package com.shoushoubackenddeveloper.kiosk_project.repository;

import com.shoushoubackenddeveloper.kiosk_project.domain.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
    List<CoffeeOrder> findAllByOrders_Id(Long orderId);
    void deleteByCoffee_CoffeeCode(String coffeeCode);

}
