package menus;

import controller.BeerController;
import menus.commands.Command;
import menus.commands.beer.*;
import util.InputUtil;

import java.util.Map;

public class BeerMenu {
    private final Map<Integer, Command> commands;
    private final InputUtil inputUtil;

    // Constructor met dependency injection voor InputUtil
    public BeerMenu(BeerController controller, InputUtil inputUtil) {
        this.inputUtil = inputUtil;
        this.commands = Map.of(
                1, new AddBeerCommand(controller),
                2, new ViewAllBeersCommand(controller),
                3, new FindBeerByIdCommand(controller),
                4, new UpdateBeerCommand(controller),
                5, new DeleteBeerCommand(controller),
                6, new FindBeersByCategoryCommand(controller),
                7, new FindBeersByBrewerCommand(controller),
                8, new FindBeersCheaperThanCommand(controller));
    }

    public Map<Integer, Command> getCommands() {
        return commands;
    }

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

            int choice = inputUtil.readInt("Uw keuze: ");
            if (choice == 0) return;

            Command cmd = commands.get(choice);
            if (cmd != null) cmd.execute();
            else System.out.println("Ongeldige keuze!");
        }
    }
}