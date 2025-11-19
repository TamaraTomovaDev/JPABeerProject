package menus.commands.brewer;

import controller.BrewerController;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class DeleteBrewerCommandTest {
    @Test
    void execute_shouldCallDeleteBrewerOnController() {
        BrewerController controller = mock(BrewerController.class);
        DeleteBrewerCommand command = new DeleteBrewerCommand(controller);
        command.execute();
        verify(controller, times(1)).deleteBrewer();
    }
}
