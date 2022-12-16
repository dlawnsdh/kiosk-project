package com.shoushoubackenddeveloper.kiosk_project.service;

import com.shoushoubackenddeveloper.kiosk_project.domain.Option;
import com.shoushoubackenddeveloper.kiosk_project.dto.OptionDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.request.OptionRequestDto;
import com.shoushoubackenddeveloper.kiosk_project.repository.OptionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;

    @Transactional(readOnly = true)
    public List<OptionDto> getOptions() {
        return optionRepository.findAll().stream()
                .map(OptionDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public Option getOption(Long optionId) {
        return optionRepository.findById(optionId).get();
    }

    private OptionDto verifyOption(OptionRequestDto dto) {
        return optionRepository.findById(dto.id())
                .map(OptionDto::from)
                .orElseThrow(() -> new EntityNotFoundException("해당 옵샨이 존재하지 않음"));
    }

}
