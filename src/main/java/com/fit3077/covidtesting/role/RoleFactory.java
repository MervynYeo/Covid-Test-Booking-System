package com.fit3077.covidtesting.role;

import com.fit3077.covidtesting.common.Action;
import com.fit3077.covidtesting.booking.SuggestTestAction;
import com.fit3077.covidtesting.booking.CheckBookingStatusAction;
import com.fit3077.covidtesting.booking.CreateBookingAction;
import com.fit3077.covidtesting.booking.CreateBookingMethodType;
import com.fit3077.covidtesting.common.SubSystemContainer;
import com.fit3077.covidtesting.test.ReceiveRATKitAction;
import com.fit3077.covidtesting.test.RegisterTestAction;
import com.fit3077.covidtesting.test.ScanQRAction;

import java.util.Arrays;
import java.util.List;

public class RoleFactory {
    private SubSystemContainer subSystemContainer;
    public RoleFactory(SubSystemContainer subSystemContainer) {
        this.subSystemContainer = subSystemContainer;
    }

    public Role getRole(Enum<RoleTypes> role) {
        if(role == null){
            return null;
        } else if (role == RoleTypes.CUSTOMER) {
            List<Action> customerActions = Arrays.asList(
                    new CreateBookingAction(
                            subSystemContainer.getBookingSystem(),
                            subSystemContainer.getTestSiteSystem(),
                            subSystemContainer.getUserSystem(),
                            Arrays.asList(CreateBookingMethodType.ONLINE, CreateBookingMethodType.ONSITE) // with help of receptionist
                    ),
                    new CheckBookingStatusAction(subSystemContainer.getBookingSystem())
            );
            return new CustomerRole(subSystemContainer, customerActions, RoleTypes.CUSTOMER);
        } else if (role == RoleTypes.RECEPTIONIST) {
            List<Action> receptionistActions = Arrays.asList(
                    new CreateBookingAction(
                            subSystemContainer.getBookingSystem(),
                            subSystemContainer.getTestSiteSystem(),
                            subSystemContainer.getUserSystem(),
                            List.of(CreateBookingMethodType.ONSITE)
                    ),
                    new CheckBookingStatusAction(subSystemContainer.getBookingSystem()),
                    new ScanQRAction(subSystemContainer.getTestSystem())
            );
            return new ReceptionistRole(subSystemContainer,receptionistActions, RoleTypes.RECEPTIONIST);
        } else if (role == RoleTypes.HEALTHCAREWORKER) {
            List<Action> HealthCareActions = Arrays.asList(
                    new SuggestTestAction(subSystemContainer.getBookingSystem()),
                    new CheckBookingStatusAction(subSystemContainer.getBookingSystem()),
                    new RegisterTestAction(
                            subSystemContainer.getTestSystem(),
                            subSystemContainer.getBookingSystem(),
                            subSystemContainer.getUserSystem()
                    ),
                    new ScanQRAction(subSystemContainer.getTestSystem())
            );
            return new HealthcareWorkerRole(subSystemContainer,HealthCareActions, RoleTypes.HEALTHCAREWORKER);
        } else if(role == RoleTypes.PATIENT){
            List<Action> patientActions = Arrays.asList(
                    new CheckBookingStatusAction(subSystemContainer.getBookingSystem()),
                    new RegisterTestAction(
                            subSystemContainer.getTestSystem(),
                            subSystemContainer.getBookingSystem(),
                            subSystemContainer.getUserSystem()
                    ),
                    new ReceiveRATKitAction(subSystemContainer.getTestSystem())
            );
            return new PatientRole(subSystemContainer, patientActions, RoleTypes.PATIENT);
        }
        return null;
    }
}
