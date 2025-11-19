package menus.commands.brewer;

import controller.BrewerController;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AddBrewerCommandTest {
    @Test
    void execute_shouldCallAddBrewerOnController() {
        BrewerController controller = mock(BrewerController.class);
        AddBrewerCommand command = new AddBrewerCommand(controller);
        command.execute();
        verify(controller, times(1)).addBrewer();
    }
}
