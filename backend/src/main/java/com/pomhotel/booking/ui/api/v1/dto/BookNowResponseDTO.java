package com.pomhotel.booking.ui.api.v1.dto;

public class BookNowResponseDTO {
    public boolean bookNowResult;
    public String bookLink;
    public long bookingId;

    public BookNowResponseDTO() {
    }

    public boolean isBookNowResult() {
        return bookNowResult;
    }

    public void setBookNowResult(boolean bookNowResult) {
        this.bookNowResult = bookNowResult;
    }

    public String getBookLink() {
        return bookLink;
    }

    public void setBookLink(String bookLink) {
        this.bookLink = bookLink;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }
}
