package com.fit3077.covidtesting.booking;

import com.fit3077.covidtesting.test.Test;
import com.fit3077.covidtesting.testsite.TestLocation;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookingAdditionalInfo {
    private String url;
    private String qrCode;
    private TestLocation testLocation;
    private String createdBy;
    private List<Test> covidTests = new ArrayList<>();

    public BookingAdditionalInfo() {

    }

    public BookingAdditionalInfo(TestLocation testLocation, String createdBy) {
        this.testLocation = testLocation;
        this.createdBy = createdBy;
    }
}
