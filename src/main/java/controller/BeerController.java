package controller;

import model.Beer;
import model.Brewer;
import model.Category;
import service.BeerService;
import util.InputUtil;

import java.util.List;

public class BeerController {
    private final BeerService beerService = new BeerService();
    private final InputUtil inputUtil;

    // Constructor
    public BeerController(InputUtil inputUtil) {
        this.inputUtil = inputUtil;
    }

    public void addBeer() {
        String name = inputUtil.readString("Naam: ");
        double alcohol = inputUtil.readDouble("Alcoholpercentage: ");
        double price = inputUtil.readDouble("Prijs: ");
        int brewerId = inputUtil.readInt("Brewer ID: ");
        int categoryId = inputUtil.readInt("Category ID: ");

        Beer beer = new Beer(name, alcohol, price, new Brewer(brewerId), new Category(categoryId));
        try {
            beerService.saveBeer(beer);
            System.out.println("Bier toegevoegd!");
        } catch (Exception e) {
            System.out.println("Fout: " + e.getMessage());
        }
    }

    // De rest van de methods aanpassen op dezelfde manier
    public void findBeerById() {
        int id = inputUtil.readInt("ID: ");
        Beer beer = beerService.findBeerById(id);
        System.out.println(beer != null ? beer : "Geen bier gevonden met ID " + id);
    }

    public void updateBeer() {
        int id = inputUtil.readInt("ID van bier: ");
        Beer existing = beerService.findBeerById(id);
        if (existing == null) {
            System.out.println("Bier niet gevonden!");
            return;
        }

        String name = inputUtil.readString("Nieuwe naam: ");
        double alcohol = inputUtil.readDouble("Nieuw alcoholpercentage: ");
        double price = inputUtil.readDouble("Nieuwe prijs: ");

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
        int id = inputUtil.readInt("ID: ");
        try {
            beerService.deleteBeer(id);
            System.out.println("Bier verwijderd!");
        } catch (Exception e) {
            System.out.println("Fout: " + e.getMessage());
        }
    }

    public void findBeersByCategory() {
        int categoryId = inputUtil.readInt("Category ID: ");
        beerService.findBeersByCategory(categoryId).forEach(System.out::println);
    }

    public void findBeersByBrewer() {
        int brewerId = inputUtil.readInt("Brewer ID: ");
        beerService.findBeersByBrewer(brewerId).forEach(System.out::println);
    }

    public void findBeersCheaperThan() {
        double price = inputUtil.readDouble("Max prijs: ");
        beerService.findBeersCheaperThan(price).forEach(System.out::println);
    }

    public void viewAllBeers() {
        List<Beer> beers = beerService.findAllBeers();
        if (beers.isEmpty()) {
            System.out.println("Geen bieren gevonden.");
        } else {
            beers.forEach(System.out::println);
        }
    }

    public void exportBeers() {
        beerService.exportBeersToJson();
    }

    public void importBeers() {
        beerService.importBeersFromJson();
    }
}
