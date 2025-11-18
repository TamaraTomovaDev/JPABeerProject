package app;


import menus.BeerMenu;
import menus.BrewerMenu;
import menus.CategoryMenu;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BeerMenu beerMenu = new BeerMenu();
        BrewerMenu brewerMenu = new BrewerMenu();
        CategoryMenu categoryMenu = new CategoryMenu();

        while (true) {
            System.out.println("Menu");
            System.out.println("1. Manage Brewers");
            System.out.println("2. Manage Beers");
            System.out.println("3. Manage Category");
            System.out.println("0. Exit");
            int  choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> brewerMenu.showMenu();
                case 2 -> beerMenu.showMenu();
                case 3 -> categoryMenu.showMenu();
                case 0 -> {
                    System.out.println("Programma afgesloten.");
                    return;
                }
                default -> System.out.println("Ongeldige keuze!");
            }
        }
    }
}