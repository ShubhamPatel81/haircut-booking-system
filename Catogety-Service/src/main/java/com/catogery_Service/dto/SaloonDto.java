package com.catogery_Service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@ToString
public class SaloonDto {

    private Long id;
    private String name;
    private List<String > images;
    private  String address;
    private String phoneNumber;
    private String email;
    private String city;
    private Long ownerId;
    private LocalTime openTime;
    private LocalTime closeTime;
    private UserDTO owner;

    public SaloonDto() {
    }

    public SaloonDto(Long id, String name, List<String> images, String address, String phoneNumber, String email, String city, Long ownerId, LocalTime openTime, LocalTime closeTime, UserDTO owner) {
        this.id = id;
        this.name = name;
        this.images = images;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.ownerId = ownerId;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }
}
