package com.shoushoubackenddeveloper.kiosk_project.dto.response;

import com.shoushoubackenddeveloper.kiosk_project.domain.CoffeeOrderOption;
import com.shoushoubackenddeveloper.kiosk_project.domain.Option;

public record OptionResponseDto(
        Long id,
        String korName,
        String engName,
        Integer price,
        Integer quantity
) {
    public static OptionResponseDto from(CoffeeOrderOption coffeeOrderOption) {
        Option option = coffeeOrderOption.getOption();
        return new OptionResponseDto(
                option.getId(),
                option.getKorName(),
                option.getEngName(),
                option.getPrice(),
                coffeeOrderOption.getQuantity()
        );
    }

}
