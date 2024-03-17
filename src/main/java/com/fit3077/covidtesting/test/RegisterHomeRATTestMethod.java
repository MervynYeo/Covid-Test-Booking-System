package com.fit3077.covidtesting.test;

import com.fit3077.covidtesting.booking.BookingSystem;
import com.fit3077.covidtesting.user.User;
import com.fit3077.covidtesting.user.UserSystem;

import java.util.Scanner;

public class RegisterHomeRATTestMethod extends RegisterTestMethod {
    private TestSystem testSystem;
    private BookingSystem bookingSystem;
    private UserSystem userSystem;

    public RegisterHomeRATTestMethod(TestSystem testSystem, BookingSystem bookingSystem, UserSystem userSystem) {
        super(testSystem, bookingSystem, userSystem);
    }

    @Override
    Test execute(User user) {
        Scanner scanner = new Scanner(System.in);
        TestRequest testRequest = new TestRequest();
        testRequest.setType(TestType.RAT);
        inputTestRequest(testRequest);
        System.out.println("Do you need RAT kit? (Y/N)");
        String ans = scanner.next();
        if (ans.equalsIgnoreCase("Y")) {
            testRequest.setAdditionalInfo(new TestAdditionalInfo(true, false));
        } else {
            testRequest.setAdditionalInfo(new TestAdditionalInfo(false, false));
        }
        try {
            Test test = registerTest(testRequest);
            System.out.println("Test: " + test);
            return test;
        } catch (Exception e) {
            String errorMessage = "Error in RegisterHomeRATTestMethod.execute: " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }
}
