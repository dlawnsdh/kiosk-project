package com.shoushoubackenddeveloper.kiosk_project.service;

import com.shoushoubackenddeveloper.kiosk_project.domain.Coffee;
import com.shoushoubackenddeveloper.kiosk_project.repository.CoffeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    @Transactional(readOnly = true)
    public List<Coffee> getCoffeeList() {
        return coffeeRepository.findAll();
    }

    public void checkCoffee(String coffeeCode) {
        findVerifiedCoffee(coffeeCode);
    }

    @Transactional(readOnly = true)
    public Coffee findCoffee(String coffeeCode) {
        return coffeeRepository.findByCoffeeCode(coffeeCode).get();
    }

    public Coffee findCoffee(Long id) { return coffeeRepository.getReferenceById(id); }

    private Coffee findVerifiedCoffee(String coffeeCode) {
        return coffeeRepository.findByCoffeeCode(coffeeCode)
                .orElseThrow(() -> new EntityNotFoundException("해당 커피 코드가 존재하지 않음"));
    }

}
