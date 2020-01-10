package com.company;

import java.util.function.Consumer;

public class MenuChoice {
    private char key;
    private String sTitle;
    private Consumer functionToCall;

    public MenuChoice(String sTitle, char key, Consumer functionToCall) {
        this.key = key;
        this.sTitle = sTitle;
        this.functionToCall = functionToCall;
    } // MenuChoice

    public Consumer getFunctionToCall() {
        return functionToCall;
    } // getFunctionToCall

    public char getKey() {
        return key;
    } // getKey

    protected String getTitle() {
        return sTitle;
    } // getTitle

    protected String getFullTitle() {
        return getKey() + ". " + getTitle();
    } // getFullTitle
} // class MenuChoice
