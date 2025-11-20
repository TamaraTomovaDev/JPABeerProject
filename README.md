# 🍺 Beer Brewery Management System

**Technologieën:** Java, JPA/Hibernate, MySQL (H2 optioneel), Jackson, SLF4J, JUnit 5
**Domein:** Beheer van bieren, brouwers en categorieën

---

## Overzicht

Console-gebaseerde Java applicatie met JPA/Hibernate (zonder Spring), met duidelijke lagenarchitectuur:

* Entities
* Repositories (inclusief BaseRepository)
* Services (businesslogica + validatie)
* Controllers
* Menus + Command Pattern (BeerMenu, BrewerMenu, CategoryMenu)
* DTO’s
* JSON import/export
* Logging
* Unit tests

Gebruikers kunnen bieren, brouwers en categorieën beheren via een overzichtelijk menu.

---

## Doel van het project

* Werken met EntityManager, transacties, JPQL en JPA-relaties
* Correcte lagenarchitectuur met BaseRepository + Repository + Service + Controller
* OneToMany en ManyToOne implementaties
* DTO’s en JSON import/export
* Command Pattern voor menu-acties
* Validatie en gebruikersinput afhandeling
* Unit tests voor commands en controllers

---

## Functionaliteiten

### Beer

* CRUD
* Zoeken op categorie, brouwer, prijs
* Validatie: naam niet leeg, alcohol ≥ 0, prijs > 0
* JSON import/export

### Brewer

* CRUD
* Zoeken op naam
* Ophalen brouwers met biercount
* Validatie: naam & locatie verplicht
* JSON import/export

### Category

* CRUD
* Zoeken op naam
* Kan niet verwijderd worden zolang er bieren aan gekoppeld zijn
* JSON import/export

### Extra

* Command Pattern voor alle menu-opties
* DTO’s voor veilige JSON-input/output
* ConfigUtil voor dynamische JSON-paden
* SLF4J Logging
* Unit tests voor commands en controllers

---

## Technologieën

* Java
* JPA / Hibernate
* MySQL (H2 optioneel)
* Jackson voor JSON
* SLF4J voor logging
* JUnit 5 voor unit tests

---

## Projectstructuur

```
src/
 ├── app/
 │     └── MainApp.java
 ├── config/
 │     └── JpaConfig.java
 ├── controller/
 │     ├── BeerController.java
 │     ├── BrewerController.java
 │     └── CategoryController.java
 ├── dto/
 │     ├── BeerDTO.java
 │     ├── BrewerDTO.java
 │     └── CategoryDTO.java
 ├── menus/
 │     ├── MenuManager.java
 │     ├── BeerMenu.java
 │     ├── BrewerMenu.java
 │     ├── CategoryMenu.java
 │     └── commands/
 │            ├── beer/
 │            ├── brewer/
 │            └── category/
 ├── model/
 │     ├── Beer.java
 │     ├── Brewer.java
 │     └── Category.java
 ├── repository/
 │     ├── BaseRepository.java
 │     ├── BeerRepository.java
 │     ├── BrewerRepository.java
 │     └── CategoryRepository.java
 ├── service/
 │     ├── BeerService.java
 │     ├── BrewerService.java
 │     └── CategoryService.java
 ├── util/
 │     ├── ConfigUtil.java
 │     ├── InputUtil.java
 │     └── JpaExecutor.java
 ├── resources/
 │     ├── META-INF/persistence.xml
 │     └── data/
 │            ├── beers.json
 │            ├── brewers.json
 │            └── categories.json
 └── test/
       ├── controller/
       └── commands/
```

---

## Best Practices

### Entities

* Correcte JPA-annotaties
* Relaties: @OneToMany(mappedBy = "...", cascade = CascadeType.NONE), @ManyToOne(optional = false)
* Validatie: @NotBlank, @Positive, @NotNull
* equals() & hashCode() via ID

### Repository Layer

* BaseRepository als generieke basis
* CRUD-methodes: create, findById, findAll, update, delete
* Extra queries: findBeersByCategory, findBrewerByName, beerCount

### Service Layer

* Validatie en business rules
* JSON import/export via DTO’s
* Werkt via JpaExecutor voor nette transacties

### Console UI

* Menu per entiteit: BeerMenu, BrewerMenu, CategoryMenu
* Command Pattern: AddBeerCommand, DeleteBrewerCommand, …
* Exception-handling
* Input-validator

### JSON Import/Export

* Bestanden in resources/data/
* Voorbeeld beer.json:

```json
[
  {
    "name": "Duvel",
    "alcoholPercentage": 8.5,
    "price": 2.8,
    "brewerId": 1,
    "categoryId": 2
  }
]
```

### Unit Tests

* Controllers
* Commands
* Menus
* Services (optioneel uitbreidbaar)

---

## Conclusie

JPA-systeem met duidelijke lagenarchitectuur, JSON-functionaliteit, menu’s, commands en goede programmeerpraktijken. Perfect als basis voor Spring Boot, REST-API’s of webapplicaties.
