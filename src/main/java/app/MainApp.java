package app;

import controller.BeerController;
import controller.BrewerController;
import controller.CategoryController;
import menus.BeerMenu;
import menus.BrewerMenu;
import menus.CategoryMenu;
import util.InputUtil;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        InputUtil inputUtil = new InputUtil(new Scanner(System.in));

        BeerController beerController = new BeerController(inputUtil);
        BrewerController brewerController = new BrewerController(inputUtil);
        CategoryController categoryController = new CategoryController(inputUtil);

        BeerMenu beerMenu = new BeerMenu(beerController, inputUtil);
        BrewerMenu brewerMenu = new BrewerMenu(brewerController, inputUtil);
        CategoryMenu categoryMenu = new CategoryMenu(categoryController, inputUtil);

        brewerController.importBrewers();
        categoryController.importCategories();
        beerController.importBeers();


        while (true) {
            System.out.println("---- Beer Brewery Management System ----");
            System.out.println("---- Menu ----");
            System.out.println("1. Manage Brewers");
            System.out.println("2. Manage Beers");
            System.out.println("3. Manage Categories");
            System.out.println("0. Exit");

            int choice = inputUtil.readInt("Uw keuze: ");

            switch (choice) {
                case 1 -> brewerMenu.showMenu();
                case 2 -> beerMenu.showMenu();
                case 3 -> categoryMenu.showMenu();
                case 0 -> {
                    brewerController.exportBrewers();
                    categoryController.exportCategories();
                    beerController.exportBeers();
                    System.out.println("Programma afgesloten. Tot ziens!");
                    return;
                }
                default -> System.out.println("Ongeldige keuze! Probeer opnieuw.");
            }
        }
    }
}
