package com.booking.service.impl;

import com.booking.Repository.BookingRepository;
import com.booking.domain.BookingStatus;
import com.booking.dto.BookingRequest;
import com.booking.dto.SaloonDto;
import com.booking.dto.ServiceDto;
import com.booking.dto.UserDTO;
import com.booking.modal.Booking;
import com.booking.modal.SaloonReport;
import com.booking.service.BookingService;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
 public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;

    public BookingServiceImpl(BookingRepository repository) {
        this.repository = repository;
    }


    @Override
    public Booking createBooking(BookingRequest booking,
                                 UserDTO userDTO,
                                 SaloonDto saloonDto,
                                 Set<ServiceDto> serviceDto) throws Exception {

        int totalDuration= serviceDto.stream().
                mapToInt(ServiceDto::getDuration)
                .sum();
        LocalDateTime bookingStartTime = booking.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);

        // checking weather the slot available or not for booking (isTimeSlotAvailable)
        Boolean isSlotAvailable = isTimeSlotAvailable(saloonDto,bookingStartTime,bookingEndTime);

        int totalPrice = serviceDto.stream()
                .mapToInt(ServiceDto::getPrice)
                .sum();

        Set<Long> idList = serviceDto.stream()
                .map(ServiceDto::getId).collect(Collectors.toSet());

        Booking newBooking = new Booking();
        newBooking.setCustomerId(userDTO.getId());
        newBooking.setSaloonId(saloonDto.getId());
        newBooking.setServiceIds(idList);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setStartTime(bookingStartTime);
        newBooking.setEndTime(bookingEndTime);
        newBooking.setTotalPrice(totalPrice);
        return repository.save(newBooking);

    }
    public  Boolean isTimeSlotAvailable(SaloonDto saloonDto,
                                       LocalDateTime bookingStartTime,
                                       LocalDateTime bookingEndTime) throws Exception {

        List<Booking> existingBookings = getBookingBySaloon(saloonDto.getId());

        LocalDateTime saloonOpenTime = saloonDto.getOpenTime().atDate(bookingStartTime.toLocalDate());
       LocalDateTime saloonCloseTime= saloonDto.getCloseTime().atDate(bookingEndTime.toLocalDate());
       if (bookingStartTime.isBefore(saloonOpenTime)|| bookingEndTime.isAfter(saloonCloseTime)){
           throw new Exception("Booking Time must be within saloon working Hours 1");
       }

       for (Booking existingBooking: existingBookings){
           LocalDateTime existingBookingStartTime = existingBooking.getStartTime();
           LocalDateTime existingBookingEndTime = existingBooking.getEndTime();

           if (bookingStartTime.isBefore(existingBookingEndTime) && bookingEndTime.isAfter(existingBookingStartTime)){
               throw new Exception("Booking Slot  is Overlapping. Not Available choose different time 2");
           }
           if (bookingStartTime.isEqual(existingBookingStartTime) || bookingEndTime.isEqual(existingBookingEndTime)){
               throw new Exception("Booking Slot  is Overlapping. Not Available choose different time 3");
           }
       }
        return true;
    }

    @Override
    public List<Booking> getBookingsByCustomer(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingBySaloon(Long saloonId) {
        return repository.findBySaloonId(saloonId);
    }

    @Override
    public Booking getBookingById(Long id) throws Exception {
        Booking booking = repository.findById(id).orElse(null);
        if (booking ==null){
            throw new Exception("Booking Bot Found 4");
        }
        return booking;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) throws Exception {
        Booking booking = getBookingById(bookingId);
        booking.setStatus(status );

        return repository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date, Long saloonId) {
        List<Booking> allBookings = getBookingBySaloon(saloonId);
        if (date == null){
            return allBookings;
        }
      return   allBookings.stream()
                .filter(booking -> isSameDate(booking.getStartTime(),date)||
                        isSameDate(booking.getEndTime(),date)).collect(Collectors.toList());
    }
    private boolean isSameDate(LocalDateTime dateTime, LocalDate date) {
         return  dateTime.toLocalDate().isEqual(date);

    }


    @Override
    public SaloonReport getSaloonReport(Long saloonId) {
        List<Booking> bookings = getBookingBySaloon(saloonId);
        Double totalEarning = bookings.stream().
                mapToDouble(Booking::getTotalPrice)
                .sum();

        Integer totalBooking = bookings.size();
        List<Booking> cancelBooking = bookings.stream().
                filter(booking -> booking.getStatus().equals(BookingStatus.CANCELLED))
                .collect(Collectors.toList());

        Double totalRefund = cancelBooking.stream()
                .mapToDouble(Booking::getTotalPrice)
                .sum();

        SaloonReport report =new SaloonReport();
        report.setSaloonId(saloonId);
//        report.setSaloonName();
        report.setCancelBookings(cancelBooking.size());
        report.setTotalBookings(totalBooking);
        report.setTotalEarnings(totalEarning);
        report.setTotalRefund(totalRefund);
//        report.getTotalBookings();
        return report;
    }


}

