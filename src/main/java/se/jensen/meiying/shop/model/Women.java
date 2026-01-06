package se.jensen.meiying.shop.model;

public class Women extends Product {
    public Women(String articleNumber, String title, double price, String description, String image) {
        super(articleNumber, title, price, description, image);
    }

    @Override
    public String category() {
        return "Women";
    }
}
