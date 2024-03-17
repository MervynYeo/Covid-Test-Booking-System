package com.fit3077.covidtesting.booking;

import com.fit3077.covidtesting.common.Action;
import com.fit3077.covidtesting.testsite.TestSite;
import com.fit3077.covidtesting.testsite.TestSiteSystem;
import com.fit3077.covidtesting.user.User;
import com.fit3077.covidtesting.user.UserSystem;
import lombok.Data;

import java.util.*;

@Data
public class CreateBookingAction extends Action {

    private BookingSystem bookingSystem;
    private TestSiteSystem testSiteSystem;
    private UserSystem userSystem;
    private CreateBookingMethod createBookingMethod;
    private List<CreateBookingMethodType> types;
    private CreateBookingMethodFactory createBookingMethodFactory = new CreateBookingMethodFactory();

    public CreateBookingAction(CreateBookingMethod createBookingMethod) {
        this.createBookingMethod = createBookingMethod;
    }

    public CreateBookingAction(BookingSystem bookingSystem, TestSiteSystem testSiteSystem, UserSystem userSystem) {
        this.bookingSystem = bookingSystem;
        this.testSiteSystem = testSiteSystem;
        this.userSystem = userSystem;
    }

    public CreateBookingAction(BookingSystem bookingSystem, TestSiteSystem testSiteSystem, UserSystem userSystem,
                               List<CreateBookingMethodType> types) {
        this.bookingSystem = bookingSystem;
        this.testSiteSystem = testSiteSystem;
        this.userSystem = userSystem;
        this.types = types;
    }

    @Override
    public void execute(User user) {
        if (this.getCreateBookingMethod() == null) {
            inputCreateBookingMethod();
        }
        Booking booking = this.createBookingMethod.execute(user);
        System.out.println("Booking initiated by User " + user.getUserName() + ": " + booking + " & created by User " +
                booking.getAdditionalInfo().getCreatedBy()
        );
    }

    private void inputCreateBookingMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Test site ID: ");
        String testSiteId = scanner.next();
        List<CreateBookingMethodType> bookingMethods = getTestSiteBookingMethods(testSiteId);
        if (bookingMethods.size() == 0) { // assumption if the field on additionalInfo not properly appended
            bookingMethods = List.of(CreateBookingMethodType.values());
        }
        System.out.println("Choose booking method: (retype any one of the below)");
        for (CreateBookingMethodType type : bookingMethods) {
            System.out.println(type);
        }
        CreateBookingMethodType chosenType = Enum.valueOf(CreateBookingMethodType.class, scanner.next().toUpperCase());
        this.setCreateBookingMethod(createBookingMethodFactory.getCreateBookingMethod(
                this.bookingSystem, this.testSiteSystem, this.userSystem, chosenType
        ));
    }

    private List<CreateBookingMethodType> getTestSiteBookingMethods(String testSiteId) {
        TestSite testSite = this.testSiteSystem.getTestSite(testSiteId);
        if (testSite.getAdditionalInfo() != null && testSite.getAdditionalInfo().getBookingType() != null) {
            return testSite.getAdditionalInfo().getBookingType();
        }
        return Collections.emptyList();
    }

    @Override
    public String displayChar() {
        return "cb";
    }

    @Override
    public String toString() {
        return "Create booking";
    }
}
