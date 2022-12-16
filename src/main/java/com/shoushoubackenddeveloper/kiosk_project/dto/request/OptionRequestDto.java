package com.shoushoubackenddeveloper.kiosk_project.dto.request;

import com.shoushoubackenddeveloper.kiosk_project.dto.OptionDto;

public record OptionRequestDto(
        Long id,
        String korName,
        String engName,
        Integer price,
        Integer quantity
) {
    public static OptionRequestDto of(Long id, String korName, String engName, Integer price, Integer quantity) {
        return new OptionRequestDto(id, korName, engName, price, quantity);
    }

    public OptionDto ToDto() {
        return new OptionDto(
                id,
                korName,
                engName,
                price
        );
    }

}
