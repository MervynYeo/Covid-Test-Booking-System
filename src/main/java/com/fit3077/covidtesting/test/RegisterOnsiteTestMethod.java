package com.fit3077.covidtesting.test;

import com.fit3077.covidtesting.booking.BookingSystem;
import com.fit3077.covidtesting.user.User;
import com.fit3077.covidtesting.user.UserSystem;

public class RegisterOnsiteTestMethod extends RegisterTestMethod {
    private TestSystem testSystem;
    private BookingSystem bookingSystem;
    private UserSystem userSystem;
    private TestType type;

    public RegisterOnsiteTestMethod(TestSystem testSystem, BookingSystem bookingSystem, UserSystem userSystem, TestType type) {
        super(testSystem, bookingSystem, userSystem);
        this.type = type;
    }

    @Override
    Test execute(User user) {
        TestRequest testRequest = new TestRequest();
        testRequest.setType(this.type);
        inputTestRequest(testRequest);
        try {
            System.out.println("Test request: " + testRequest);
            Test test = registerTest(testRequest);
            System.out.println("Test: " + test);
            return test;
        } catch (Exception e) {
            String errorMessage = "Error in RegisterOnsiteTestMethod.execute: " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }

    }
}
