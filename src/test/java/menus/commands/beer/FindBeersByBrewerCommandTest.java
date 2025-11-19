package menus.commands.beer;

import controller.BeerController;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class FindBeersByBrewerCommandTest {
    @Test
    void execute_shouldCallFindBeersCheaperThanOnController() {
        BeerController controller = mock(BeerController.class);
        FindBeersCheaperThanCommand command = new FindBeersCheaperThanCommand(controller);
        command.execute();
        verify(controller, times(1)).findBeersCheaperThan();
    }
}
