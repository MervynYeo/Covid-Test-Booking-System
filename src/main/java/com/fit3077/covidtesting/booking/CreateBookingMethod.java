package com.fit3077.covidtesting.booking;

import com.fit3077.covidtesting.testsite.TestLocation;
import com.fit3077.covidtesting.testsite.TestSite;
import com.fit3077.covidtesting.testsite.TestSiteSystem;
import com.fit3077.covidtesting.user.User;
import com.fit3077.covidtesting.user.UserSystem;
import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;

@Data
public abstract class CreateBookingMethod {

    protected BookingSystem bookingSystem;
    protected TestSiteSystem testSiteSystem;
    protected UserSystem userSystem;

    public CreateBookingMethod(BookingSystem bookingSystem, TestSiteSystem testSiteSystem, UserSystem userSystem) {
        this.bookingSystem = bookingSystem;
        this.testSiteSystem = testSiteSystem;
        this.userSystem = userSystem;
    }

    abstract Booking execute(User user);

    protected String stringToDate(String dateString) {
        try {
            TimeZone tz = TimeZone.getTimeZone("UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
            df.setTimeZone(tz);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            Date date = formatter.parse(dateString);
            String ISODate = df.format(date);
            return ISODate;
        } catch (Exception e) {
            String errorMessage = "Error in CreateBookingMethod.stringToDate: " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }

    protected TestSite getTestSite(String id) {
        return this.testSiteSystem.getTestSite(id);
    }

    protected boolean isTestSiteExist(String id) {
        if (getTestSite(id) == null) {
            return false;
        }
        return true;
    }

    protected User getUser(String id) {
        return this.userSystem.getUser(id);
    }

    protected boolean isUserExist(String id) {
        if (getUser(id) == null) {
            return false;
        }
        return true;
    }

    protected Booking createBooking(BookingRequest bookingRequest) throws Exception {
        return this.bookingSystem.createBooking(bookingRequest);
    }

    protected BookingRequest inputBookingRequest(User user) {
        Scanner scanner = new Scanner(System.in);
        String customerId;
        boolean isUserExist;
        do {
            System.out.println("Customer ID: ");
            customerId = scanner.next();
            isUserExist = isUserExist(customerId);
            if (!isUserExist) {
                System.out.println("User not found");
            }
        } while (!isUserExist);
        String testingSiteId;
        boolean isTestSiteExist;
        do {
            System.out.println("Test site ID: ");
            testingSiteId = scanner.next();
            isTestSiteExist = isTestSiteExist(testingSiteId);
            if (!isTestSiteExist) {
                System.out.println("Test site not found");
            }
        } while (!isTestSiteExist);
        System.out.println("Start time (dd-MMM-yyyy, e.g. 7-Jun-2013): ");
        String startTime = stringToDate(scanner.next());
        System.out.println(startTime);
        System.out.println("Notes: ");
        String notes = scanner.next();
        TestLocation testLocation = null;
        do {
            System.out.println("Test location: (retype any one of the below)");
            for (TestLocation location : TestLocation.values()) {
                System.out.println(location);
            }
            try {
                testLocation = Enum.valueOf(TestLocation.class, scanner.next().toUpperCase());
            } catch (Exception e) {
                System.out.println("Only these option(s) are available");
            }
        } while (testLocation == null);
        String createdBy = user.getUserName();
        BookingAdditionalInfo bookingAdditionalInfo = new BookingAdditionalInfo(testLocation, createdBy);
        return new BookingRequest(customerId, testingSiteId, startTime, notes,
                bookingAdditionalInfo
        );
    }

}
