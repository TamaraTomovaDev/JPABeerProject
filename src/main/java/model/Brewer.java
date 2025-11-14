package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "brewers")
public class Brewer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;


    @OneToMany(mappedBy = "brewer")
    private List<Beer> beers;

    public Brewer() {
    }

    public Brewer(String name, String location, List<Beer> beers) {
        this.name = name;
        this.location = location;
        this.beers = beers;
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
    public String toString() {
        return "Brewer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", beers=" + beers +
                '}';
    }
}
