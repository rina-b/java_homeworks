package ua.com.alevel.entity;

import ua.com.alevel.annotation.CSVMapp;

public class BookShop {
    @CSVMapp("id")
    private int id;
    @CSVMapp("name")
    private String name;
    @CSVMapp("author")
    private String author;
    @CSVMapp("dateOfCreation")
    private String dateOfCreation;
    @CSVMapp("numberOfBooks")
    private int numberOfBooks;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    @Override
    public String toString() {
        return "BookShop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", dateOfCreation='" + dateOfCreation + '\'' +
                ", numberOfBooks=" + numberOfBooks +
                '}';
    }
}
