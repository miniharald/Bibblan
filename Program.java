package com.company;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class Program {
    private final String BOOK_FOLDER = "books/";
    private final String USER_FOLDER = "users\\";

    private ArrayList<Book> books;
    private ArrayList<User> users;
    private Menu menu;

    public Program() {
        menu = new Menu(this);
        books = new ArrayList<Book>();
        users = new ArrayList<User>();
        loadFiles(BOOK_FOLDER);
        //loadFiles(USER_FOLDER);
    }

    public void runProgram() {
        menu = new Menu(this);

        // låt menyn ta hand om alla val
        menu.handleMenu();

        System.out.print("\nTack för idag och välkommen åter!");
    } // runProgram

    private void loadFiles(String folderName) {
        File folderPath = new File(folderName);

        for (File file : folderPath.listFiles()) {
            Scanner s = null;
            try {
                s = new Scanner(file);
                Path f = file.toPath();
                List<String> contents = Files.readAllLines(f);
                //if(folderName.contains(BOOK_FOLDER)){
                    books.add(new Book(contents));
                //} else {
                    //users.add(new User(contents));
                //}
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//loadFile

    private int getCustomerId(){
        int customerId;
        Scanner scan = new Scanner(System.in);
        System.out.print("Ange ditt Låneummer: ");
        customerId = scan.nextInt();
        for (User u : users) {
            if (u.getId() == customerId)
                return customerId;
        } // for...
        return -1;
    }

    private void listFreeOrBorrowedBooks(Book b, boolean bLookForFreeBooks){
        if((bLookForFreeBooks && !b.isBorrowed() || !bLookForFreeBooks && b.isBorrowed())){
            System.out.println(b);
        }//book.isBorrowed
    }// listFreeOrBorrowedBooks

    private Book getSelectedBook() {
        int nChoice = 0;
        boolean bBadInput = true;
        Scanner scan = new Scanner(System.in);

        do {
            try {
                nChoice = scan.nextInt();
                if (scan.nextLine().equals(""))
                    bBadInput = false;
            } catch (InputMismatchException ex) {
                System.out.println("Hoppsan, det var visst inte ett id. Försök igen!");
                scan.nextLine();
            } // catch
        } while (bBadInput);

        // Loopa igenom och returnera rätt kundval
        for (Book b : books) {
            if (b.getId() == nChoice)
                return b;
        } // for...

        // Fanns inget giltigt menyval, snopet - returnera null
        return null;
    } // getSelectedCustomer

    protected void listBooks(Object o){
        Book book;
        books.forEach(System.out::println);
        System.out.print("Ange Id för bok som du vill ha mer information om: ");
        book = getSelectedBook();
        if(book != null){
            System.out.println(book.getInfo());
        }
    } // listBooks

    protected void listCustomers(Object o){
        users.forEach(System.out::println);
    } // listCustomers

    protected void searchForBook(Object o){
        Scanner scan = new Scanner(System.in);
        String search;
        boolean bFound = false;
        System.out.print("Ange sökterm: ");
        search = scan.nextLine();
        for(Book b : books){
            if(b.getTitle().toLowerCase().contains(search.toLowerCase()) ||
                    b.getAuthor().toLowerCase().contains(search.toLowerCase())){
                System.out.println(b);
                bFound = true;
            }//if b...
        }
        if(!bFound){
            System.out.println("Ingen titel eller författare hittad");
        }// if !bFound...
    } // searchForBook

    protected void addBook(Object o) {

        try (Stream<Path> files = Files.list(Paths.get("books"))) {
            long newFile = files.count() + 1; //counts the amount of files in folder & adds 1 for the new file
            PrintWriter writer = new PrintWriter("books/" + newFile + ".txt", "UTF-8"); //creates the new file
            //input all data
            Scanner scan = new Scanner(System.in);
            System.out.println("Bokens titel:");
            String title = scan.nextLine();
            System.out.println("Bokens författare:");
            String author = scan.nextLine();
            System.out.println("Bokens genre:");
            String genre = scan.nextLine();
            System.out.println("Bokens årtal:");
            String year = scan.nextLine();
            System.out.println("Bokens ISBN:");
            String isbn = scan.nextLine();
            System.out.println("Bokens antal:");
            String quantity = scan.nextLine();
            //add the book as an object
            books.add(new Book(title, author, genre, year, isbn, quantity));
            //Write everything to the txt-file
            String str = String.format("%s\n%s\n%s\n%s\n%s\n%s", title, author, genre, year, isbn, quantity);
            writer.println(str);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//addBook

    protected void deleteBook(Object o){
        Book book;
        File file;
        books.forEach(System.out::println);
        System.out.print("Ange Id för den bok som du vill ta bort: ");
        book = getSelectedBook();
        file = new File("books/" + book.getId() + ".txt");


        if(book != null){
            books.remove(book);
            file.delete();
            System.out.printf("%s är nu borttagen!", book.getTitle());
        }//if...
        else{
            System.out.println("Inget giltigt id!");
        }//else
    }//deleteBook

    protected void addCustomer(Object o){
        Scanner scan = new Scanner(System.in);
        System.out.println("Skriv in kundens tilltalsnamn:");
        String fNamn = scan.nextLine();
        System.out.println("Skriv in kundens efternamn:");
        String lNamn = scan.nextLine();
        users.add(new User(fNamn, lNamn));
    } //addCustomer
} // class Program

