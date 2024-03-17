package com.fit3077.covidtesting.role;

import com.fit3077.covidtesting.common.Action;
import com.fit3077.covidtesting.common.SubSystemContainer;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public abstract class Role {

    protected RoleTypes name;

    protected SubSystemContainer subSystemContainer;

    protected List<Action> actions;

    public Role() {

    }

    public Role(SubSystemContainer subSystemContainer) {
        this.subSystemContainer = subSystemContainer;
    }

    public Role(SubSystemContainer subSystemContainer, List<Action> actions, RoleTypes name) {
        this.subSystemContainer = subSystemContainer;
        this.actions = actions;
        this.name = name;
    }


    public abstract String toString();

    public List<Action> getActions(){
        if (Collections.unmodifiableList(actions)==null){
            return new ArrayList<Action>();
        }
        return Collections.unmodifiableList(actions);
    }

    protected void addAction(Action action) {
        actions.add(action);
    }

}
