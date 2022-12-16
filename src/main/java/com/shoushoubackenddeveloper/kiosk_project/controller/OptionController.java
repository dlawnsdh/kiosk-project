package com.shoushoubackenddeveloper.kiosk_project.controller;

import com.shoushoubackenddeveloper.kiosk_project.dto.OptionDto;
import com.shoushoubackenddeveloper.kiosk_project.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/options")
@RequiredArgsConstructor
public class OptionController {
    private final OptionService optionService;

    @GetMapping
    public ResponseEntity getOptions() {
        List<OptionDto> dto = optionService.getOptions();

        return new ResponseEntity(dto, HttpStatus.OK);
    }

}
