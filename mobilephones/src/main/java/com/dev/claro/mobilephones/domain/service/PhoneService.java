package com.dev.claro.mobilephones.domain.service;

import com.dev.claro.mobilephones.domain.model.Phone;
import com.dev.claro.mobilephones.domain.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Transactional
    public Phone save(Phone phone) {
        if(validPhone(phone)) {
            if (phone.getCode() == null) {
                phone.setCode(UUID.randomUUID().toString());
            }
            return phoneRepository.save(phone);
        }
        return null;
    }

    public Phone findPhone(String code) {
        Optional<Phone> phone = phoneRepository.findById(code);
        return phone.orElse(null);
    }

    public List<Phone> listAll() {
        return phoneRepository.findAll();
    }

    private boolean validPhone(Phone phone) {
        return phone.getBrand() != null && phone.getDate() != null && phone.getModel() != null && (phone.getPhoto() != null && phone.getPhoto().length() <= 250);
    }

}
