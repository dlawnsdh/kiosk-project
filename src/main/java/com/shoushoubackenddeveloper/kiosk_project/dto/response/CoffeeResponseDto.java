package com.shoushoubackenddeveloper.kiosk_project.dto.response;

import com.shoushoubackenddeveloper.kiosk_project.domain.Coffee;
import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeDto;

public record CoffeeResponseDto(
        Long id,
        String coffeeCode,
        String korName,
        String engName,
        Integer price,
        String orderStatus,
        Boolean sizeSelectable
) {
    public static CoffeeResponseDto of(Long id, String coffeeCode, String korName, String engName, Integer price, String orderStatus, Boolean sizeSelectable) {
        return new CoffeeResponseDto(id, coffeeCode, korName, engName, price, orderStatus, sizeSelectable);
    }

    public static CoffeeResponseDto from(CoffeeDto dto) {
        return new CoffeeResponseDto(
                dto.id(),
                dto.coffeeCode(),
                dto.korName(),
                dto.engName(),
                dto.price(),
                dto.orderStatus(),
                dto.sizeSelectable()
        );
    }

    public static CoffeeResponseDto from(Coffee coffee) {
        return new CoffeeResponseDto(
                coffee.getId(),
                coffee.getCoffeeCode(),
                coffee.getKorName(),
                coffee.getEngName(),
                coffee.getPrice(),
                coffee.getOrderStatus(),
                coffee.getSizeSelectable()
        );
    }

}
