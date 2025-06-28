package com.payment.service.Impl;

import com.payment.PaymentReposotory.PaymentRepo;
import com.payment.domain.PaymentMethod;
import com.payment.domain.PaymentOrderStatus;
import com.payment.modal.PaymentOrder;
import com.payment.payload.PaymentLinkResponse;
import com.payment.payload.dto.BookingDto;
import com.payment.payload.dto.UserDTO;
import com.payment.service.PaymentService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import netscape.javascript.JSObject;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("{$stripe.api.key")
    private final String stripeApiKey = "your_stripe_api_key";

    @Value("{$razorpay.api.key}")
    private final String razorpayApiKey = "your_razorpay_api_key";

    @Value("{$razorpay.api.secret}")
    private final String razorpayApiSecret = "your_razorpay_api_secret";

    @Value("{$stripe.api.secret}")
    private final String stripeApiSecret = "your_stripe_api_secret";

    private final PaymentRepo paymentRepo;
    public PaymentServiceImpl(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    @Override
    public PaymentLinkResponse createOrder(UserDTO userDto,
                                           BookingDto bookingDto,
                                           PaymentMethod paymentMethod) throws StripeException {

        Long amount = (long) bookingDto.getTotalPrice() ; // Convert to cents
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setAmount(amount);
        paymentOrder.setPaymentMethod(paymentMethod);
        paymentOrder.setBookingId(bookingDto.getId());
        paymentOrder.setSaloonId(bookingDto.getSaloonId());

        PaymentOrder savedOrder = paymentRepo.save(paymentOrder);

        PaymentLinkResponse response = new PaymentLinkResponse();

        if (paymentMethod == PaymentMethod.RAZORPAY) {
            PaymentLink paymentLink = createRazorpayPaymentLink(userDto, savedOrder.getAmount(), savedOrder.getId());
            String paymentLinkUrl = paymentLink.get("sort_url");
            String paymentId = paymentLink.get("id");
            response.setPaymentLinkUrl(paymentLinkUrl);
            response.setPaymentLinkId(paymentId);

            savedOrder.setPaymentLinkId(paymentId);

            paymentRepo.save(savedOrder);
        }else {
            String paymentLinkUrl = createStripePaymentLink(userDto, savedOrder.getAmount(), savedOrder.getId());
            response.setPaymentLinkUrl(paymentLinkUrl);


        }
        return response;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long orderId) {
        PaymentOrder order = paymentRepo.findById(orderId).orElse(null);
        if (order ==null){
            throw new RuntimeException("PaymentOrder not found");
        }

        return order;
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {


        return paymentRepo.findByPaymentLinkId(paymentId);
    }

    @Override
    public PaymentLink createRazorpayPaymentLink(UserDTO userDTO, Long Amount, Long orderId) {

        Long amount = Amount * 100;// Convert to paisa
        try {
            RazorpayClient razorPay = new RazorpayClient(razorpayApiKey, razorpayApiSecret);
            JSONObject paymentLinkRequest = new JSONObject();
             paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("currency", "INR");

            JSONObject customer = new JSONObject();
            customer.put("name", userDTO.getFullName());
            customer.put("email", userDTO.getEmail());


            JSONObject notify = new JSONObject();
            notify.put("url", true);
            paymentLinkRequest.put("notify", notify);


            paymentLinkRequest.put("reminder_enable",true);
            paymentLinkRequest.put("calback_url", "http://localhost:8080/payment/callback/"+orderId);
            paymentLinkRequest.put("callback_method", "get");

            PaymentLink paymentLink = razorPay.paymentLink.create(paymentLinkRequest);
            return paymentLink;
        }catch (Exception e) {
            throw new RuntimeException("Failed to create payment link", e);
        }
    }

    @Override
    public String createStripePaymentLink(UserDTO userDto, Long amount, Long orderId) throws StripeException {

        Stripe.apiKey= stripeApiKey;

        SessionCreateParams sessionParams = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8080/payment/success/"+orderId)
                .setCancelUrl("http://localhost:8080/payment/cancel/"+orderId)
                .addLineItem(SessionCreateParams
                        .LineItem
                        .builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams
                                .LineItem
                                .PriceData
                                .builder()
                                .setCurrency("USD")
                                .setUnitAmount(amount*100)
                                .setProductData(SessionCreateParams
                                        .LineItem
                                        .PriceData
                                        .ProductData
                                        .builder()
                                        .setName("Payment for Booking")
                                        .build())
                                .build())
                        .build())
                .build();

      Session session = Session.create(sessionParams);

        return session.getUrl();
    }

    @Override
    public Boolean proceedPayment(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException {

        if (paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)) {
            if (paymentOrder.getPaymentMethod().equals(PaymentMethod.RAZORPAY) && paymentId!= null) {
                RazorpayClient razorPay = new RazorpayClient(razorpayApiKey, razorpayApiSecret);

                Payment payment = razorPay.payments.fetch(paymentId);
                Integer paid = payment.get("amount");
                String status = payment.get("status").toString();
                if (status.equals("captured")) {
                    //by using KAFKA, publish a message to update the order status  paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                    paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                    paymentRepo.save(paymentOrder);
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                    paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                    paymentRepo.save(paymentOrder);
                    return true;
                }
            }
        return false;
    }
}
