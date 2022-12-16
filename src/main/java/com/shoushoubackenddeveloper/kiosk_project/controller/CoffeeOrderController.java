package com.shoushoubackenddeveloper.kiosk_project.controller;

import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeOrderDto;
import com.shoushoubackenddeveloper.kiosk_project.service.CoffeeOrderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/coffee-orders")
@RequiredArgsConstructor
public class CoffeeOrderController {
    private final CoffeeOrderService coffeeOrderService;

    @GetMapping // 선택한 커피 추가
    public String addCoffeeOrder(@CookieValue("orderNo") Integer orderNo,
                                 @CookieValue("coffeeCode") String coffeeCode,
                                 HttpServletResponse response) {

        CoffeeOrderDto dto = coffeeOrderService.addCoffee(orderNo, coffeeCode);

        Cookie cookie = new Cookie("coffeeOrderId", String.valueOf(dto.id()));
        cookie.setDomain("localhost");
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/options"; // 커피 선택하면 옵션 선택 창으로 리다이렉트
    }

}
