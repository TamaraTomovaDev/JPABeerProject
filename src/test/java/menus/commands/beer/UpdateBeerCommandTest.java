package menus.commands.beer;

import controller.BeerController;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class UpdateBeerCommandTest {
    @Test
    void execute_shouldCallUpdateBeerOnController() {
        BeerController controller = mock(BeerController.class);
        UpdateBeerCommand command = new UpdateBeerCommand(controller);
        command.execute();
        verify(controller, times(1)).updateBeer();
    }
}
