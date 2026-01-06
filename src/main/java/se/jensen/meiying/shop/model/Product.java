package se.jensen.meiying.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Product {
    private String articleNumber;
    private String title;
    private double price;
    private String description;
    private String image;

    public Product(String articleNumber, String title, double price, String description, String image) {
        this.articleNumber = articleNumber;
        this.title = title;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    @JsonProperty("category")
    public abstract String category();
}
