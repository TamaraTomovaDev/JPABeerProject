package menus.commands.brewer;

import controller.BrewerController;
import menus.commands.Command;

public class DeleteBrewerCommand implements Command {
    private final BrewerController controller;
    public DeleteBrewerCommand(BrewerController controller) { this.controller = controller; }
    @Override public void execute() { controller.deleteBrewer(); }
}
