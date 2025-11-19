package menus;

import controller.BrewerController;
import menus.commands.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.InputUtil;

import java.util.Map;

import static org.mockito.Mockito.*;

class BrewerMenuTest {

    private BrewerMenu brewerMenu;
    private BrewerController brewerControllerMock;
    private InputUtil inputUtilMock;

    @BeforeEach
    void setUp() {
        brewerControllerMock = mock(BrewerController.class);
        inputUtilMock = mock(InputUtil.class);
        brewerMenu = new BrewerMenu(brewerControllerMock, inputUtilMock);
    }

    @Test
    void testAddBrewerCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(1)
                .thenReturn(0);

        brewerMenu.showMenu();

        verify(brewerControllerMock, times(1)).addBrewer();
    }

    @Test
    void testViewAllBrewersCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(2)
                .thenReturn(0);

        brewerMenu.showMenu();

        verify(brewerControllerMock, times(1)).viewAllBrewers();
    }

    @Test
    void testFindBrewerByIdCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(3)
                .thenReturn(0);

        brewerMenu.showMenu();

        verify(brewerControllerMock, times(1)).findBrewerById();
    }

    @Test
    void testUpdateBrewerCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(4)
                .thenReturn(0);

        brewerMenu.showMenu();

        verify(brewerControllerMock, times(1)).updateBrewer();
    }

    @Test
    void testDeleteBrewerCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(5)
                .thenReturn(0);

        brewerMenu.showMenu();

        verify(brewerControllerMock, times(1)).deleteBrewer();
    }

    @Test
    void testFindBrewerByNameCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(6)
                .thenReturn(0);

        brewerMenu.showMenu();

        verify(brewerControllerMock, times(1)).findBrewerByName();
    }

    @Test
    void testViewBrewersWithBeerCountCommand() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(7)
                .thenReturn(0);

        brewerMenu.showMenu();

        verify(brewerControllerMock, times(1)).viewBrewersWithBeerCount();
    }

    @Test
    void testInvalidChoiceDoesNothing() {
        when(inputUtilMock.readInt("Uw keuze: "))
                .thenReturn(99)
                .thenReturn(0);

        brewerMenu.showMenu();

        verifyNoInteractions(brewerControllerMock);
    }

    @Test
    void testCommandsMapIntegrity() {
        Map<Integer, Command> commands = brewerMenu.getCommands();
        assert commands.size() == 7;
        assert commands.get(1) != null;
        assert commands.get(7) != null;
    }
}