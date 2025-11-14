package model;

import jakarta.persistence.*;

@Entity
@Table(name = "beers")
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double alcoholPercentage;
    private double price;

    @ManyToOne
    @JoinColumn(name = "brewerId")
    private Brewer brewer;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    public Beer() {
    }

    public Beer(String name, double alcoholPercentage, double price, Brewer brewer, Category category) {
        this.name = name;
        this.alcoholPercentage = alcoholPercentage;
        this.price = price;
        this.brewer = brewer;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Brewer getBrewer() {
        return brewer;
    }

    public void setBrewer(Brewer brewer) {
        this.brewer = brewer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alcoholPercentage=" + alcoholPercentage +
                ", price=" + price +
                ", brewer=" + brewer +
                ", category=" + category +
                '}';
    }
}
