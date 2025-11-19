package menus.commands.brewer;

import controller.BrewerController;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ViewBrewersWithBeerCountCommandTest {
    @Test
    void execute_shouldCallViewBrewersWithBeerCountOnController() {
        BrewerController controller = mock(BrewerController.class);
        ViewBrewersWithBeerCountCommand command = new ViewBrewersWithBeerCountCommand(controller);
        command.execute();
        verify(controller, times(1)).viewBrewersWithBeerCount();
    }
}
