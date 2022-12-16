package com.shoushoubackenddeveloper.kiosk_project.dto;

import com.shoushoubackenddeveloper.kiosk_project.domain.Coffee;

import java.time.LocalDateTime;

public record CoffeeDto(
        Long id,
        String coffeeCode,
        String korName,
        String engName,
        Integer price,
        String orderStatus,
        Boolean sizeSelectable,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static CoffeeDto of(String coffeeCode, String korName, String engName, Integer price, String orderStatus, Boolean sizeSelectable) {
        return new CoffeeDto(null, coffeeCode, korName, engName, price, orderStatus, sizeSelectable, null, null);
    }

    public static CoffeeDto of(Long id, String coffeeCode, String korName, String engName, Integer price, String orderStatus, Boolean sizeSelectable, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new CoffeeDto(id, coffeeCode, korName, engName, price, orderStatus, sizeSelectable, createdAt, modifiedAt);
    }

    public static CoffeeDto from(Coffee coffee) {
        return new CoffeeDto(
                coffee.getId(),
                coffee.getCoffeeCode(),
                coffee.getKorName(),
                coffee.getEngName(),
                coffee.getPrice(),
                coffee.getOrderStatus(),
                coffee.getSizeSelectable(),
                coffee.getCreatedAt(),
                coffee.getModifiedAt()
        );
    }

    public Coffee toEntity() {
        return Coffee.of(
                coffeeCode,
                korName,
                engName,
                price,
                orderStatus,
                sizeSelectable
        );
    }

}
