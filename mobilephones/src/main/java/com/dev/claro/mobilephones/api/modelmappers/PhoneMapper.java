package com.dev.claro.mobilephones.api.modelmappers;

import com.dev.claro.mobilephones.api.dto.PhoneDTO;
import com.dev.claro.mobilephones.domain.model.Phone;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhoneMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Phone toDomain(PhoneDTO phoneDTO) {
        return modelMapper.map(phoneDTO, Phone.class);
    }

    public PhoneDTO toResponse(Phone phone) {
        return modelMapper.map(phone, PhoneDTO.class);
    }

    public List<PhoneDTO> toCollectionModel(List<Phone> phones) {
        return phones.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
