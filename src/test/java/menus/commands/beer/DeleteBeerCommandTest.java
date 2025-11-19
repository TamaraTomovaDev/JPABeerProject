package menus.commands.beer;

import controller.BeerController;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class DeleteBeerCommandTest {
    @Test
    void execute_shouldCallDeleteBeerOnController() {
        BeerController controller = mock(BeerController.class);
        DeleteBeerCommand command = new DeleteBeerCommand(controller);
        command.execute();
        verify(controller, times(1)).deleteBeer();
    }
}
