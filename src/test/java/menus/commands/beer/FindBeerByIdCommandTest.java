package menus.commands.beer;

import controller.BeerController;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class FindBeerByIdCommandTest {
    @Test
    void execute_shouldCallFindBeerByIdOnController() {
        BeerController controller = mock(BeerController.class);
        FindBeerByIdCommand command = new FindBeerByIdCommand(controller);
        command.execute();
        verify(controller, times(1)).findBeerById();
    }
}
