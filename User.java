package com.company;

public class User {
    private int id;
    private String fName;
    private String lName;
    private static int lastId = 0;

    public User(String fName, String lName) {
        this.id = ++lastId;
        this.fName = fName;
        this.lName = lName;
    }

    public User(String readFromFile) {
        String[] stringsInfo = readFromFile.split(":");
        this.id = Integer.parseInt(stringsInfo[0]);
        this.fName = stringsInfo[1];
        this.lName = stringsInfo[2];
        lastId = this.id;
    }

    @Override
    public String toString() {
        return String.format("Id: %3d Namn: %s %s", id, fName, lName);
    }

    public String toSaveString() {
        return String.format("%d:%s:%s", id, fName, lName);
    }

    public int getId() {return id;}

    public String getName() {
        return fName + " " + lName;
    }
}
