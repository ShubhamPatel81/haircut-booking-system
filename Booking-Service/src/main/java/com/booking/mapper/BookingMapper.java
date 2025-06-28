package com.booking.mapper;

import com.booking.dto.BookingDto;
import com.booking.modal.Booking;

public class BookingMapper {
    public static BookingDto toDto(Booking booking){
        BookingDto bookingDto =new  BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setCustomerId(bookingDto.getCustomerId());
        bookingDto.setStatus(booking.getStatus());
        bookingDto.setEndTime(booking.getEndTime());
        bookingDto.setStartTime(booking.getStartTime());
        bookingDto.setSaloonId(booking.getSaloonId());
        bookingDto.setServiceIds(booking.getServiceIds());
        return bookingDto;
    }

}
