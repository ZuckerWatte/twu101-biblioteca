package com.twu.biblioteca;

import java.util.function.Function;

public class MenuOption {

    public static final String SEPARATOR = " - ";

    private String name;
    private String command;
    private String successMsg;
    private String failureMsg;
    private String requestMsg;
    private Function function;

    public MenuOption(String name, String command, Function function) {
        this(name, command, "", "", "", function);
    }

    public MenuOption(String name, String command, String successMsg, String failureMsg, String requestMsg, Function function) {
        this.name = name;
        this.command = command;
        this.successMsg = successMsg;
        this.failureMsg = failureMsg;
        this.requestMsg = requestMsg;
        this.function = function;
    }

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public String getFailureMsg() {
        return failureMsg;
    }

    public String getRequestMsg() {
        return requestMsg;
    }

    public void execute(BibliotecaApp bib) {
        if (!requestMsg.isEmpty()) {
            BibliotecaApp.print("\n" + requestMsg);
            String userinput = BibliotecaApp.readUserInput();
            BibliotecaApp.print((boolean) function.apply(userinput) ? successMsg : failureMsg);
        } else {
            function.apply(bib);
        }
    }

    @Override
    public String toString() {
        return command + SEPARATOR + name;
    }

}
