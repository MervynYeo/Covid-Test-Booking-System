package com.fit3077.covidtesting.common;

import com.fit3077.covidtesting.user.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Action {

//    protected T subSystem;

    public abstract void execute(User user) throws Exception;

    public abstract String displayChar();

    public abstract String toString();
}
