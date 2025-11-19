package menus.commands.category;

import controller.CategoryController;
import menus.commands.Command;

public class AddCategoryCommand implements Command {
    private final CategoryController controller;
    public AddCategoryCommand(CategoryController controller) { this.controller = controller; }
    @Override public void execute() { controller.addCategory(); }
}
