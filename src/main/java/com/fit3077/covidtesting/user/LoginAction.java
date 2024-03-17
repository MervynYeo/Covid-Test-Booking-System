package com.fit3077.covidtesting.user;

import com.fit3077.covidtesting.Console;
import com.fit3077.covidtesting.common.Action;
import com.fit3077.covidtesting.role.RoleTypes;
import com.fit3077.covidtesting.role.RoleFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginAction extends Action {

    private final UserSystem userSystem;
    private final RoleFactory roleFactory;
    private final Console console;

    public LoginAction(UserSystem userSystem, RoleFactory roleFactory, Console console){
        super();
        this.userSystem = userSystem;
        this.roleFactory = roleFactory;
        this.console = console;
    }
    public void execute(User user) throws Exception {
        User loginUser = getLoginUser();
        setLoginRole(loginUser);
        console.listen(loginUser);
        System.out.println("LoginAction done for user: " + loginUser.getUserName());
    }

    private User getLoginUser() throws Exception {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter your username:");
        String username = scanner.next();
        System.out.println("Please enter your password:");
        String password = scanner.next();
        try {
            User user = userSystem.login(username, password);
            return user;
        } catch (Exception e) {
            String errorMessage = "Failed to log in, error in LoginAction.execute: " + e.getMessage();
            System.out.println(errorMessage);
            throw new Exception(errorMessage);
        }
    }

    private void setLoginRole(User user) {
        Scanner scanner=new Scanner(System.in);
        ArrayList<Enum<RoleTypes>> roles=user.checkRole();
        for(int i = 0; i < roles.size(); i++) {
            System.out.println("press "+i+" for "+roles.get(i));
        }
        System.out.println("Please select an account to login:");
        Integer index =scanner.nextInt();
        user.setRole(this.roleFactory.getRole(roles.get(index)));
    }

    public String displayChar(){
        return "l";
    }
    public String toString(){
        return "Login";
    }
}
