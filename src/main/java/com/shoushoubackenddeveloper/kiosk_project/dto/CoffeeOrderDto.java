package com.shoushoubackenddeveloper.kiosk_project.dto;

import com.shoushoubackenddeveloper.kiosk_project.domain.CoffeeOrder;

public record CoffeeOrderDto(
        Long id,
        OrdersDto ordersDto,
        CoffeeDto coffeeDto,
        Integer quantity
) {
    public static CoffeeOrderDto of(Long id, OrdersDto orders, CoffeeDto coffee, Integer quantity) {
        return new CoffeeOrderDto(id, orders, coffee, quantity);
    }

    public static CoffeeOrderDto of(OrdersDto orders, CoffeeDto coffee, Integer quantity) {
        return new CoffeeOrderDto(null, orders, coffee, quantity);
    }

    public static CoffeeOrderDto from(CoffeeOrder coffeeOrder) {
        return new CoffeeOrderDto(
                coffeeOrder.getId(),
                OrdersDto.from(coffeeOrder.getOrders()),
                CoffeeDto.from(coffeeOrder.getCoffee()),
                coffeeOrder.getQuantity()
        );
    }

    public CoffeeOrder toEntity() {
        return CoffeeOrder.of(
                ordersDto.toEntity(),
                coffeeDto.toEntity(),
                quantity
        );
    }

}
