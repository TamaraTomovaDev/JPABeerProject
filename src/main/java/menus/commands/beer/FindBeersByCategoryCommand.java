package menus.commands.beer;

import controller.BeerController;
import menus.commands.Command;

public class FindBeersByCategoryCommand implements Command {
    private final BeerController controller;
    public FindBeersByCategoryCommand(BeerController controller) { this.controller = controller; }
    @Override public void execute() { controller.findBeersByCategory(); }
}
