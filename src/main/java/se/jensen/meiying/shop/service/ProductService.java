package se.jensen.meiying.shop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import se.jensen.meiying.shop.model.Product;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private static final String FILE_PATH = "products.json";
    private List<Product> products;
    private ObjectMapper objectMapper;

    public ProductService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.products = loadProducts();
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Optional<Product> getProductByArticleNumber(String articleNumber) {
        return products.stream()
                .filter(p -> p.getArticleNumber().equalsIgnoreCase(articleNumber))
                .findFirst();
    }

    public Product addProduct(Product product) {
        products.add(product);
        saveProducts();
        return product;
    }

    public boolean deleteProduct(String articleNumber) {
        boolean removed = products.removeIf(p ->
                p.getArticleNumber().equalsIgnoreCase(articleNumber)
        );
        if (removed) {
            saveProducts();
        }
        return removed;
    }

    private List<Product> loadProducts() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                Product[] productArray = objectMapper.readValue(file, Product[].class);
                return new ArrayList<>(Arrays.asList(productArray));
            }
        } catch (IOException e) {
            System.err.println("Error loading products: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    private void saveProducts() {
        try {
            objectMapper.writeValue(new File(FILE_PATH), products);
        } catch (IOException e) {
            System.err.println("Error saving products: " + e.getMessage());
        }
    }
}