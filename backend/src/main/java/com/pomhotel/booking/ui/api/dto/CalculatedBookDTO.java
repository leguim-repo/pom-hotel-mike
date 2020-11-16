package com.pomhotel.booking.ui.api.dto;

public class CalculatedBookDTO {

    public long totalNights;
    public boolean longStay;
    public double roomPricePerNight;
    public double roomTotalPrice;

    public double roomSpecialPricePerNight;
    public double roomSpecialTotalPrice;

    public double breakFastPricePerNight;
    public double breakFastTotalPrice;

    public double carParkingPricePerNight;
    public double carParkingTotalPrice;

    public double spaPricePerNight;
    public double spaTotalPrice;

    public double laundryPricePerNight;
    public double laundryTotalPrice;

    public double shuttlePricePerNight;
    public double shuttleTotalPrice;

    public double codeDiscountPrice;
    public double totalBookingPrice;

    public CalculatedBookDTO() {
    }

    public boolean isLongStay() {
        return longStay;
    }

    public double getRoomSpecialPricePerNight() {
        return roomSpecialPricePerNight;
    }

    public void setRoomSpecialPricePerNight(double roomSpecialPricePerNight) {
        this.roomSpecialPricePerNight = roomSpecialPricePerNight;
    }

    public double getRoomSpecialTotalPrice() {
        return roomSpecialTotalPrice;
    }

    public void setRoomSpecialTotalPrice(double roomSpecialTotalPrice) {
        this.roomSpecialTotalPrice = roomSpecialTotalPrice;
    }

    public void setLongStay(boolean longStay) {
        this.longStay = longStay;
    }

    public double getRoomTotalPrice() {
        return roomTotalPrice;
    }

    public void setRoomTotalPrice(double roomTotalPrice) {
        this.roomTotalPrice = roomTotalPrice;
    }

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

    public double getCarParkingPricePerNight() {
        return carParkingPricePerNight;
    }

    public void setCarParkingPricePerNight(double carParkingPricePerNight) {
        this.carParkingPricePerNight = carParkingPricePerNight;
    }

    public double getCarParkingTotalPrice() {
        return carParkingTotalPrice;
    }

    public void setCarParkingTotalPrice(double carParkingTotalPrice) {
        this.carParkingTotalPrice = carParkingTotalPrice;
    }

    public double getSpaPricePerNight() {
        return spaPricePerNight;
    }

    public void setSpaPricePerNight(double spaPricePerNight) {
        this.spaPricePerNight = spaPricePerNight;
    }

    public double getSpaTotalPrice() {
        return spaTotalPrice;
    }

    public void setSpaTotalPrice(double spaTotalPrice) {
        this.spaTotalPrice = spaTotalPrice;
    }

    public double getLaundryPricePerNight() {
        return laundryPricePerNight;
    }

    public void setLaundryPricePerNight(double laundryPricePerNight) {
        this.laundryPricePerNight = laundryPricePerNight;
    }

    public double getLaundryTotalPrice() {
        return laundryTotalPrice;
    }

    public void setLaundryTotalPrice(double laundryTotalPrice) {
        this.laundryTotalPrice = laundryTotalPrice;
    }

    public double getShuttlePricePerNight() {
        return shuttlePricePerNight;
    }

    public void setShuttlePricePerNight(double shuttlePricePerNight) {
        this.shuttlePricePerNight = shuttlePricePerNight;
    }

    public double getShuttleTotalPrice() {
        return shuttleTotalPrice;
    }

    public void setShuttleTotalPrice(double shuttleTotalPrice) {
        this.shuttleTotalPrice = shuttleTotalPrice;
    }

    public double getCodeDiscountPrice() {
        return codeDiscountPrice;
    }

    public void setCodeDiscountPrice(double codeDiscountPrice) {
        this.codeDiscountPrice = codeDiscountPrice;
    }

    public double getTotalBookingPrice() {
        return totalBookingPrice;
    }

    public void setTotalBookingPrice(double totalBookingPrice) {
        this.totalBookingPrice = totalBookingPrice;
    }


    @Override
    public String toString() {
        return "CalculatedBookDTO{" +
                "totalNights=" + totalNights +
                ", roomPricePerNight=" + roomPricePerNight +
                ", roomTotalPrice=" + roomTotalPrice +
                ", breakFastPricePerNight=" + breakFastPricePerNight +
                ", breakFastTotalPrice=" + breakFastTotalPrice +
                ", carParkingPricePerNight=" + carParkingPricePerNight +
                ", carParkingTotalPrice=" + carParkingTotalPrice +
                ", spaPricePerNight=" + spaPricePerNight +
                ", spaTotalPrice=" + spaTotalPrice +
                ", laundryPricePerNight=" + laundryPricePerNight +
                ", laundryTotalPrice=" + laundryTotalPrice +
                ", shuttlePricePerNight=" + shuttlePricePerNight +
                ", shuttleTotalPrice=" + shuttleTotalPrice +
                ", codeDiscountPrice=" + codeDiscountPrice +
                ", totalBookingPrice=" + totalBookingPrice +
                '}';
    }
}
