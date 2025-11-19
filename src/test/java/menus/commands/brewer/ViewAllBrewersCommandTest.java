package menus.commands.brewer;

import controller.BrewerController;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ViewAllBrewersCommandTest {
    @Test
    void execute_shouldCallViewAllBrewersOnController() {
        BrewerController controller = mock(BrewerController.class);
        ViewAllBrewersCommand command = new ViewAllBrewersCommand(controller);
        command.execute();
        verify(controller, times(1)).viewAllBrewers();
    }
}
