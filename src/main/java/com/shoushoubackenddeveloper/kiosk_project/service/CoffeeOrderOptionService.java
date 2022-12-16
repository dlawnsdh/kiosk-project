package com.shoushoubackenddeveloper.kiosk_project.service;

import com.shoushoubackenddeveloper.kiosk_project.domain.CoffeeOrder;
import com.shoushoubackenddeveloper.kiosk_project.domain.CoffeeOrderOption;
import com.shoushoubackenddeveloper.kiosk_project.dto.response.CoffeeResponseDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.response.OptionResponseDto;
import com.shoushoubackenddeveloper.kiosk_project.repository.CoffeeOrderOptionRepository;
import com.shoushoubackenddeveloper.kiosk_project.repository.CoffeeOrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CoffeeOrderOptionService {
    private final CoffeeOrderRepository coffeeOrderRepository;
    private final CoffeeOrderOptionRepository repository;

    public void addOption(CoffeeOrderOption entity) {
        repository.save(entity);
    }

    @Transactional(readOnly = true)
    public Map<Map<CoffeeResponseDto, List<OptionResponseDto>>, Integer> getOrderedCoffeeOptionWithOrderId(Long orderId) {
        Map<Map<CoffeeResponseDto, List<OptionResponseDto>>, Integer> orderCoffeesInfoWithOptions = new HashMap<>();

        List<CoffeeOrder> coffeeOrderList = coffeeOrderRepository.findAllByOrders_Id(orderId);
        for (CoffeeOrder coffeeOrder : coffeeOrderList) {
            log.info("CoffeeOrder : {}", coffeeOrder.getCoffee().getKorName());

            Map<CoffeeResponseDto, List<OptionResponseDto>> map = new HashMap<>();
            CoffeeResponseDto dto = CoffeeResponseDto.from(coffeeOrder.getCoffee());
            List<OptionResponseDto> list = getOptionDtoList(coffeeOrder.getId());
            map.put(dto, list);
            orderCoffeesInfoWithOptions.put(map, coffeeOrder.getQuantity());
        }

        return orderCoffeesInfoWithOptions;
    }

    private List<OptionResponseDto> getOptionDtoList(Long coffeeOrderId) {
        List<CoffeeOrderOption> list = repository.findAllByCoffeeOrder_Id(coffeeOrderId);
        return list.stream()
                .map(OptionResponseDto::from)
                .toList();
    }

}
