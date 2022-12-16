package com.shoushoubackenddeveloper.kiosk_project.repository;

import com.shoushoubackenddeveloper.kiosk_project.domain.CoffeeOrderOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoffeeOrderOptionRepository extends JpaRepository<CoffeeOrderOption, Long> {
    Optional<CoffeeOrderOption> findByCoffeeOrder_Id(Long coffeeOrderId);
    List<CoffeeOrderOption> findAllByCoffeeOrder_Id(Long coffeeOrderId);

}
