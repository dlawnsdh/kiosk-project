package com.shoushoubackenddeveloper.kiosk_project.dto.response;

import java.util.List;
import java.util.Map;

public record OrderWithOptionResponseDto(
        Long orderNo,
        Map<Map<CoffeeResponseDto, List<OptionResponseDto>>, Integer> orderCoffeesWithOptionsInfo
) {
    public static OrderWithOptionResponseDto of(Long orderNo, Map<Map<CoffeeResponseDto, List<OptionResponseDto>>, Integer> orderCoffeesInfo) {
        return new OrderWithOptionResponseDto(orderNo, orderCoffeesInfo);
    }

}
