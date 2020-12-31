package com.dev.claro.mobilephones.api.controller;

import com.dev.claro.mobilephones.api.dto.PhoneDTO;
import com.dev.claro.mobilephones.api.modelmappers.PhoneMapper;
import com.dev.claro.mobilephones.domain.model.Phone;
import com.dev.claro.mobilephones.domain.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/claro/mobile")
public class PhoneController {

    @Autowired
    private PhoneMapper phoneMapper;

    @Autowired
    private PhoneService phoneService;

    @PostMapping
    public ResponseEntity<PhoneDTO> register(@RequestBody @Valid PhoneDTO phoneDTO) {

        Phone phone = phoneMapper.toDomain(phoneDTO);
        phone = phoneService.save(phone);

        if(phone == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().body(phoneMapper.toResponse(phone));
    }

    @GetMapping("/{code}")
    public ResponseEntity<PhoneDTO> findByCode(@PathVariable String code) {
        Phone phone = phoneService.findPhone(code);
        if(phone != null)
            return ResponseEntity.ok().body(phoneMapper.toResponse(phone));

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PhoneDTO>> listAll() {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(120, TimeUnit.SECONDS))
                .body(phoneMapper.toCollectionModel(phoneService.listAll()));
    }

}
