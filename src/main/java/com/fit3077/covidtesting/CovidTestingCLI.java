package com.fit3077.covidtesting;

import com.fit3077.covidtesting.booking.Booking;
import com.fit3077.covidtesting.booking.BookingSystem;
import com.fit3077.covidtesting.common.SubSystemContainer;
import com.fit3077.covidtesting.test.TestSystem;
import com.fit3077.covidtesting.testsite.TestSite;
import com.fit3077.covidtesting.testsite.TestSiteSystem;
import com.fit3077.covidtesting.user.UserSystem;
import com.fit3077.covidtesting.role.RoleFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CovidTestingCLI implements CommandLineRunner {

    @Override
    public void run (String... args) throws Exception {
        main(args);
    }

    public static void main(String[] args) throws Exception {
        UserSystem userSystem = new UserSystem();
        BookingSystem bookingSystem = new BookingSystem();
        TestSiteSystem testSiteSystem = new TestSiteSystem();
        TestSystem testSystem = new TestSystem();
        SubSystemContainer subSystemContainer = new SubSystemContainer(
                userSystem, bookingSystem, testSiteSystem, testSystem
        );
        
//        List<Booking> bookings = bookingSystem.getBookings();
//        System.out.println("List of Booking objects: " + bookings);
//        System.out.println("ID of first Booking object: " + bookings.get(0).getId());

        Console console = new Console(subSystemContainer);
        console.run();
    }
}
