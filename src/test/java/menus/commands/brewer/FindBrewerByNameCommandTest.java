package menus.commands.brewer;

import controller.BrewerController;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class FindBrewerByNameCommandTest {
    @Test
    void execute_shouldCallFindBrewerByNameOnController() {
        BrewerController controller = mock(BrewerController.class);
        FindBrewerByNameCommand command = new FindBrewerByNameCommand(controller);
        command.execute();
        verify(controller, times(1)).findBrewerByName();
    }
}
