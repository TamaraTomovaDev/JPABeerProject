package menus.commands.beer;

import controller.BeerController;
import menus.commands.Command;

public class FindBeersCheaperThanCommand implements Command {
    private final BeerController controller;
    public FindBeersCheaperThanCommand(BeerController controller) { this.controller = controller; }
    @Override public void execute() { controller.findBeersCheaperThan(); }
}
