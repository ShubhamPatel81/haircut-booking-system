package com.payment.service;

import com.payment.domain.PaymentMethod;
import com.payment.modal.PaymentOrder;
import com.payment.payload.PaymentLinkResponse;
import com.payment.payload.dto.BookingDto;
import com.payment.payload.dto.UserDTO;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {

    PaymentLinkResponse createOrder(UserDTO userDto, BookingDto bookingDto , PaymentMethod paymentMethod) throws StripeException;

    PaymentOrder getPaymentOrderById(Long orderId);

    PaymentOrder getPaymentOrderByPaymentId(String paymentId);

    PaymentLink createRazorpayPaymentLink(UserDTO userDTO,Long amount,Long orderId);

    String createStripePaymentLink(UserDTO userDto, Long amount, Long orderId) throws StripeException;

    Boolean proceedPayment(PaymentOrder paymentOrder,String paymentId,String paymentLinkId) throws RazorpayException;
}
