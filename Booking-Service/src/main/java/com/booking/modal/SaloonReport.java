package com.booking.modal;


public class SaloonReport {
    private Long saloonId;

    private String saloonName;
    private  Double totalEarnings;
    private Integer totalBookings;

    private Integer cancelBookings;

    private Double totalRefund;

    public SaloonReport() {
    }

    public SaloonReport(Long saloonId, String saloonName, Double totalEarnings, Integer totalBookings, Integer cancelBookings, Double totalRefund) {
        this.saloonId = saloonId;
        this.saloonName = saloonName;
        this.totalEarnings = totalEarnings;
        this.totalBookings = totalBookings;
        this.cancelBookings = cancelBookings;
        this.totalRefund = totalRefund;
    }

    public Long getSaloonId() {
        return saloonId;
    }

    public void setSaloonId(Long saloonId) {
        this.saloonId = saloonId;
    }

    public String getSaloonName() {
        return saloonName;
    }

    public void setSaloonName(String saloonName) {
        this.saloonName = saloonName;
    }

    public Double getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(Double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public Integer getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(Integer totalBookings) {
        this.totalBookings = totalBookings;
    }

    public Integer getCancelBookings() {
        return cancelBookings;
    }

    public void setCancelBookings(Integer cancelBookings) {
        this.cancelBookings = cancelBookings;
    }

    public Double getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(Double totalRefund) {
        this.totalRefund = totalRefund;
    }
}
