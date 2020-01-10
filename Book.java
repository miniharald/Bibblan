package com.company;

import java.util.List;

public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private int year;
    private long isbn;
    private byte quantity;
    private boolean borrowed;
    private int borrowedBy;
    private static int lastId = 0;

    public Book(String title, String author, String genre, String year, String isbn, String quantity) {
        this.id = ++lastId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = Integer.parseInt(year);
        this.isbn = Long.parseLong(isbn);
        this.quantity = Byte.parseByte(quantity);
    }

    public Book(List<String> readFromFile) {
        this.id = ++lastId;
        int i = 0;
        String[] stringsInfo = new String [6];
        for(String content:readFromFile){
            stringsInfo[i] = content;
            i++;
        }
        this.title = stringsInfo[0];
        this.author = stringsInfo[1];
        this.genre = stringsInfo[2];
        this.year = Integer.parseInt(stringsInfo[3]);
        this.isbn = Long.parseLong(stringsInfo[4]);
        this.quantity = Byte.parseByte(stringsInfo[5]);
    }

    @Override
    public String toString() {
        String str = String.format("%d. %s (%d) by %s", id, title, year, author);
        return str;
    }

    public String toSaveString() {
        String str = String.format("%s:%s:%s:", title, author, (borrowed?"true":"false"));
        if(borrowed)
            str += String.format("%d", borrowedBy);
        return str;
    }
    public String getInfo() {
        String str = String.format("Titel: \t\t%s\nUtgiven: \t%d \nFörfattare: %s\nISBN: \t\t%d\nAntal: \t\t%d\n",
                title, year, author, isbn, quantity);
        return str;
    }

    public int getId() {return id;}

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed, int borrowedBy) {
        this.borrowed = borrowed;
        this.borrowedBy = borrowedBy;
    }
}
