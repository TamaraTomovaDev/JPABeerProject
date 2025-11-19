package dto;

import model.Beer;
import model.Brewer;
import model.Category;

import java.util.List;
import java.util.stream.Collectors;

public class BeerDTO {
    private String name;
    private double alcoholPercentage;
    private double price;
    private int brewerId;
    private int categoryId;

    public BeerDTO() {
    }

    public BeerDTO(String name, double alcoholPercentage, double price, int brewerId, int categoryId) {
        this.name = name;
        this.alcoholPercentage = alcoholPercentage;
        this.price = price;
        this.brewerId = brewerId;
        this.categoryId = categoryId;
    }

    /**
     * ✅ Zet Beer naar BeerDTO
     */
    public static BeerDTO fromEntity(Beer beer) {
        return new BeerDTO(
                beer.getName(),
                beer.getAlcoholPercentage(),
                beer.getPrice(),
                beer.getBrewer().getId(),
                beer.getCategory().getId()
        );
    }

    /**
     * ✅ Zet lijst van Beer naar lijst van BeerDTO
     */
    public static List<BeerDTO> fromEntityList(List<Beer> beers) {
        return beers.stream()
                .map(BeerDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * ✅ Zet BeerDTO naar Beer (met Brewer en Category)
     */
    public Beer toEntity(Brewer brewer, Category category) {
        return new Beer(name, alcoholPercentage, price, brewer, category);
    }

    /**
     * ✅ Zet lijst van BeerDTO naar lijst van Beer
     */
    public static List<Beer> toEntityList(List<BeerDTO> dtoList, List<Brewer> brewers, List<Category> categories) {
        return dtoList.stream()
                .map(dto -> {
                    Brewer brewer = brewers.stream()
                            .filter(b -> b.getId() == dto.brewerId)
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Brouwer niet gevonden: " + dto.brewerId));

                    Category category = categories.stream()
                            .filter(c -> c.getId() == dto.categoryId)
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Categorie niet gevonden: " + dto.categoryId));

                    return dto.toEntity(brewer, category);
                })
                .collect(Collectors.toList());
    }

    // ✅ Getters en setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBrewerId() {
        return brewerId;
    }

    public void setBrewerId(int brewerId) {
        this.brewerId = brewerId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}