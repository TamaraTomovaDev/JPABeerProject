package menus;

import controller.CategoryController;
import menus.commands.Command;
import menus.commands.category.*;
import util.InputUtil;

import java.util.Map;

public class CategoryMenu {
    private final Map<Integer, Command> commands;
    private final InputUtil inputUtil;

    // Constructor met dependency injection voor InputUtil
    public CategoryMenu(CategoryController controller, InputUtil inputUtil) {
        this.inputUtil = inputUtil;
        this.commands = Map.of(
                1, new AddCategoryCommand(controller),
                2, new ViewAllCategoriesCommand(controller),
                3, new FindCategoryByIdCommand(controller),
                4, new UpdateCategoryCommand(controller),
                5, new DeleteCategoryCommand(controller),
                6, new FindCategoryByNameCommand(controller)
        );
    }

    public Map<Integer, Command> getCommands() {
        return commands;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- Category Menu ---");
            System.out.println("1. Add Category");
            System.out.println("2. View All Categories");
            System.out.println("3. Find Category by ID");
            System.out.println("4. Update Category");
            System.out.println("5. Delete Category");
            System.out.println("6. Find Category by Name");
            System.out.println("0. Back");

            int choice = inputUtil.readInt("Uw keuze: ");
            if (choice == 0) return;

            Command cmd = commands.get(choice);
            if (cmd != null) cmd.execute();
            else System.out.println("Ongeldige keuze!");
        }
    }
}