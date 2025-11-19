package menus.commands.brewer;

import controller.BrewerController;
import menus.commands.Command;

public class ViewBrewersWithBeerCountCommand implements Command {
    private final BrewerController controller;
    public ViewBrewersWithBeerCountCommand(BrewerController controller) { this.controller = controller; }
    @Override public void execute() { controller.viewBrewersWithBeerCount(); }
}
