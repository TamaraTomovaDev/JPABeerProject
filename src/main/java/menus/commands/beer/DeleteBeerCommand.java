package menus.commands.beer;

import controller.BeerController;
import menus.commands.Command;

public class DeleteBeerCommand implements Command {
    private final BeerController controller;
    public DeleteBeerCommand(BeerController controller) { this.controller = controller; }
    @Override public void execute() { controller.deleteBeer(); }
}
