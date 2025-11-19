package menus.commands.category;

import controller.CategoryController;
import menus.commands.Command;

public class ViewAllCategoriesCommand implements Command {
    private final CategoryController controller;
    public ViewAllCategoriesCommand(CategoryController controller) { this.controller = controller; }
    @Override public void execute() { controller.viewAllCategories(); }
}
