package com.pomhotel.booking.ui.api.dto;

public class CalculatedBookDTO {

    public long totalNights;
    public double roomPricePerNight;
    public double breakFastPricePerNight;
    public double breakFastTotalPrice;

    public double carParkingPrice;
    public double spaPrice;
    public double laundryPrice;
    public double shuttlePrice;
    public double codeDiscountPrice;
    public double totalPrice;


    public long getTotalNights() {
        return totalNights;
    }

    public void setTotalNights(long totalNights) {
        this.totalNights = totalNights;
    }

    public double getRoomPricePerNight() {
        return roomPricePerNight;
    }

    public void setRoomPricePerNight(double roomPricePerNight) {
        this.roomPricePerNight = roomPricePerNight;
    }

    public double getBreakFastPricePerNight() {
        return breakFastPricePerNight;
    }

    public void setBreakFastPricePerNight(double breakFastPricePerNight) {
        this.breakFastPricePerNight = breakFastPricePerNight;
    }

    public double getBreakFastTotalPrice() {
        return breakFastTotalPrice;
    }

    public void setBreakFastTotalPrice(double breakFastTotalPrice) {
        this.breakFastTotalPrice = breakFastTotalPrice;
    }

    public double getCarParkingPrice() {
        return carParkingPrice;
    }

    public void setCarParkingPrice(double carParkingPrice) {
        this.carParkingPrice = carParkingPrice;
    }

    public double getSpaPrice() {
        return spaPrice;
    }

    public void setSpaPrice(double spaPrice) {
        this.spaPrice = spaPrice;
    }

    public double getLaundryPrice() {
        return laundryPrice;
    }

    public void setLaundryPrice(double laundryPrice) {
        this.laundryPrice = laundryPrice;
    }

    public double getShuttlePrice() {
        return shuttlePrice;
    }

    public void setShuttlePrice(double shuttlePrice) {
        this.shuttlePrice = shuttlePrice;
    }

    public double getCodeDiscountPrice() {
        return codeDiscountPrice;
    }

    public void setCodeDiscountPrice(double codeDiscountPrice) {
        this.codeDiscountPrice = codeDiscountPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CalculatedBookDTO{" +
                "totalNights=" + totalNights +
                ", roomPricePerNight=" + roomPricePerNight +
                ", breakFastPricePerNight=" + breakFastPricePerNight +
                ", breakFastTotalPrice=" + breakFastTotalPrice +
                ", carParkingPrice=" + carParkingPrice +
                ", spaPrice=" + spaPrice +
                ", laundryPrice=" + laundryPrice +
                ", shuttlePrice=" + shuttlePrice +
                ", codeDiscountPrice=" + codeDiscountPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
