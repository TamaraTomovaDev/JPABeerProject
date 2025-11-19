package menus.commands.category;

import controller.CategoryController;
import menus.commands.Command;

public class FindCategoryByIdCommand implements Command {
    private final CategoryController controller;
    public FindCategoryByIdCommand(CategoryController controller) { this.controller = controller; }
    @Override public void execute() { controller.findCategoryById();}
}
