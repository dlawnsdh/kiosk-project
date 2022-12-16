package com.shoushoubackenddeveloper.kiosk_project.service;

import com.shoushoubackenddeveloper.kiosk_project.domain.Orders;
import com.shoushoubackenddeveloper.kiosk_project.dto.OrdersDto;
import com.shoushoubackenddeveloper.kiosk_project.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public void createOrder(Integer orderNo) {
        if (!existedOrder(orderNo))
            orderRepository.save(Orders.of(orderNo));
    }

    @Transactional(readOnly = true)
    public Orders findOrder(Integer orderNo) {
        return orderRepository.findByOrderNo(orderNo)
                .orElseThrow(() -> new EntityNotFoundException("해당 주문 번호가 존재하지 않음"));
    }

    public OrdersDto findOrderDto(Integer orderNo) {
        return orderRepository.findByOrderNo(orderNo)
                .map(OrdersDto::from)
                .orElseThrow(() -> new EntityNotFoundException("해당 주문 번호가 존재하지 않음"));
    }

    private boolean existedOrder(Integer orderNo) {
        if (orderRepository.findByOrderNo(orderNo).isPresent())
            return true;
        return false;
    }

}
