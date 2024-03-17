package com.fit3077.covidtesting.test;

import com.fit3077.covidtesting.booking.Booking;
import com.fit3077.covidtesting.booking.BookingSystem;
import com.fit3077.covidtesting.user.User;
import com.fit3077.covidtesting.user.UserSystem;

import java.util.Scanner;

public abstract class RegisterTestMethod {
    protected TestSystem testSystem;
    protected BookingSystem bookingSystem;
    protected UserSystem userSystem;

    public RegisterTestMethod(TestSystem testSystem, BookingSystem bookingSystem, UserSystem userSystem) {
        this.testSystem = testSystem;
        this.bookingSystem = bookingSystem;
        this.userSystem = userSystem;
    }

    abstract Test execute(User user);

    protected User getUser(String id) {
        return this.userSystem.getUser(id);
    }

    protected boolean isUserExist(String id) {
        if (getUser(id) == null) {
            return false;
        }
        return true;
    }

    protected Booking getBooking(String id) {
        return this.bookingSystem.getBooking(id);
    }

    protected boolean isBookingExist(String id) {
        if (getBooking(id) == null) {
            return false;
        }
        return true;
    }

    protected Test registerTest(TestRequest testRequest) throws Exception {
        return this.testSystem.registerTest(testRequest);
    }

    protected void inputTestRequest(TestRequest testRequest) {
        Scanner scanner = new Scanner(System.in);
        String patientId;
        boolean isPatientExist;
        do {
            System.out.println("Patient ID: ");
            patientId = scanner.next();
            isPatientExist = isUserExist(patientId);
            if (!isPatientExist) {
                System.out.println("Patient not found");
            }
        } while (!isPatientExist);
        testRequest.setPatientId(patientId);
        String administererId;
        boolean isAdministererExist;
        do {
            System.out.println("Administerer ID: ");
            administererId = scanner.next();
            isAdministererExist = isUserExist(administererId);
            if (!isAdministererExist) {
                System.out.println("Administerer not found");
            }
        } while (!isAdministererExist);
        testRequest.setAdministererId(administererId);
        String bookingId;
        boolean isBookingExist;
        do {
            System.out.println("Booking ID: ");
            bookingId = scanner.next();
            isBookingExist = isBookingExist(bookingId);
            if (!isBookingExist) {
                System.out.println("Booking not found");
            }
        } while (!isBookingExist);
        testRequest.setBookingId(bookingId);
        testRequest.setResult(ResultType.PENDING);
        testRequest.setStatus("INITIATED");
        testRequest.setNotes("Notes");
        testRequest.setAdditionalInfo(new TestAdditionalInfo());
    }
}
