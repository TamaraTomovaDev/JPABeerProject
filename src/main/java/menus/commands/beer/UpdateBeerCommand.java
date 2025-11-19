package menus.commands.beer;

import controller.BeerController;
import menus.commands.Command;

public class UpdateBeerCommand implements Command {
    private final BeerController controller;
    public UpdateBeerCommand(BeerController controller) { this.controller = controller; }
    @Override public void execute() { controller.updateBeer(); }
}
