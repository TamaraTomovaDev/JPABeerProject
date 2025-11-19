package menus;

import controller.BrewerController;
import menus.commands.Command;
import menus.commands.brewer.*;
import util.InputUtil;

import java.util.Map;

public class BrewerMenu {
    private final Map<Integer, Command> commands;
    private final InputUtil inputUtil;

    // Constructor met dependency injection voor InputUtil
    public BrewerMenu(BrewerController controller, InputUtil inputUtil) {
        this.inputUtil = inputUtil;
        this.commands = Map.of(
                1, new AddBrewerCommand(controller),
                2, new ViewAllBrewersCommand(controller),
                3, new FindBrewerByIdCommand(controller),
                4, new UpdateBrewerCommand(controller),
                5, new DeleteBrewerCommand(controller),
                6, new FindBrewerByNameCommand(controller),
                7, new ViewBrewersWithBeerCountCommand(controller)
        );
    }

    public Map<Integer, Command> getCommands() {
        return commands;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- Brewer Menu ---");
            System.out.println("1. Add Brewer");
            System.out.println("2. View All Brewers");
            System.out.println("3. Find Brewer by ID");
            System.out.println("4. Update Brewer");
            System.out.println("5. Delete Brewer");
            System.out.println("6. Find Brewer by Name");
            System.out.println("7. View Brewers with Beer Count");
            System.out.println("0. Back");

            int choice = inputUtil.readInt("Uw keuze: ");
            if (choice == 0) return;

            Command cmd = commands.get(choice);
            if (cmd != null) cmd.execute();
            else System.out.println("Ongeldige keuze!");
        }
    }
}