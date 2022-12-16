package com.shoushoubackenddeveloper.kiosk_project.controller;

import com.shoushoubackenddeveloper.kiosk_project.domain.CoffeeOrder;
import com.shoushoubackenddeveloper.kiosk_project.domain.CoffeeOrderOption;
import com.shoushoubackenddeveloper.kiosk_project.domain.Option;
import com.shoushoubackenddeveloper.kiosk_project.service.CoffeeOrderOptionService;
import com.shoushoubackenddeveloper.kiosk_project.service.CoffeeOrderService;
import com.shoushoubackenddeveloper.kiosk_project.service.OptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/coffee-order-options")
@RequiredArgsConstructor
public class CoffeeOrderOptionController {
    private final OptionService optionService;
    private final CoffeeOrderService coffeeOrderService;
    private final CoffeeOrderOptionService coffeeOrderOptionService;

    @GetMapping("/{option-id}/coffee-order-id/{coffee-order-id}") // TODO: url 리팩토링 필요
    public String addCoffeeOption(@PathVariable("option-id") Long optionId,
                                  @PathVariable("coffee-order-id") Long coffeeOrderId,
                                  @CookieValue("orderNo") Integer orderNo,
                                  //@CookieValue("coffeeOrderId") Long coffeeOrderId,
                                  @RequestParam("quantity") Integer quantity) {

        CoffeeOrder coffeeOrder = coffeeOrderService.findOrderedCoffee(coffeeOrderId);
        log.info("CoffeeOrder : {}", coffeeOrder);

        Option option = optionService.getOption(optionId);
        log.info("Option : {}", option);

        CoffeeOrderOption coffeeOrderOption = CoffeeOrderOption.of(option, coffeeOrder, quantity);

        coffeeOrderOptionService.addOption(coffeeOrderOption);

        return "redirect:/orders/" + orderNo;
    }

}
