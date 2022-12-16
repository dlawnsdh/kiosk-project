package com.shoushoubackenddeveloper.kiosk_project.dto;

import com.shoushoubackenddeveloper.kiosk_project.domain.Orders;

import java.time.LocalDateTime;

public record OrdersDto(
        Long id,
        Integer orderNo,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static OrdersDto of(Integer orderNo) {
        return new OrdersDto(null, orderNo, null, null);
    }

    public static OrdersDto of(Long id, Integer orderNo, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new OrdersDto(id, orderNo, createdAt, modifiedAt);
    }

    public static OrdersDto from(Orders orders) {
        return new OrdersDto(
                orders.getId(),
                orders.getOrderNo(),
                orders.getCreatedAt(),
                orders.getModifiedAt()
        );
    }

    public Orders toEntity() {
        return Orders.of(orderNo);
    }

}
