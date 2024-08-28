package se.systementor.javasecstart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="Dog")
public class Dog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="Id", length = 50)
    private int id;

    @Column(name="Age", length = 50)
    private String age;

    @Column(name="Gender", length = 50)
    private String gender;

    @Column(name = "Breed", length = 50)
    private String breed;

    @Column(name="SoldTo", length = 50)
    private String soldTo;


    @Column(name="Price", length = 50)
    private int price;

    @Column(name="Name", length = 50)
    private String name;

    @Column(name="Size", length = 50)
    private String size;


    @Column(name="Image", length = 50)
    private String image;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String hender) {
        this.gender = hender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSoldTo() {
        return soldTo;
    }

    public void setSoldTo(String soldTo) {
        this.soldTo = soldTo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}