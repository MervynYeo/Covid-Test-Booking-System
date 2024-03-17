package com.fit3077.covidtesting.role;

import com.fit3077.covidtesting.common.Action;
import com.fit3077.covidtesting.common.SubSystemContainer;
import lombok.Data;

import java.util.List;

@Data
public class CustomerRole extends Role {

    public CustomerRole(SubSystemContainer subSystemContainer) {
        super(subSystemContainer);
    }

    public CustomerRole(SubSystemContainer subSystemContainer, List<Action> actions, RoleTypes name) {
        super(subSystemContainer, actions, name);
    }

    public  String toString(){
        return "Customer Role";
    }
}
