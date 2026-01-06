package se.jensen.meiying.shop.model;

public class Men extends Product {
    public Men(String articleNumber, String title, double price, String description, String image) {
        super(articleNumber, title, price, description, image);
    }

    @Override
    public String category() {
        return "Men";
    }
}
