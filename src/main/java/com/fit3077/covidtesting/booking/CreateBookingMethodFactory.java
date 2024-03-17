package com.fit3077.covidtesting.booking;

import com.fit3077.covidtesting.testsite.TestSiteSystem;
import com.fit3077.covidtesting.user.UserSystem;

public class CreateBookingMethodFactory {
    public CreateBookingMethod getCreateBookingMethod(
            BookingSystem bookingSystem, TestSiteSystem testSiteSystem, UserSystem userSystem,
            CreateBookingMethodType type
    ) {
        CreateBookingMethod method = null;
        switch (type) {
            case ONLINE:
                method = new OnlineCreateBookingMethod(bookingSystem, testSiteSystem, userSystem);
                break;
            case ONSITE:
                method = new OnSiteCreateBookingMethod(bookingSystem, testSiteSystem, userSystem);
                break;
            default:
                break;
        }
        return method;
    }
}
