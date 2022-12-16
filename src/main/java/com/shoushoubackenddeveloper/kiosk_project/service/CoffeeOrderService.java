package com.shoushoubackenddeveloper.kiosk_project.service;

import com.shoushoubackenddeveloper.kiosk_project.domain.Coffee;
import com.shoushoubackenddeveloper.kiosk_project.domain.CoffeeOrder;
import com.shoushoubackenddeveloper.kiosk_project.domain.Orders;
import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeOrderDto;
import com.shoushoubackenddeveloper.kiosk_project.repository.CoffeeOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CoffeeOrderService {
    private final OrderService orderService;
    private final CoffeeService coffeeService;
    private final CoffeeOrderRepository coffeeOrderRepository;

    public CoffeeOrderDto addCoffee(Integer orderNo, String coffeeCode) {
        Orders orders = orderService.findOrder(orderNo);
        Coffee coffee = coffeeService.findCoffee(coffeeCode);

        CoffeeOrder coffeeOrder = CoffeeOrder.of(orders, coffee, 1);
        return CoffeeOrderDto.from(coffeeOrderRepository.save(coffeeOrder));
    }

    public void changeQuantity(Long coffeeOrderId, Integer quantity) {
        CoffeeOrder coffeeOrder = coffeeOrderRepository.findById(coffeeOrderId)
                .orElseThrow(() -> new EntityNotFoundException("해당 커피 오더가 존재하지 않음"));
        coffeeOrder.setQuantity(quantity);
        coffeeOrderRepository.save(coffeeOrder);
    }

    @Transactional(readOnly = true)
    public CoffeeOrder findOrderedCoffee(Long coffeeOrderId) {
        return coffeeOrderRepository.findById(coffeeOrderId).get();
    }

    public void deleteCoffeeOrder(String coffeeCode) {
        coffeeOrderRepository.deleteByCoffee_CoffeeCode(coffeeCode);
    }

    private boolean verifySelectedCoffee(String coffeeCode, CoffeeOrder coffeeOrder) {
        return coffeeCode.equals(coffeeOrder.getCoffee().getCoffeeCode());
    }

}
