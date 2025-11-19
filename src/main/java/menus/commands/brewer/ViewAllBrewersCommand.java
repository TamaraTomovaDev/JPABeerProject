package menus.commands.brewer;

import controller.BrewerController;
import menus.commands.Command;

public class ViewAllBrewersCommand implements Command {
    private final BrewerController controller;
    public ViewAllBrewersCommand(BrewerController controller) { this.controller = controller; }
    @Override public void execute() { controller.viewAllBrewers(); }
}
