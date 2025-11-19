package menus.commands.beer;

import controller.BeerController;
import menus.commands.Command;

public class AddBeerCommand implements Command {
    private final BeerController controller;
    public AddBeerCommand(BeerController controller) {this.controller = controller;}
    @Override
    public void execute() {controller.addBeer();}
}
