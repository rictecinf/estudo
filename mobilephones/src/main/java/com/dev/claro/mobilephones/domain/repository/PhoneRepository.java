package com.dev.claro.mobilephones.domain.repository;

import com.dev.claro.mobilephones.domain.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, String> {
}
