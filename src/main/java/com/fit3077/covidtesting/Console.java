package com.fit3077.covidtesting;

import com.fit3077.covidtesting.common.Action;
import com.fit3077.covidtesting.user.LoginAction;
import com.fit3077.covidtesting.testsite.ViewTestSite;
import com.fit3077.covidtesting.common.SubSystemContainer;
import com.fit3077.covidtesting.role.RoleFactory;
import com.fit3077.covidtesting.user.User;

import java.util.HashMap;
import java.util.Scanner;

public class Console {
    private User user;

    private SubSystemContainer subSystemContainer;

    public Console(SubSystemContainer subSystemContainer) {
        this.subSystemContainer = subSystemContainer;
    }

    private void addAction(String hotKey,Action action,HashMap<String,Action> actions){
        actions.put(hotKey,action);
        System.out.println(hotKey+" : "+action);
    }

    private void setUser(User user) {
        this.user = user;
    }

    public void run() throws Exception {

        Scanner scanner = new Scanner(System.in);
        String c;
        do {
            System.out.println("Covid Testing Booking System");
            HashMap<String, Action> availableActions = new HashMap<>();
            ViewTestSite viewTestSite=new ViewTestSite();
            addAction(viewTestSite.displayChar(),viewTestSite,availableActions);
            if (this.user == null) {
                RoleFactory roleFactory = new RoleFactory(subSystemContainer); // either should be here or Console
                LoginAction login = new LoginAction(subSystemContainer.getUserSystem(), roleFactory, this);
                addAction(login.displayChar(), login,availableActions);
            } else {
                for (Action action : user.getActions()) {
                    String command = action.displayChar();
                    addAction(command,action,availableActions);
                }
            }
            System.out.println("exit: Exit system");
            c = scanner.next();
            if (!c.equalsIgnoreCase("exit")) {
                try {
                    availableActions.get(c).execute(user);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } while (!c.equalsIgnoreCase("exit"));
    }

    public void listen(User user) {
        if (this.user == null) {
            setUser(user);
        }
    }
}
