package com.fit3077.covidtesting.test;

import com.fit3077.covidtesting.common.Action;
import com.fit3077.covidtesting.common.JsonUtils;
import com.fit3077.covidtesting.user.User;

import java.util.Optional;
import java.util.Scanner;

public class ScanQRAction extends Action {
    private TestSystem testSystem;

    public ScanQRAction(TestSystem testSystem) {
        this.testSystem = testSystem;
    }

    @Override
    public void execute(User user) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Test ID: ");
        String testId = scanner.next();
        Test test = testSystem.getTest(testId);
        if (test.getBooking().getAdditionalInfo().getQrCode() != null) {
            if (isReceivingRATKit(test)) {
                test.getAdditionalInfo().setIsRATKitReceived(true);
                String additionalInfoFields = JsonUtils.toJsonString(test.getAdditionalInfo());
                String additionalInfo = String.format("{\"additionalInfo\":%s}", additionalInfoFields);
                System.out.println("additionalInfo " + additionalInfo);
                try {
                    Test updatedTest = testSystem.updateTest(test.getId(), additionalInfo);
                    System.out.println(updatedTest);
                } catch (Exception e) {
                    System.out.println("Error in ScanQRAction.execute");
                }
            } else {
                System.out.println("Booking verified");
            }
        } else {
            System.out.println("Invalid QR Code");
        }
    }

    private boolean isReceivingRATKit(Test test) {
        return test.getType() == TestType.RAT && Optional.ofNullable(test.getAdditionalInfo().getIsNeedRATKit()).orElse(false);
    }

    @Override
    public String displayChar() {
        return "sq";
    }

    @Override
    public String toString() {
        return "Scan QR (for RAT kit)";
    }
}