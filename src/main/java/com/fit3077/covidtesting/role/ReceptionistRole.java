package com.fit3077.covidtesting.role;

import com.fit3077.covidtesting.common.Action;
import com.fit3077.covidtesting.common.SubSystemContainer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ReceptionistRole extends Role {

    public ReceptionistRole(SubSystemContainer subSystemContainer, List<Action> actions, RoleTypes name) {
        super(subSystemContainer, actions, name);
    }

    public  String toString(){
        return "Receptionist Role";
    }
}
