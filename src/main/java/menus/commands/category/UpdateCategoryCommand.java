package menus.commands.category;

import controller.CategoryController;
import menus.commands.Command;

public class UpdateCategoryCommand implements Command {
    private final CategoryController controller;
    public UpdateCategoryCommand(CategoryController controller) { this.controller = controller; }
    @Override public void execute() { controller.updateCategory(); }
}
