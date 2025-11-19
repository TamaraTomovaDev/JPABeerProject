package menus.commands.beer;

import controller.BeerController;
import menus.commands.Command;

public class ViewAllBeersCommand implements Command {
    private final BeerController controller;
    public ViewAllBeersCommand(BeerController controller) { this.controller = controller; }
    @Override public void execute() { controller.viewAllBeers(); }
}
