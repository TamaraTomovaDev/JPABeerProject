package menus.commands.beer;

import controller.BeerController;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ViewAllBeersCommandTest {
    @Test
    void execute_shouldCallViewAllBeersOnController() {
        BeerController controller = mock(BeerController.class);
        ViewAllBeersCommand command = new ViewAllBeersCommand(controller);
        command.execute();
        verify(controller, times(1)).viewAllBeers();
    }
}
