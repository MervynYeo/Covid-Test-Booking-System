package com.fit3077.covidtesting.booking;

import com.fit3077.covidtesting.common.ReceptionistCaller;
import com.fit3077.covidtesting.role.RoleTypes;
import com.fit3077.covidtesting.testsite.TestSiteSystem;
import com.fit3077.covidtesting.user.User;
import com.fit3077.covidtesting.user.UserSystem;

public class OnSiteCreateBookingMethod extends CreateBookingMethod {

    public OnSiteCreateBookingMethod(
            BookingSystem bookingSystem,
            TestSiteSystem testSiteSystem,
            UserSystem userSystem
    ) {
        super(bookingSystem, testSiteSystem, userSystem);
    }

    @Override
    public Booking execute(User user) {
        System.out.println("You are " + user.getRole().getName());
        if (user.getRole().getName() != RoleTypes.RECEPTIONIST) {
            System.out.println(
                    "You must be assisted by a receptionist to create booking.\n" +
                            "We will call one for you.\n" +
                            "In the meantime, please fill in your booking request information: "
            );
            user = ReceptionistCaller.getReceptionist();
        }
        BookingRequest bookingRequest = inputBookingRequest(user);
        try {
            Booking booking = createBooking(bookingRequest);
            System.out.println("SMS PIN: " + booking.getSmsPin());
            return booking;
        } catch (Exception e) {
            String errorMessage = "Error in OnSiteCreateBookingMethod.execute: " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }
}
