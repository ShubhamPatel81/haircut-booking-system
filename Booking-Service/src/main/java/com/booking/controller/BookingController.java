package com.booking.controller;


import com.booking.domain.BookingStatus;
import com.booking.dto.*;
import com.booking.mapper.BookingMapper;
import com.booking.modal.Booking;
import com.booking.modal.SaloonReport;
import com.booking.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

   private final   BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //create booking
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestParam Long saloonId,@RequestBody BookingRequest BookingRequest
    ) throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        SaloonDto saloonDto = new SaloonDto();
        saloonDto.setId(saloonId);
        saloonDto.setOpenTime(LocalTime.now());
        saloonDto.setCloseTime(LocalTime.now().plusHours(5));

        Set<ServiceDto> serviceDtoSet= new HashSet<>();

        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setId(1L);

        serviceDto.setPrice(200);
        serviceDto.setDuration(35);
        serviceDto.setServiceName("Hair cut for man ");

        serviceDtoSet.add(serviceDto);

        Booking booking = bookingService.createBooking(BookingRequest, userDTO, saloonDto, serviceDtoSet);

        return ResponseEntity.ok(booking);
    }


    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDto>> getBooingByCustomer( ){

        List<Booking> bookings= bookingService.getBookingsByCustomer(1L);

        return ResponseEntity.ok(getBookingDtos(bookings));

    }

    private Set<BookingDto> getBookingDtos(List<Booking> bookings){
        return bookings.stream()
                .map(booking -> {
                    return BookingMapper.toDto(booking);
                }).collect(Collectors.toSet());
    }

    @GetMapping("/saloon")
    public ResponseEntity<Set<BookingDto>> getBooingBySaloon( ){

        List<Booking> bookings= bookingService.getBookingBySaloon(1L);

        return ResponseEntity.ok(getBookingDtos(bookings));

    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDto> getBooingById(@PathVariable Long bookingId ) throws Exception {

        Booking booking= bookingService.getBookingById(bookingId);

        return ResponseEntity.ok(BookingMapper.toDto(booking));

    }

    @PatchMapping("/{bookingId}/status")
    public ResponseEntity<BookingDto> updateBookingStatus(
            @PathVariable Long bookingId, @RequestParam BookingStatus status
            ) throws Exception {
        Booking booking = bookingService.updateBooking(bookingId,status);
        return  ResponseEntity.ok(BookingMapper.toDto(booking));
    }
    @GetMapping("/slots/saloon/{saloonId}/date/{date}")
    public ResponseEntity<List<BookingSlotDto>> getBookedSlot(
            @PathVariable Long saloonId,
            @RequestParam(required = false) LocalDate date
            ) throws Exception {
       List<Booking> booking = bookingService.getBookingsByDate(date, saloonId);

       List<BookingSlotDto> slotDtos = booking.stream()
               .map(booking1 -> {
                   BookingSlotDto bookingSlotDto = new BookingSlotDto();
                   bookingSlotDto.setStartTime(booking1.getStartTime());
                   bookingSlotDto.setEndTime(booking1.getEndTime());
                   return bookingSlotDto;
               }).collect(Collectors.toList());

       return  ResponseEntity.ok(slotDtos);
    }

    @GetMapping("/reports")
    public ResponseEntity<SaloonReport> getSaloonReport() throws Exception {
       SaloonReport report = bookingService.getSaloonReport(1L);
       return ResponseEntity.ok(report);

    }
    
    



}
