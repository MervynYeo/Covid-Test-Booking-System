package com.fit3077.covidtesting.test;

import com.fit3077.covidtesting.booking.*;
import com.fit3077.covidtesting.testsite.TestLocation;
import com.fit3077.covidtesting.user.UserSystem;

public class RegisterTestMethodFactory {
    public RegisterTestMethod getRegisterTestMethod(
            TestSystem testSystem, BookingSystem bookingSystem, UserSystem userSystem,
            TestType type, TestLocation testLocation
    ) {
        if (testLocation == TestLocation.HOME) {
            return new RegisterHomeRATTestMethod(testSystem, bookingSystem, userSystem);
        } else {
            return new RegisterOnsiteTestMethod(testSystem, bookingSystem, userSystem, type);
        }
    }
}
