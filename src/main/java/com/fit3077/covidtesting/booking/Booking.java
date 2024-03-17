package com.fit3077.covidtesting.booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fit3077.covidtesting.test.Test;
import com.fit3077.covidtesting.testsite.TestSite;
import com.fit3077.covidtesting.user.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class Booking {
    private String id;
    private User customer;
    private TestSite testingSite;
    private Date createdAt;
    private Date updatedAt;
    private Date startTime;
    private String smsPin;
    private String status;
    private List<Test> covidTests;
    private String notes;
    private BookingAdditionalInfo additionalInfo;

    public void addCovidTest(Test test){
        if (covidTests==null){
            covidTests=new ArrayList<Test>();
        }
        covidTests.add(test);
    }
}
