package com.fit3077.covidtesting.booking;

import com.fit3077.covidtesting.testsite.TestLocation;
import com.fit3077.covidtesting.testsite.TestSiteSystem;
import com.fit3077.covidtesting.user.User;
import com.fit3077.covidtesting.user.UserSystem;

public class OnlineCreateBookingMethod extends CreateBookingMethod {

    public OnlineCreateBookingMethod(
            BookingSystem bookingSystem,
            TestSiteSystem testSiteSystem,
            UserSystem userSystem
    ) {
        super(bookingSystem, testSiteSystem, userSystem);
    }

    @Override
    public Booking execute(User user) {
        BookingRequest bookingRequest = inputBookingRequest(user);
        if (bookingRequest.getAdditionalInfo().getTestLocation() == TestLocation.HOME) {
            bookingRequest.getAdditionalInfo().setQrCode("Sample QR Code");
            bookingRequest.getAdditionalInfo().setUrl("Sample URL");
        }
        try {
            Booking booking = createBooking(bookingRequest);
            System.out.println("SMS PIN: " + booking.getSmsPin());
            System.out.println("QR Code: " + booking.getAdditionalInfo().getQrCode());
            System.out.println("URL: " + booking.getAdditionalInfo().getUrl());
            return booking;
        } catch (Exception e) {
            String errorMessage = "Error in OnlineCreateBookingMethod.execute: " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }
}
