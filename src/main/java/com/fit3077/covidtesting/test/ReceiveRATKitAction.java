package com.fit3077.covidtesting.test;

import com.fit3077.covidtesting.common.Action;
import com.fit3077.covidtesting.common.ReceptionistCaller;
import com.fit3077.covidtesting.testsite.TestLocation;
import com.fit3077.covidtesting.user.User;

import java.util.List;
import java.util.Scanner;

public class ReceiveRATKitAction extends Action {


    private TestSystem testSystem;


    public ReceiveRATKitAction(TestSystem testSystem) {
        this.testSystem = testSystem;
    }

    private Boolean isValid() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Patient ID: ");
        String patientId = scanner.next();
        List<Test> tests = this.testSystem.getTests();
        for (Test test : tests) {
            try {
                if (test.getPatient().getId().equals(patientId) && test.getType() == TestType.RAT &&
                        test.getBooking().getAdditionalInfo().getTestLocation() == TestLocation.HOME) {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Error in ReceiveRATKitAction.isValid: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    @Override
    public void execute(User user) throws Exception {
        if (isValid()) {
            User staff = ReceptionistCaller.getReceptionist();
            System.out.println("Staff called: " + staff);
            Action scanQR = new ScanQRAction(this.testSystem);
            scanQR.execute(staff);
            System.out.println("Scanned QR");
        } else {
            System.out.println("Invalid action");
        }
    }

    @Override
    public String displayChar() {
        return "rrk";
    }

    @Override
    public String toString() {
        return "Receive RAT kit";
    }
}
