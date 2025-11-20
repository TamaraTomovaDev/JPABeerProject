package controller;

import model.Brewer;
import service.BrewerService;
import util.InputUtil;

import java.util.List;

public class BrewerController {
    private final BrewerService brewerService = new BrewerService();
    private final InputUtil inputUtil;

    public BrewerController(InputUtil inputUtil) {
        this.inputUtil = inputUtil;
    }

    public void addBrewer() {
        String name = inputUtil.readString("Naam brouwer: ");
        String location = inputUtil.readString("Locatie: ");
        Brewer brewer = new Brewer();
        brewer.setName(name);
        brewer.setLocation(location);

        try {
            brewerService.saveBrewer(brewer);
            System.out.println("Brouwer toegevoegd!");
        } catch (Exception e) {
            System.out.println("Fout: " + e.getMessage());
        }
    }

    public void viewAllBrewers() {
        List<Brewer> brewers = brewerService.findAllBrewers();
        if (brewers.isEmpty()) {
            System.out.println("Geen brouwers gevonden.");
        } else {
            brewers.forEach(System.out::println);
        }
    }

    public void findBrewerById() {
        int id = inputUtil.readInt("ID: ");
        Brewer brewer = brewerService.findBrewerById(id);
        System.out.println(brewer != null ? brewer : "Geen brouwer gevonden met ID " + id);
    }

    public void updateBrewer() {
        int id = inputUtil.readInt("ID van brouwer: ");
        Brewer existing = brewerService.findBrewerById(id);
        if (existing == null) {
            System.out.println("Brouwer niet gevonden!");
            return;
        }

        String name = inputUtil.readString("Nieuwe naam: ");
        String location = inputUtil.readString("Nieuwe locatie: ");
        existing.setName(name);
        existing.setLocation(location);

        try {
            brewerService.updateBrewer(existing);
            System.out.println("Brouwer geüpdatet!");
        } catch (Exception e) {
            System.out.println("Fout: " + e.getMessage());
        }
    }

    public void deleteBrewer() {
        int id = inputUtil.readInt("ID: ");
        try {
            brewerService.deleteBrewer(id);
            System.out.println("Brouwer verwijderd!");
        } catch (Exception e) {
            System.out.println("Fout: " + e.getMessage());
        }
    }

    public void findBrewerByName() {
        String name = inputUtil.readString("Naam brouwer: ");
        Brewer brewer = brewerService.findBrewerByName(name);
        System.out.println(brewer != null ? brewer : "Geen brouwer gevonden met naam " + name);
    }

    public void viewBrewersWithBeerCount() {
        List<Object[]> results = brewerService.findAllBrewersWithBeerCount();
        results.forEach(row -> System.out.println(row[0] + " - " + row[1] + " bieren"));
    }

    public void importBrewers() {
        try {
            brewerService.importBrewersFromJson();
            System.out.println("Brewers geïmporteerd uit JSON.");
        } catch (Exception e) {
            System.out.println("Fout bij importeren: " + e.getMessage());
        }
    }

    public void exportBrewers() {
        try {
            brewerService.exportBrewersToJson();
            System.out.println("Brewers geëxporteerd naar JSON.");
        } catch (Exception e) {
            System.out.println("Fout bij exporteren: " + e.getMessage());
        }
    }
}
