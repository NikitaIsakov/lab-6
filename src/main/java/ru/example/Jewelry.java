package ru.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "jewelry_store")
public class Jewelry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название изделия не должно быть пустым")
    @Size(max = 100, message = "Название изделия не должно превышать 100 символов")
    private String name;

    @NotBlank(message = "Материал не должен быть пустым")
    @Size(max = 50, message = "Материал не должен превышать 50 символов")
    private String material;

    @Size(max = 255, message = "Описание не должно превышать 255 символов")
    private String description;

    @NotNull(message = "Вес изделия обязателен")
    @DecimalMin(value = "0.1", message = "Вес изделия должен быть больше 0")
    private double weight;

    @NotNull(message = "Цена изделия обязательна")
    @DecimalMin(value = "0.1", message = "Цена изделия должна быть больше 0")
    private double price;

    public Jewelry() {}

    public Jewelry(String name, String material, String description, double weight, double price) {
        this.name = name;
        this.material = material;
        this.description = description;
        this.weight = weight;
        this.price = price;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getMaterial() { return material; }
    public String getDescription() { return description; }
    public double getWeight() { return weight; }
    public double getPrice() { return price; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setMaterial(String material) { this.material = material; }
    public void setDescription(String description) { this.description = description; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Украшение [id=" + id + ", название=" + name + ", материал=" + material +
                ", описание=" + description + ", вес=" + weight + ", цена=" + price + "]";
    }
}
