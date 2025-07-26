package org.ewflorencio.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int idCategory;
    private int idWriter;
    private int idStatus;
    private int nuPages;


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

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdWriter() {
        return idWriter;
    }

    public void setIdWriter(int idWriter) {
        this.idWriter = idWriter;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getNuPages() {
        return nuPages;
    }

    public void setNuPages(int nuPages) {
        this.nuPages = nuPages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idCategory=" + idCategory +
                ", idWriter=" + idWriter +
                ", idStatus=" + idStatus +
                ", nuPages=" + nuPages +
                '}';
    }
}
