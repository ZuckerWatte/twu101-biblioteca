package com.twu.biblioteca;

import java.util.function.Function;

public class MenuOption {

    public static final String SEPARATOR = " - ";

    private String name;
    private String command;
    private String successMsg;
    private String failureMsg;
    private String inquiryMsg;
    private Function<String, Boolean> function;

    private boolean visibleForLoggedIn;
    private boolean visibleForLoggedOut;

    public MenuOption(String name, String command, String successMsg, String failureMsg, String inquiryMsg, Function<String, Boolean> function, boolean visibleForLoggedIn, boolean visibleForLoggedOut) {
        this.name = name;
        this.command = command;
        this.successMsg = successMsg;
        this.failureMsg = failureMsg;
        this.inquiryMsg = inquiryMsg;
        this.function = function;
        this.visibleForLoggedIn = visibleForLoggedIn;
        this.visibleForLoggedOut = visibleForLoggedOut;
    }

    public MenuOption(String name, String command, String successMsg, String failureMsg, Function<String, Boolean> function, boolean visibleForLoggedIn, boolean visibleForLoggedOut) {
        this(name, command, successMsg, failureMsg, "", function, visibleForLoggedIn, visibleForLoggedOut);
    }

    public MenuOption(String name, String command, Function<String, Boolean> function, boolean visibleForLoggedOut) {
        this(name, command, "", "", function, true, visibleForLoggedOut);
    }

    public void execute(boolean loggedInUser) {
        boolean shouldExecute = loggedInUser ? visibleForLoggedIn : visibleForLoggedOut;
        if (!shouldExecute) {
            Helper.print(Constants.INVALIDOPT_MSG);
            return;
        }
        Helper.print(inquiryMsg);
        String userInput = inquiryMsg.isEmpty() ? "" : Helper.readUserInput();
        Helper.print(function.apply(userInput) ? successMsg : failureMsg);
    }

    public String getCommand() {
        return command;
    }

    public String toString(boolean loggedInUser) {
        boolean shouldPrint = loggedInUser ? visibleForLoggedIn : visibleForLoggedOut;
        return shouldPrint ? command + SEPARATOR + name : "";
    }

}
