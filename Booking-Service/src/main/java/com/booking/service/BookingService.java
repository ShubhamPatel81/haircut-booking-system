package com.booking.service;

import com.booking.domain.BookingStatus;
import com.booking.dto.BookingRequest;
import com.booking.dto.SaloonDto;
import com.booking.dto.ServiceDto;
import com.booking.dto.UserDTO;
import com.booking.modal.Booking;
import com.booking.modal.SaloonReport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface BookingService {

    Booking createBooking(BookingRequest booking,
                          UserDTO userDTO,
                          SaloonDto saloonDto,
                          Set<ServiceDto> serviceDto) throws Exception;

    List<Booking> getBookingsByCustomer(Long customerId);

    List<Booking> getBookingBySaloon(Long saloonId);

    Booking getBookingById(Long id) throws Exception;

    Booking updateBooking(Long bookingId, BookingStatus status) throws Exception;

    List<Booking> getBookingsByDate(LocalDate date, Long saloonId);

    SaloonReport getSaloonReport(Long saloonId);
}
