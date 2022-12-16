package com.shoushoubackenddeveloper.kiosk_project.controller;

import com.shoushoubackenddeveloper.kiosk_project.dto.response.OrderWithOptionResponseDto;
import com.shoushoubackenddeveloper.kiosk_project.service.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final CoffeeOrderService coffeeOrderService;
    private final CoffeeOrderOptionService coffeeOrderOptionService;

    @GetMapping
    public String createOrder(@RequestParam("orderNo") Integer orderNo,
                              HttpServletResponse response) {
        orderService.createOrder(orderNo); // 주문 생성
        Cookie cookie = new Cookie("orderNo", String.valueOf(orderNo));
        cookie.setDomain("localhost");
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/coffees";
    }

    @GetMapping("/{order-id}") // 주문 정보 확인
    public ResponseEntity getOrderInfo(@PathVariable("order-id") Long orderId) {
        // 옵션이 포함되지 않은 커피 주문 정보
        //Map<CoffeeResponseDto, Integer> coffeeOrderInfo = coffeeOrderService.getOrderedCoffeeWithOrderId(orderId);
        //OrderResponseDto orderResponseDto = OrderResponseDto.of(orderId, coffeeOrderInfo);

        OrderWithOptionResponseDto coffeeOrderInfoWithOptions =
                OrderWithOptionResponseDto.of(orderId, coffeeOrderOptionService.getOrderedCoffeeOptionWithOrderId(orderId));

        return new ResponseEntity<>(coffeeOrderInfoWithOptions, HttpStatus.OK);
    }

    @PatchMapping("/{order-id}/coffee-orders/{coffee-order-id}") // 커피 수향 변경  // TODO: url 리팩토링 필요
    public String changeQuantity(@PathVariable("order-id") Long orderId,
                                 @PathVariable("coffee-order-id") Long coffeeOrderId,
                                 @RequestParam("quantity") Integer quantity) {

        coffeeOrderService.changeQuantity(coffeeOrderId, quantity);

        return "redirect:/orders/" + orderId;
    }

    @DeleteMapping("/{order-id}/coffee-code/{coffee-code}") // 선택한 커피 삭제
    private String deleteCoffeeOrder(@PathVariable("order-id") Long orderId,
                                   @PathVariable("coffee-code") String coffeeCode) {

        coffeeOrderService.deleteCoffeeOrder(coffeeCode);

        return "redirect:/orders/" + orderId;
    }

}
