package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "brewers")
public class Brewer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;

    private String location;

    @OneToMany(mappedBy = "brewer")
    private List<Beer> beers;

    public Brewer() {
    }

    public Brewer(int id) {
        this.id = id;
    }

    public Brewer(String name, String location, List<Beer> beers) {
        this.name = name;
        this.location = location;
        this.beers = beers;
    }

    public Brewer(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brewer)) return false;
        Brewer brewer = (Brewer) o;
        return id == brewer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "Brewer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", beersCount=" + (beers != null ? beers.size() : 0) +
                '}';
    }
}
