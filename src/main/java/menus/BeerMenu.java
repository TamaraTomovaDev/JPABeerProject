package menus;

import controller.BeerController;

import java.util.Scanner;

public class BeerMenu {
    private final BeerController beerController = new BeerController();
    private final Scanner sc = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n--- Beer Menu ---");
            System.out.println("1. Add Beer");
            System.out.println("2. View All Beers");
            System.out.println("3. Find Beer by ID");
            System.out.println("4. Update Beer");
            System.out.println("5. Delete Beer");
            System.out.println("6. Find Beers by Category");
            System.out.println("7. Find Beers by Brewer");
            System.out.println("8. Find Beers cheaper than X");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> beerController.addBeer();
                case 2 -> beerController.viewAllBeers();
                case 3 -> beerController.findBeerById();
                case 4 -> beerController.updateBeer();
                case 5 -> beerController.deleteBeer();
                case 6 -> beerController.findBeersByCategory();
                case 7 -> beerController.findBeersByBrewer();
                case 8 -> beerController.findBeersCheaperThan();
                case 0 -> { return; }
                default -> System.out.println("Ongeldige keuze!");
            }
        }
    }
}
