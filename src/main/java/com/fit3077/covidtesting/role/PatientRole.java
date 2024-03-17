package com.fit3077.covidtesting.role;

import com.fit3077.covidtesting.common.Action;
import com.fit3077.covidtesting.common.SubSystemContainer;
import lombok.Data;

import java.util.List;

@Data
public class PatientRole extends Role {

    public PatientRole(SubSystemContainer subSystemContainer, List<Action> actions, RoleTypes name) {
        super(subSystemContainer, actions, name);
    }

    public  String toString(){
        return "Patient Role";
    }
}
