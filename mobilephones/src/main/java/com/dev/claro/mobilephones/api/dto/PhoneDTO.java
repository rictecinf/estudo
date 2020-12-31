package com.dev.claro.mobilephones.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class PhoneDTO {

    private String code;
    @NotBlank(message = "Model is mandatory")
    private String model;
    @Positive(message = "Price need to be greater than zero")
    private int price;
    @NotBlank(message = "Brand is mandatory")
    private String brand;
    @NotBlank(message = "Photo is mandatory")
    private String photo;
    @NotBlank(message = "Date is mandatory")
    private String date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
