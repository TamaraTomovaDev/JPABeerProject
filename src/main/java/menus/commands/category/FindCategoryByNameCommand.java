package menus.commands.category;

import controller.CategoryController;
import menus.commands.Command;

public class FindCategoryByNameCommand implements Command {
    private final CategoryController controller;
    public FindCategoryByNameCommand(CategoryController controller) { this.controller = controller; }
    @Override public void execute() { controller.findCategoryByName(); }
}
