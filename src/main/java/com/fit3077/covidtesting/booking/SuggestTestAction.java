package com.fit3077.covidtesting.booking;

import com.fit3077.covidtesting.common.Action;
import com.fit3077.covidtesting.common.JsonUtils;
import com.fit3077.covidtesting.test.Test;
import com.fit3077.covidtesting.test.TestType;
import com.fit3077.covidtesting.user.User;

import java.util.*;

public class SuggestTestAction extends Action {

    private BookingSystem bookingSystem;

    private static final Map<String, TestType> SUGGESTION_FORMULA = new HashMap<>() {{
        put("Y", TestType.PCR);
        put("N", TestType.RAT);
    }};

    public SuggestTestAction(BookingSystem bookingSystem) {
        this.bookingSystem = bookingSystem;
    }

    public void execute(User user) throws Exception{
        Booking booking;
        String customerId;
        String bookingPin;
        do{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Customer ID: ");
            customerId = scanner.next();
            System.out.println("SMS PIN: ");
            bookingPin = scanner.next();
            booking = getBooking(customerId, bookingPin);

        } while(booking == null);
        TestType testType = survey();
        System.out.println("Suggested test: " + testType);
        Test test = new Test();
        test.setType(testType);
        booking.addCovidTest(test);
        String covidTestsString = JsonUtils.toJsonString(booking.getCovidTests());
        String bookingUpdate = String.format("{\"additionalInfo\":{\"covidTests\":%s}}", covidTestsString);
        Booking updatedBooking = this.bookingSystem.updateBooking(booking.getId(), bookingUpdate);
        System.out.println("Updated Booking with suggested test type: " + updatedBooking);
    }
    private Booking getBooking(String customerId,String bookingPin){
        BookingSystem bookingSystem=new BookingSystem();
        return bookingSystem.verifyBooking(customerId,bookingPin);
    }
    private TestType survey(){
        String symptom;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Y if you have severe symptoms");
        System.out.println("Press N if you have no symptom or just want to confirm if you have been infected");
        symptom=scanner.next();
        return SUGGESTION_FORMULA.get(symptom.toUpperCase());
    }

    public  String displayChar(){
        return "s";
    };

    public  String toString(){
     return "Suggest Test";
    }
}
