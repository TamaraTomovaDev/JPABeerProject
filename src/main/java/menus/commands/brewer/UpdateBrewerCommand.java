package menus.commands.brewer;

import controller.BrewerController;
import menus.commands.Command;

public class UpdateBrewerCommand implements Command {
    private final BrewerController controller;
    public UpdateBrewerCommand(BrewerController controller) { this.controller = controller; }
    @Override public void execute() { controller.updateBrewer();}
}
