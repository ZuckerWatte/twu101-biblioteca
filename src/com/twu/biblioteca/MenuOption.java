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

    public MenuOption(String name, String command, String successMsg, String failureMsg, String inquiryMsg, Function<String, Boolean> function) {
        this.name = name;
        this.command = command;
        this.successMsg = successMsg;
        this.failureMsg = failureMsg;
        this.inquiryMsg = inquiryMsg;
        this.function = function;
    }

    public MenuOption(String name, String command, String successMsg, String failureMsg, Function<String, Boolean> function) {
        this(name, command, successMsg, failureMsg, "", function);
    }

    public MenuOption(String name, String command, Function<String, Boolean> function) {
        this(name, command, "", "", function);
    }

    public void execute() {
        Helper.print(inquiryMsg);
        String userInput = inquiryMsg.isEmpty() ? "" : Helper.readUserInput();
        Helper.print(function.apply(userInput) ? successMsg : failureMsg);
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return command + SEPARATOR + name;
    }

}
