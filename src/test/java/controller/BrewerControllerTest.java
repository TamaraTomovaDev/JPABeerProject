package controller;

import model.Brewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.BrewerService;
import util.InputUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class BrewerControllerTest {

    private BrewerController brewerController;
    private BrewerService brewerServiceMock;
    private InputUtil inputUtilMock;

    @BeforeEach
    void setUp() throws Exception {
        brewerServiceMock = mock(BrewerService.class);
        inputUtilMock = mock(InputUtil.class);

        // Maak een echte BrewerController met InputUtil mock
        brewerController = new BrewerController(inputUtilMock);

        // Injecteer BrewerService mock via reflection
        Field serviceField = BrewerController.class.getDeclaredField("brewerService");
        serviceField.setAccessible(true);
        serviceField.set(brewerController, brewerServiceMock);
    }

    @Test
    void testAddBrewer() {
        when(inputUtilMock.readString("Naam brouwer: ")).thenReturn("TestBrewer");
        when(inputUtilMock.readString("Locatie: ")).thenReturn("Brussel");

        brewerController.addBrewer();

        verify(brewerServiceMock, times(1)).saveBrewer(any(Brewer.class));
    }

    @Test
    void testViewAllBrewers() {
        Brewer brewer1 = new Brewer();
        brewer1.setName("Brewer1");
        brewer1.setLocation("Brussel");

        Brewer brewer2 = new Brewer();
        brewer2.setName("Brewer2");
        brewer2.setLocation("Antwerpen");

        List<Brewer> brewers = Arrays.asList(brewer1, brewer2);
        when(brewerServiceMock.findAllBrewers()).thenReturn(brewers);

        brewerController.viewAllBrewers();

        verify(brewerServiceMock, times(1)).findAllBrewers();
    }

    @Test
    void testFindBrewerById() {
        when(inputUtilMock.readInt("ID: ")).thenReturn(1);

        Brewer brewer = new Brewer();
        brewer.setName("TestBrewer");
        brewer.setLocation("Brussel");

        when(brewerServiceMock.findBrewerById(1)).thenReturn(brewer);

        brewerController.findBrewerById();

        verify(brewerServiceMock, times(1)).findBrewerById(1);
    }

    @Test
    void testUpdateBrewer() {
        when(inputUtilMock.readInt("ID van brouwer: ")).thenReturn(1);

        Brewer existing = new Brewer();
        existing.setName("OldName");
        existing.setLocation("OldLocation");

        when(brewerServiceMock.findBrewerById(1)).thenReturn(existing);

        when(inputUtilMock.readString("Nieuwe naam: ")).thenReturn("NewName");
        when(inputUtilMock.readString("Nieuwe locatie: ")).thenReturn("NewLocation");

        brewerController.updateBrewer();

        verify(brewerServiceMock, times(1)).updateBrewer(existing);
    }

    @Test
    void testDeleteBrewer() {
        when(inputUtilMock.readInt("ID: ")).thenReturn(1);

        brewerController.deleteBrewer();

        verify(brewerServiceMock, times(1)).deleteBrewer(1);
    }

    @Test
    void testFindBrewerByName() {
        when(inputUtilMock.readString("Naam brouwer: ")).thenReturn("TestBrewer");

        Brewer brewer = new Brewer();
        brewer.setName("TestBrewer");
        brewer.setLocation("Brussel");

        when(brewerServiceMock.findBrewerByName("TestBrewer")).thenReturn(brewer);

        brewerController.findBrewerByName();

        verify(brewerServiceMock, times(1)).findBrewerByName("TestBrewer");
    }

    @Test
    void testViewBrewersWithBeerCount() {
        List<Object[]> results = Arrays.asList(
                new Object[]{"Brewer1", 5},
                new Object[]{"Brewer2", 3}
        );
        when(brewerServiceMock.findAllBrewersWithBeerCount()).thenReturn(results);

        brewerController.viewBrewersWithBeerCount();

        verify(brewerServiceMock, times(1)).findAllBrewersWithBeerCount();
    }
}
