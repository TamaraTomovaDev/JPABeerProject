package menus.commands.beer;

import controller.BeerController;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class FindBeersByCategoryCommandTest {
    @Test
    void execute_shouldCallFindBeersByCategoryOnController() {
        BeerController controller = mock(BeerController.class);
        FindBeersByCategoryCommand command = new FindBeersByCategoryCommand(controller);
        command.execute();
        verify(controller, times(1)).findBeersByCategory();
    }
}
