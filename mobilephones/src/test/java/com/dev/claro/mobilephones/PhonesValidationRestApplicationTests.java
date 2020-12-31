package com.dev.claro.mobilephones;

import com.dev.claro.mobilephones.domain.model.Phone;
import com.dev.claro.mobilephones.domain.repository.PhoneRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class PhonesValidationRestApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PhoneRepository phoneRepository;

    @Test
    @Order(1)
    void registerPhoneSuccessTest() throws Exception  {
        Phone phone = new Phone();
        phone.setCode("f7212f18-f90f-11ea-adc1-0242ac120002");
        phone.setBrand("Apple");
        phone.setModel("Iphone");
        phone.setPrice(3000);
        phone.setDate(LocalDateTime.now().toString());
        phone.setPhoto("url_photo");

        mockMvc.perform(post("/claro/mobile")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("admin:admin".getBytes()))
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(phone)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void registerPhoneErrorTest() throws Exception  {
        Phone phone = new Phone();
        phone.setCode("f7212f18-f90f-11ea-adc1-0242ac120004");
        phone.setBrand("Motorola");
        phone.setPrice(1000);
        phone.setDate(LocalDateTime.now().toString());
        phone.setPhoto("url_photo");

        mockMvc.perform(post("/claro/mobile")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("admin:admin".getBytes()))
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(phone)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(3)
    void getPhoneByCodeTest() throws Exception  {
        mockMvc.perform(get("/claro/mobile/{code}", "f7212f18-f90f-11ea-adc1-0242ac120002")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("admin:admin".getBytes()))

        )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void getPhoneByCodeNotFoundTest() throws Exception {
        mockMvc.perform(get("/claro/mobile/{code}", "f90f-11ea-adc1")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("admin:admin".getBytes()))
        )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(5)
    void getAllPhonesTest() throws Exception {
        mockMvc.perform(get("/claro/mobile")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("admin:admin".getBytes())))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @AfterAll
    void removePhoneByCodeTest() {
        phoneRepository.deleteById("f7212f18-f90f-11ea-adc1-0242ac120002");
    }

}
