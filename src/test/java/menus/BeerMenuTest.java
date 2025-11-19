package menus;

import controller.BeerController;
import menus.commands.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.InputUtil;

import java.util.Map;

import static org.mockito.Mockito.*;

class BeerMenuTest {

    private BeerMenu beerMenu;
    private BeerController beerControllerMock;
    private InputUtil inputUtilMock;

    @BeforeEach
    void setUp() {
        beerControllerMock = mock(BeerController.class);
        inputUtilMock = mock(InputUtil.class);
        beerMenu = new BeerMenu(beerControllerMock, inputUtilMock);
    }

    @Test
    void testAddBeerCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(1) // Add Beer
                .thenReturn(0); // Exit

        beerMenu.showMenu();

        verify(beerControllerMock, times(1)).addBeer();
    }

    @Test
    void testViewAllBeersCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(2)
                .thenReturn(0);

        beerMenu.showMenu();

        verify(beerControllerMock, times(1)).viewAllBeers();
    }

    @Test
    void testFindBeerByIdCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(3)
                .thenReturn(0);

        beerMenu.showMenu();

        verify(beerControllerMock, times(1)).findBeerById();
    }

    @Test
    void testUpdateBeerCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(4)
                .thenReturn(0);

        beerMenu.showMenu();

        verify(beerControllerMock, times(1)).updateBeer();
    }

    @Test
    void testDeleteBeerCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(5)
                .thenReturn(0);

        beerMenu.showMenu();

        verify(beerControllerMock, times(1)).deleteBeer();
    }

    @Test
    void testFindBeersByCategoryCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(6)
                .thenReturn(0);

        beerMenu.showMenu();

        verify(beerControllerMock, times(1)).findBeersByCategory();
    }

    @Test
    void testFindBeersByBrewerCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(7)
                .thenReturn(0);

        beerMenu.showMenu();

        verify(beerControllerMock, times(1)).findBeersByBrewer();
    }

    @Test
    void testFindBeersCheaperThanCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(8)
                .thenReturn(0);

        beerMenu.showMenu();

        verify(beerControllerMock, times(1)).findBeersCheaperThan();
    }

    @Test
    void testInvalidChoiceDoesNothing() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(99)
                .thenReturn(0);

        beerMenu.showMenu();

        verifyNoInteractions(beerControllerMock);
    }

    @Test
    void testCommandsMapIntegrity() {
        Map<Integer, Command> commands = beerMenu.getCommands();
        assert commands.size() == 8;
        assert commands.get(1) != null;
        assert commands.get(8) != null;
    }
}
