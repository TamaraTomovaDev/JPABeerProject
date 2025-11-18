package controller;

import model.Beer;
import model.Brewer;
import model.Category;
import service.BeerService;

import java.util.List;
import java.util.Scanner;

public class BeerController {
    private final BeerService beerService = new BeerService();
    private final Scanner sc = new Scanner(System.in);

    public void addBeer() {
        System.out.print("Naam: ");
        String name = sc.nextLine();
        System.out.print("Alcoholpercentage: ");
        double alcohol = Double.parseDouble(sc.nextLine());
        System.out.print("Prijs: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.print("Brewer ID: ");
        int brewerId = Integer.parseInt(sc.nextLine());
        System.out.print("Category ID: ");
        int categoryId = Integer.parseInt(sc.nextLine());

        Beer beer = new Beer(name, alcohol, price, new Brewer(brewerId), new Category(categoryId));
        try {
            beerService.saveBeer(beer);
            System.out.println("Bier toegevoegd!");
        } catch (Exception e) {
            System.out.println("Fout: " + e.getMessage());
        }
    }

    public void viewAllBeers() {
        List<Beer> beers = beerService.findAllBeers();
        beers.forEach(System.out::println);
    }

    public void findBeerById() {
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());
        Beer beer = beerService.findBeerById(id);
        System.out.println(beer != null ? beer : "Niet gevonden");
    }

    public void updateBeer() {
        System.out.print("ID van bier: ");
        int id = Integer.parseInt(sc.nextLine());
        Beer existing = beerService.findBeerById(id);
        if (existing == null) {
            System.out.println("Bier niet gevonden!");
            return;
        }
        System.out.print("Nieuwe naam: ");
        String name = sc.nextLine();
        System.out.print("Nieuw alcoholpercentage: ");
        double alcohol = Double.parseDouble(sc.nextLine());
        System.out.print("Nieuwe prijs: ");
        double price = Double.parseDouble(sc.nextLine());

        existing.setName(name);
        existing.setAlcoholPercentage(alcohol);
        existing.setPrice(price);

        try {
            beerService.updateBeer(existing);
            System.out.println("Bier geüpdatet!");
        } catch (Exception e) {
            System.out.println("Fout: " + e.getMessage());
        }
    }

    public void deleteBeer() {
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());
        try {
            beerService.deleteBeer(id);
            System.out.println("Bier verwijderd!");
        } catch (Exception e) {
            System.out.println("Fout: " + e.getMessage());
        }
    }

    public void findBeersByCategory() {
        System.out.print("Category ID: ");
        int categoryId = Integer.parseInt(sc.nextLine());
        beerService.findBeersByCategory(categoryId).forEach(System.out::println);
    }

    public void findBeersByBrewer() {
        System.out.print("Brewer ID: ");
        int brewerId = Integer.parseInt(sc.nextLine());
        beerService.findBeersByBrewer(brewerId).forEach(System.out::println);
    }

    public void findBeersCheaperThan() {
        System.out.print("Max prijs: ");
        double price = Double.parseDouble(sc.nextLine());
        beerService.findBeersCheaperThan(price).forEach(System.out::println);
    }
}
