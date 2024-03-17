package com.fit3077.covidtesting.test;

import com.fit3077.covidtesting.common.Action;
import com.fit3077.covidtesting.booking.Booking;
import com.fit3077.covidtesting.booking.BookingSystem;
import com.fit3077.covidtesting.testsite.TestLocation;
import com.fit3077.covidtesting.user.User;
import com.fit3077.covidtesting.user.UserSystem;
import lombok.Data;

import java.util.Scanner;

@Data
public class RegisterTestAction extends Action {
    private TestSystem testSystem;
    private BookingSystem bookingSystem;
    private UserSystem userSystem;
    private RegisterTestMethod registerTestMethod;
    private RegisterTestMethodFactory registerTestMethodFactory = new RegisterTestMethodFactory();

    public RegisterTestAction(TestSystem testSystem, BookingSystem bookingSystem, UserSystem userSystem) {
        this.testSystem = testSystem;
        this.bookingSystem = bookingSystem;
        this.userSystem = userSystem;
    }

    @Override
    public void execute(User user) {
        if (this.getRegisterTestMethod() == null) {
            inputRegisterTestMethod();
        }
        Test test = this.registerTestMethod.execute(user);
        System.out.println("Test registered by User " + user.getUserName() + ": " + test);
    }

    private void inputRegisterTestMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("SMS PIN: ");
        String smsPin = scanner.next();
        System.out.println("Customer ID: ");
        String customerId = scanner.next();
        Booking booking = bookingSystem.verifyBooking(customerId, smsPin);
        if (booking.getStatus().equals("INITIATED")) { // dummy data only has this to test
            System.out.println("Booking verified");
            System.out.println("Choose the type of test: (retype any one of the below)");
            if (booking.getAdditionalInfo().getTestLocation() == TestLocation.HOME) {
                System.out.println(TestType.RAT);
            } else {
                for (TestType type : TestType.values()) {
                    System.out.println(type);
                }
            }
            TestType chosenType = Enum.valueOf(TestType.class, scanner.next().toUpperCase());
            this.setRegisterTestMethod(registerTestMethodFactory.getRegisterTestMethod(
                    this.testSystem, this.bookingSystem, this.userSystem, chosenType,
                    booking.getAdditionalInfo().getTestLocation()
            ));
        }

    }

    @Override
    public String displayChar() {
        return "rt";
    }

    @Override
    public String toString() {
        return "Register test";
    }
}
