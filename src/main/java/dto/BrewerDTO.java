package dto;

import model.Brewer;
import java.util.List;
import java.util.stream.Collectors;

public class BrewerDTO {
    private int id; // optioneel voor updates
    private String name;
    private String location;

    public BrewerDTO() {}

    public BrewerDTO(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    // Zet Brewer naar DTO
    public static BrewerDTO fromEntity(Brewer brewer) {
        return new BrewerDTO(brewer.getId(), brewer.getName(), brewer.getLocation());
    }

    // Zet lijst van Brewer naar lijst van DTO
    public static List<BrewerDTO> fromEntityList(List<Brewer> brewers) {
        return brewers.stream().map(BrewerDTO::fromEntity).collect(Collectors.toList());
    }

    // Zet DTO naar Brewer (zonder beers-lijst)
    public Brewer toEntity() {
        return new Brewer(name, location); // JPA genereert ID automatisch
    }

    // Zet lijst van DTO naar lijst van Brewer
    public static List<Brewer> toEntityList(List<BrewerDTO> dtoList) {
        return dtoList.stream().map(BrewerDTO::toEntity).collect(Collectors.toList());
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}