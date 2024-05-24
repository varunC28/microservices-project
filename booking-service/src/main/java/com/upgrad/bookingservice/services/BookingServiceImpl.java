package com.upgrad.bookingservice.services;


import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.upgrad.bookingservice.dao.BookingDao;
import com.upgrad.bookingservice.dto.BookingDTO;
import com.upgrad.bookingservice.dto.PaymentDTO;
import com.upgrad.bookingservice.entities.BookingInfoEntity;
import com.upgrad.bookingservice.utils.POJOconvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    private String paymentServiceUrl = "/payment/transaction";


    private ArrayList<String> getRandomNumbers(int count) {
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String> numberList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        return numberList;
    }

    @Override
    public BookingInfoEntity createBooking(BookingDTO bookingDTO) {
        BookingInfoEntity BookingInfoEntity = POJOconvertor.covertBookingDTOToEntity(bookingDTO);

        long diff = BookingInfoEntity.getToDate().getTime() - BookingInfoEntity.getFromDate().getTime();
        int numberofdays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        BookingInfoEntity.setRoomPrice(1000* BookingInfoEntity.getNumOfRooms()*(numberofdays));

        ArrayList<String> roomList = getRandomNumbers(BookingInfoEntity.getNumOfRooms());
        String rooms = "";
        for(String x : roomList) {
            rooms = rooms + x + ",";
        }
        // System.out.println(rooms);
        rooms = rooms.substring(0,rooms.length()-1);
        // System.out.println(rooms);
        BookingInfoEntity.setRoomNumbers(rooms);
        BookingInfoEntity.setBookedOn(new Date());
        return (bookingDao.save(BookingInfoEntity));
    }


    public BookingInfoEntity savePayment(int bookingId, PaymentDTO paymentDTO) throws Exception {
        String paymentMode = paymentDTO.getPaymentMode();
        paymentMode = paymentMode.trim();

        if(!(paymentMode.equalsIgnoreCase("CARD") || paymentMode.equalsIgnoreCase("UPI"))){
            throw new Exception("Invalid mode of payment");
        }
        Optional<BookingInfoEntity> bookingInfo  = bookingDao.findById(bookingId);
        if(bookingInfo.isPresent()) {
            BookingInfoEntity BookingInfoEntity = bookingInfo.get();
            System.out.println(paymentDTO);
            int transactionId = restTemplate.postForObject(getMicroservice2Url()+paymentServiceUrl,paymentDTO,Integer.class);
            String message = "Booking confirmed for user with aadhaar number: "
                    + BookingInfoEntity.getAadharNumber()
                    +    "    |    "
                    + "Here are the booking details:    " + BookingInfoEntity.toString();
            System.out.println(message);

            BookingInfoEntity.setTransactionId(transactionId);
            bookingDao.save(BookingInfoEntity);
            return  BookingInfoEntity;

        }
        else
            throw new Exception("Invalid Booking Id ");
    }

    public String getMicroservice2Url() {
        List<ServiceInstance> instances = discoveryClient.getInstances("PAYMENT-SERVICE");
        if (instances != null && !instances.isEmpty()) {
            System.out.println(instances.get(0).getUri().toString());
            return instances.get(0).getUri().toString();
        }
        return null; // Or handle the case when the service is not found
    }
}

