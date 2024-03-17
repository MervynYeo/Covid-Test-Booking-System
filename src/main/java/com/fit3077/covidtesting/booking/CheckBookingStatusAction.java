package com.fit3077.covidtesting.booking;

import com.fit3077.covidtesting.common.Action;
import com.fit3077.covidtesting.user.User;

import java.util.Scanner;

public class CheckBookingStatusAction extends Action {
    private BookingSystem bookingSystem;

    public CheckBookingStatusAction(BookingSystem bookingSystem) {
        this.bookingSystem = bookingSystem;
    }

    @Override
    public void execute(User user) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Customer ID: ");
        String customerId = scanner.next();
        System.out.println("SMS PIN: ");
        String smsPin = scanner.next();
        String bookingStatus = bookingSystem.checkBookingStatus(customerId, smsPin);
        System.out.println("Booking status: " + bookingStatus);
    }

    @Override
    public String displayChar() {
        return "cbs";
    }

    @Override
    public String toString() {
        return "Check booking status";
    }
}
