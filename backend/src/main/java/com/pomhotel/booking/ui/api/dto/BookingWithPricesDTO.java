package com.pomhotel.booking.ui.api.dto;


import com.pomhotel.booking.application.models.BookingsModel;

public class BookingWithPricesDTO {
    public BookingsModel book;
    public CalculatedBookDTO prices;


    public BookingWithPricesDTO() {
    }

    public BookingsModel getBook() {
        return book;
    }

    public void setBook(BookingsModel book) {
        this.book = book;
    }

    public CalculatedBookDTO getPrices() {
        return prices;
    }

    public void setPrices(CalculatedBookDTO prices) {
        this.prices = prices;
    }
}
