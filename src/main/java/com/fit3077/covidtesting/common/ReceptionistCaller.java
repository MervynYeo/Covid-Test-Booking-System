package com.fit3077.covidtesting.common;

import com.fit3077.covidtesting.role.ReceptionistRole;
import com.fit3077.covidtesting.user.User;

import java.util.HashMap;
import java.util.UUID;

public class ReceptionistCaller {
    public static User getReceptionist() {
        return new User(
                UUID.randomUUID().toString(),
                "Helper",
                "Receptionist",
                "helperReceptionist",
                null,
                false,
                true,
                false,
                new HashMap<String, String>(),
                new ReceptionistRole(),
                null
        );
    }
}
