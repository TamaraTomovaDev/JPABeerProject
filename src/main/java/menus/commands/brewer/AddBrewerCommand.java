package menus.commands.brewer;

import controller.BrewerController;
import menus.commands.Command;

public class AddBrewerCommand implements Command {
    private final BrewerController controller;
    public AddBrewerCommand(BrewerController controller) { this.controller = controller; }
    @Override public void execute() { controller.addBrewer();}
}
