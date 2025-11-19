package menus;

import controller.CategoryController;
import menus.commands.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.InputUtil;

import java.util.Map;

import static org.mockito.Mockito.*;

class CategoryMenuTest {

    private CategoryMenu categoryMenu;
    private CategoryController categoryControllerMock;
    private InputUtil inputUtilMock;

    @BeforeEach
    void setUp() {
        categoryControllerMock = mock(CategoryController.class);
        inputUtilMock = mock(InputUtil.class);
        categoryMenu = new CategoryMenu(categoryControllerMock, inputUtilMock);
    }

    @Test
    void testAddCategoryCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(1) // Add Category
                .thenReturn(0); // Exit

        categoryMenu.showMenu();

        verify(categoryControllerMock, times(1)).addCategory();
    }

    @Test
    void testViewAllCategoriesCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(2) // View All
                .thenReturn(0);

        categoryMenu.showMenu();

        verify(categoryControllerMock, times(1)).viewAllCategories();
    }

    @Test
    void testFindCategoryByIdCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(3) // Find by ID
                .thenReturn(0);

        categoryMenu.showMenu();

        verify(categoryControllerMock, times(1)).findCategoryById();
    }

    @Test
    void testUpdateCategoryCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(4) // Update
                .thenReturn(0);

        categoryMenu.showMenu();

        verify(categoryControllerMock, times(1)).updateCategory();
    }

    @Test
    void testDeleteCategoryCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(5) // Delete
                .thenReturn(0);

        categoryMenu.showMenu();

        verify(categoryControllerMock, times(1)).deleteCategory();
    }

    @Test
    void testFindCategoryByNameCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(6) // Find by Name
                .thenReturn(0);

        categoryMenu.showMenu();

        verify(categoryControllerMock, times(1)).findCategoryByName();
    }

    @Test
    void testInvalidChoiceDoesNothing() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(99) // Ongeldige keuze
                .thenReturn(0);

        categoryMenu.showMenu();

        verifyNoInteractions(categoryControllerMock);
    }

    @Test
    void testCommandsMapIntegrity() {
        Map<Integer, Command> commands = categoryMenu.getCommands();
        assert commands.size() == 6;
        assert commands.get(1) != null;
        assert commands.get(6) != null;
    }
}