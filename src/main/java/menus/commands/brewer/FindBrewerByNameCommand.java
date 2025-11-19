package menus.commands.brewer;

import controller.BrewerController;
import menus.commands.Command;

public class FindBrewerByNameCommand implements Command {
    private final BrewerController controller;
    public FindBrewerByNameCommand(BrewerController controller) { this.controller = controller; }
    @Override public void execute() { controller.findBrewerByName(); }
}
