package com.shoushoubackenddeveloper.kiosk_project.controller;

import com.shoushoubackenddeveloper.kiosk_project.domain.Coffee;
import com.shoushoubackenddeveloper.kiosk_project.service.CoffeeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/coffees")
@RequiredArgsConstructor
public class CoffeeController {
    private final CoffeeService coffeeService;

    @GetMapping
    public String getCoffees(ModelMap map) {
        List<Coffee> coffees = coffeeService.getCoffeeList();

        map.addAttribute("coffees", coffees);

        return "coffees";
    }

    @GetMapping("/{coffee-code}")
    public String findCoffee(@PathVariable("coffee-code") String coffeeCode,
                             HttpServletResponse response) {

        coffeeService.checkCoffee(coffeeCode); // 존재하는 커피인지 체크

        Cookie cookie = new Cookie("coffeeCode", coffeeCode);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/coffee-orders";
    }

}
