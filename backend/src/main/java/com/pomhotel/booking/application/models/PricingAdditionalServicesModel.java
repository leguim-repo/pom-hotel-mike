package com.pomhotel.booking.application.models;

public class PricingAdditionalServicesModel {

    public Integer numberOfNightsForLongStay;
    public double roomPercentDiscountInPricePerNightLongStay;

    public double breakFastPricePerNight;

    public double carParkingPricePerNight;

    public double spaPricePerNight;

    public double laundryPricePerNight;

    public double shuttlePricePerNight;


    public PricingAdditionalServicesModel() {
        this.numberOfNightsForLongStay = 20;
        this.roomPercentDiscountInPricePerNightLongStay = 0.20;
        this.breakFastPricePerNight = 4;
        this.carParkingPricePerNight = 10;
        this.spaPricePerNight = 5;
        this.laundryPricePerNight = 2;
        this.shuttlePricePerNight = 20;
    }
}
