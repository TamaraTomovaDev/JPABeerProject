package menus.commands.brewer;

import controller.BrewerController;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class FindBrewerByIdCommandTest {
    @Test
    void execute_shouldCallFindBrewerByIdOnController() {
        BrewerController controller = mock(BrewerController.class);
        FindBrewerByIdCommand command = new FindBrewerByIdCommand(controller);
        command.execute();
        verify(controller, times(1)).findBrewerById();
    }
}
