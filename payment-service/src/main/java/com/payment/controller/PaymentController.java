package com.payment.controller;

import com.payment.domain.PaymentMethod;
import com.payment.modal.PaymentOrder;
import com.payment.payload.PaymentLinkResponse;
import com.payment.payload.dto.BookingDto;
import com.payment.payload.dto.UserDTO;
import com.payment.service.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @RequestBody BookingDto bookingDto,
            @RequestParam PaymentMethod paymentMethod
            ) throws StripeException {

        UserDTO userDto = new UserDTO();
        userDto.setFullName("Hello");
        userDto.setEmail("example@example.com");
        userDto.setId(1L);

        PaymentLinkResponse  res = paymentService.createOrder(userDto,bookingDto, paymentMethod);
        
        return ResponseEntity.ok(res);
    }

   @GetMapping("/{paymentOrderId}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById(@PathVariable Long paymentOrderId){
        PaymentOrder res = paymentService.getPaymentOrderById(paymentOrderId);
        return ResponseEntity.ok(res);
   }

   @PatchMapping("/proceed")
    public ResponseEntity<Boolean> proceedPayment(
         @RequestParam String paymentId,
         @RequestParam String paymentLinkId
         ) throws RazorpayException {
        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentLinkId);

        Boolean res = paymentService.proceedPayment(paymentOrder, paymentId,paymentLinkId);
        return ResponseEntity.ok(res);
   }
}
