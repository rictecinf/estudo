package com.dev.claro.mobilephones.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "tb_phones")
public class Phone {

    @Id
    private String code;
    private String model;
    private int price;
    private String brand;
    private String photo;
    private String date;

    public Phone(String code, String model, int price, String brand, String photo, String date) {
        this.code = code;
        this.model = model;
        this.price = price;
        this.brand = brand;
        this.photo = photo;
        this.date = date;
    }

    public Phone() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return code.equals(phone.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
