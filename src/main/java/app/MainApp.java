package app;

import config.JpaConfig;
import model.Beer;
import model.Brewer;
import model.Category;
import service.BeerService;
import service.BrewerService;
import service.CategoryService;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        BrewerService brewerService = new BrewerService();
        CategoryService categoryService = new CategoryService();
        BeerService beerService = new BeerService();

        // 1. Maak een Brewer
        Brewer brewer = new Brewer("Duvel Moortgat", "Breendonk", new ArrayList<>());
        brewerService.saveBrewer(brewer);

        // 2. Maak een Category
        Category category = new Category("Blond", "Licht blond bier", new ArrayList<>());
        categoryService.saveCategory(category);

        // 3. Maak een Beer en koppel Brewer + Category
        Beer beer = new Beer("Duvel", 8.5, 2.5, brewer, category);
        beerService.saveBeer(beer);

        // 4. Haal alle bieren op
        List<Beer> beers = beerService.findAllBeers();
        System.out.println("Alle bieren in database:");
        beers.forEach(System.out::println);

        // Sluit EntityManagerFactory
        JpaConfig.close();
    }
}