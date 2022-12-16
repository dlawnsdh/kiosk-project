package com.shoushoubackenddeveloper.kiosk_project.dto;

import com.shoushoubackenddeveloper.kiosk_project.domain.Option;

public record OptionDto(
        Long id,
        String korName,
        String engName,
        Integer price
) {
    public static OptionDto of(Long id, String korName, String engName, Integer price) {
        return new OptionDto(id, korName, engName, price);
    }

    public static OptionDto of(String korName, String engName, Integer price, Integer quantity) {
        return new OptionDto(null, korName, engName, price);
    }

    public static OptionDto from(Option option) {
        return new OptionDto(
                option.getId(),
                option.getKorName(),
                option.getEngName(),
                option.getPrice()
        );
    }

    public Option toEntity() {
        return Option.of(
                korName,
                engName,
                price
        );
    }

}
