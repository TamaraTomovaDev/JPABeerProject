package menus.commands.brewer;

import controller.BrewerController;
import menus.commands.Command;

public class FindBrewerByIdCommand implements Command {
    private final BrewerController controller;
    public FindBrewerByIdCommand(BrewerController controller) { this.controller = controller; }
    @Override public void execute() { controller.findBrewerById();}
}
