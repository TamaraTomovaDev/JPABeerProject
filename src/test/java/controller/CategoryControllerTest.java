package controller;

import model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.CategoryService;
import util.InputUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class CategoryControllerTest {

    private CategoryController categoryController;
    private CategoryService categoryServiceMock;
    private InputUtil inputUtilMock;

    @BeforeEach
    void setUp() throws Exception {
        categoryServiceMock = mock(CategoryService.class);
        inputUtilMock = mock(InputUtil.class);

        // Maak een echte CategoryController met InputUtil mock
        categoryController = new CategoryController(inputUtilMock);

        // Injecteer CategoryService mock via reflection
        Field serviceField = CategoryController.class.getDeclaredField("categoryService");
        serviceField.setAccessible(true);
        serviceField.set(categoryController, categoryServiceMock);
    }

    @Test
    void testAddCategory() {
        when(inputUtilMock.readString("Naam categorie: ")).thenReturn("TestCategory");
        when(inputUtilMock.readString("Beschrijving: ")).thenReturn("TestDescription");

        categoryController.addCategory();

        verify(categoryServiceMock, times(1)).saveCategory(any(Category.class));
    }

    @Test
    void testViewAllCategories() {
        Category cat1 = new Category();
        cat1.setName("Category1");
        cat1.setDescription("Description1");

        Category cat2 = new Category();
        cat2.setName("Category2");
        cat2.setDescription("Description2");

        List<Category> categories = Arrays.asList(cat1, cat2);
        when(categoryServiceMock.findAllCategories()).thenReturn(categories);

        categoryController.viewAllCategories();

        verify(categoryServiceMock, times(1)).findAllCategories();
    }

    @Test
    void testFindCategoryById() {
        when(inputUtilMock.readInt("ID: ")).thenReturn(1);

        Category category = new Category();
        category.setName("TestCategory");
        category.setDescription("TestDescription");

        when(categoryServiceMock.findCategoryById(1)).thenReturn(category);

        categoryController.findCategoryById();

        verify(categoryServiceMock, times(1)).findCategoryById(1);
    }

    @Test
    void testUpdateCategory() {
        when(inputUtilMock.readInt("ID van categorie: ")).thenReturn(1);

        Category existing = new Category();
        existing.setName("OldName");
        existing.setDescription("OldDescription");

        when(categoryServiceMock.findCategoryById(1)).thenReturn(existing);

        when(inputUtilMock.readString("Nieuwe naam: ")).thenReturn("NewName");
        when(inputUtilMock.readString("Nieuwe beschrijving: ")).thenReturn("NewDescription");

        categoryController.updateCategory();

        verify(categoryServiceMock, times(1)).updateCategory(existing);
    }

    @Test
    void testDeleteCategory() {
        when(inputUtilMock.readInt("ID: ")).thenReturn(1);

        categoryController.deleteCategory();

        verify(categoryServiceMock, times(1)).deleteCategory(1);
    }

    @Test
    void testFindCategoryByName() {
        when(inputUtilMock.readString("Naam categorie: ")).thenReturn("TestCategory");

        Category category = new Category();
        category.setName("TestCategory");
        category.setDescription("TestDescription");

        when(categoryServiceMock.findCategoryByName("TestCategory")).thenReturn(category);

        categoryController.findCategoryByName();

        verify(categoryServiceMock, times(1)).findCategoryByName("TestCategory");
    }
}
