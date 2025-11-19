package menus.commands.beer;

import controller.BeerController;
import menus.commands.Command;

public class FindBeerByIdCommand implements Command {
    private final BeerController controller;
    public FindBeerByIdCommand(BeerController controller) { this.controller = controller; }
    @Override public void execute() { controller.findBeerById(); }
}
