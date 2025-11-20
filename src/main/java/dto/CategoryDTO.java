package dto;

import model.Category;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryDTO {
    private int id;
    private String name;
    private String description;

    // Lege constructor
    public CategoryDTO() {}

    // Constructor met parameters
    public CategoryDTO(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Zet Category naar DTO
    public static CategoryDTO fromEntity(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getDescription());
    }

    // Zet lijst van Category naar lijst van DTO
    public static List<CategoryDTO> fromEntityList(List<Category> categories) {
        return categories.stream()
                .map(CategoryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // Zet DTO naar Category (zonder beers-lijst)
    public Category toEntity() {
        return new Category(name, description); // ID wordt door JPA gegenereerd
    }

    // Zet lijst van DTO naar lijst van Category
    public static List<Category> toEntityList(List<CategoryDTO> dtoList) {
        return dtoList.stream()
                .map(CategoryDTO::toEntity)
                .collect(Collectors.toList());
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}