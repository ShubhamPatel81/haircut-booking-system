package com.booking.modal;


import com.booking.domain.BookingStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;


@Entity
@Table(name = "Booking_table")
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private Long saloonId;

    private Long customerId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ElementCollection
    private Set<Long> serviceIds;

    private BookingStatus status =BookingStatus.PENDING;

    private int totalPrice;

    public Booking() {
    }

    public Booking(Long id, Long saloonId, Long customerId, LocalDateTime startTime, LocalDateTime endTime, Set<Long> serviceIds, BookingStatus status, int totalPrice) {
        this.id = id;
        this.saloonId = saloonId;
        this.customerId = customerId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.serviceIds = serviceIds;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSaloonId() {
        return saloonId;
    }

    public void setSaloonId(Long saloonId) {
        this.saloonId = saloonId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Set<Long> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(Set<Long> serviceIds) {
        this.serviceIds = serviceIds;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
