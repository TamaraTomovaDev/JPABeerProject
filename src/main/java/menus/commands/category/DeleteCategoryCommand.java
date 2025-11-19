package menus.commands.category;

import controller.CategoryController;
import menus.commands.Command;

public class DeleteCategoryCommand implements Command {
    private final CategoryController controller;
    public DeleteCategoryCommand(CategoryController controller) { this.controller = controller; }
    @Override public void execute() { controller.deleteCategory(); }
}
