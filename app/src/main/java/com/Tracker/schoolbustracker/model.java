package com.Tracker.schoolbustracker;

public class model {
    String message;
    int chatNumber;
    String dir;

    public model(String message, int chatNumber, String dir) {
        this.message = message;
        this.chatNumber = chatNumber;
        this.dir = dir;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getChatNumber() {
        return chatNumber;
    }

    public void setChatNumber(int chatNumber) {
        this.chatNumber = chatNumber;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
