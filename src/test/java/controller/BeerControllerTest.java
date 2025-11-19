package controller;

import model.Beer;
import model.Brewer;
import model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.BeerService;
import util.InputUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class BeerControllerTest {

    private BeerController beerController;
    private BeerService beerServiceMock;
    private InputUtil inputUtilMock;

    @BeforeEach
    void setUp() throws Exception {
        beerServiceMock = mock(BeerService.class);
        inputUtilMock = mock(InputUtil.class);

        // Maak een echte BeerController met InputUtil mock
        beerController = new BeerController(inputUtilMock);

        // Injecteer BeerService mock via reflection
        Field serviceField = BeerController.class.getDeclaredField("beerService");
        serviceField.setAccessible(true);
        serviceField.set(beerController, beerServiceMock);
    }

    @Test
    void testAddBeer() {
        when(inputUtilMock.readString("Naam: ")).thenReturn("TestBeer");
        when(inputUtilMock.readDouble("Alcoholpercentage: ")).thenReturn(5.0);
        when(inputUtilMock.readDouble("Prijs: ")).thenReturn(2.5);
        when(inputUtilMock.readInt("Brewer ID: ")).thenReturn(1);
        when(inputUtilMock.readInt("Category ID: ")).thenReturn(2);

        beerController.addBeer();

        verify(beerServiceMock, times(1)).saveBeer(any(Beer.class));
    }

    @Test
    void testFindBeerById() {
        when(inputUtilMock.readInt("ID: ")).thenReturn(1);
        Beer beer = new Beer("TestBeer", 5.0, 2.5, new Brewer(1), new Category(2));
        when(beerServiceMock.findBeerById(1)).thenReturn(beer);

        beerController.findBeerById();

        verify(beerServiceMock, times(1)).findBeerById(1);
    }

    @Test
    void testViewAllBeers() {
        List<Beer> beers = Arrays.asList(
                new Beer("Beer1", 5.0, 2.5, new Brewer(1), new Category(2)),
                new Beer("Beer2", 6.0, 3.0, new Brewer(2), new Category(3))
        );
        when(beerServiceMock.findAllBeers()).thenReturn(beers);

        beerController.viewAllBeers();

        verify(beerServiceMock, times(1)).findAllBeers();
    }
}
