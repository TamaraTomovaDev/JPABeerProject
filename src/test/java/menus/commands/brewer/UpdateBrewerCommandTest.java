package menus.commands.brewer;

import controller.BrewerController;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class UpdateBrewerCommandTest {
    @Test
    void execute_shouldCallUpdateBrewerOnController() {
        BrewerController controller = mock(BrewerController.class);
        UpdateBrewerCommand command = new UpdateBrewerCommand(controller);
        command.execute();
        verify(controller, times(1)).updateBrewer();
    }
}
