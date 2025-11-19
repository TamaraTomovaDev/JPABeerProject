package menus.commands.beer;

import controller.BeerController;
import menus.commands.Command;

public class FindBeersByBrewerCommand implements Command {
    private final BeerController controller;
    public FindBeersByBrewerCommand(BeerController controller) { this.controller = controller; }
    @Override public void execute() { controller.findBeersByBrewer(); }
}
