package com.fit3077.covidtesting.common;

import com.fit3077.covidtesting.booking.BookingSystem;
import com.fit3077.covidtesting.test.TestSystem;
import com.fit3077.covidtesting.testsite.TestSiteSystem;
import com.fit3077.covidtesting.user.UserSystem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubSystemContainer {
    private UserSystem userSystem;
    private BookingSystem bookingSystem;
    private TestSiteSystem testSiteSystem;
    private TestSystem testSystem;
    // Add more subsystems here
}
