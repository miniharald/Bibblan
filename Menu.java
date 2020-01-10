package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private ArrayList<MenuChoice> mainMenu;
    private ArrayList<MenuChoice> customerMenu;
    private ArrayList<MenuChoice> utilityMenu;
    private ArrayList<MenuChoice> currentMenu;

    public Menu(Program p){
        mainMenu = new ArrayList<MenuChoice>();
        mainMenu.add(new MenuChoice("Visa kundmeny", '1', this::showCustomerMenu));
        mainMenu.add(new MenuChoice("Visa bibliotekariemeny", '2', this::showLibrarianMenu));
        mainMenu.add(new MenuChoice("Avsluta", '0', null));

        customerMenu = new ArrayList<MenuChoice>();
        customerMenu.add(new MenuChoice("Lista böcker", '3', p::listBooks));
        customerMenu.add(new MenuChoice("Sök bok", '4', p::searchForBook));
        customerMenu.add(new MenuChoice("Tillbaka till huvudmeny", '0', this::showMainMenu));


        utilityMenu = new ArrayList<MenuChoice>();
        utilityMenu.add(new MenuChoice("Lägg till bok", '1', p::addBook));
        utilityMenu.add(new MenuChoice("Ta bort bok", '2', p::deleteBook));
        utilityMenu.add(new MenuChoice("Lista böcker", '3', p::listBooks));
        utilityMenu.add(new MenuChoice("Lägg till kund", '4', p::addCustomer));
        utilityMenu.add(new MenuChoice("Lista kunder", '6', p::listCustomers));
        utilityMenu.add(new MenuChoice("Tillbaka till huvudmeny", '0', this::showMainMenu));

        currentMenu = mainMenu;
    }

    private void showMainMenu(Object o) {
        currentMenu = mainMenu;
    }//showMainMenu

    private void showLibrarianMenu(Object o) {
        currentMenu = utilityMenu;
    }//showLibrarianMenu

    private void showCustomerMenu(Object o) {
        currentMenu = customerMenu;
    }//showCustomerMenu

    private void printMenu() {
        System.out.println("");
        for (MenuChoice m : currentMenu) {
            System.out.printf("%s%n", m.getFullTitle());
        } // for m...
        System.out.print("Ange ditt val: ");
    } // printMenu


    // Hämta användarens val
    private MenuChoice getMenuChoice() {
        String sChoice;
        Scanner scan = new Scanner(System.in);

        // Se till så att det finns ett menyval och inte en tomrad (blir så efter nextDouble)
        do
            sChoice = scan.nextLine();
        while (sChoice.length() == 0);

        // Loopa igenom och returnera rätt menyval
        for (MenuChoice m : currentMenu) {
            if (m.getKey() == sChoice.charAt(0))
                return m;
        } // for...

        // Fanns inget giltigt menyval, snopet - returnera null
        //if(currentMenu != mainMenu){
          //  currentMenu = mainMenu;
        //}
        return null;
    } // getMenuChoice

    public void handleMenu() {
        boolean bStop = false;
        String sChoice;
        MenuChoice m;
        while (!bStop) {
            printMenu();
            m = getMenuChoice();
            if (m == null)
                System.out.println("Felaktigt val, försök igen!");
            else {
                System.out.printf("Du valde: %s%n", m.getTitle());
                bStop = m.getFunctionToCall() == null;
                if (!bStop) {
                    m.getFunctionToCall().accept(null);
                } // if bStop...
            } // else
        }// while
    }
}
