package menus.commands.beer;

import controller.BeerController;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AddBeerCommandTest {
    @Test
    void execute_shouldCallAddBeerOnController() {
        BeerController controller = mock(BeerController.class);
        AddBeerCommand command = new AddBeerCommand(controller);
        command.execute();
        verify(controller, times(1)).addBeer();
    }
}
