package se.jensen.meiying.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jensen.meiying.shop.model.Children;
import se.jensen.meiying.shop.model.Men;
import se.jensen.meiying.shop.model.Product;
import se.jensen.meiying.shop.model.Women;
import se.jensen.meiying.shop.service.ProductService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{articleNumber}")
    public ResponseEntity<Product> getProduct(@PathVariable String articleNumber) {
        return productService.getProductByArticleNumber(articleNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Map<String, Object> body) {
        try {
            String articleNumber = (String) body.get("articleNumber");
            String title = (String) body.get("title");
            double price = Double.parseDouble(body.get("price").toString());
            String description = (String) body.get("description");
            String image = (String) body.get("image");
            String category = (String) body.get("category");

            Product product;
            switch (category.toLowerCase()) {
                case "women" -> product = new Women(articleNumber, title, price, description, image);
                case "men" -> product = new Men(articleNumber, title, price, description, image);
                case "children" -> product = new Children(articleNumber, title, price, description, image);
                default -> {
                    return ResponseEntity.badRequest().body("Invalid category: " + category);
                }
            }

            Product saved = productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid request: " + e.getMessage());
        }
    }

    @DeleteMapping("/{articleNumber}")
    public ResponseEntity<String> deleteProduct(@PathVariable String articleNumber) {
        boolean deleted = productService.deleteProduct(articleNumber);
        if (deleted) {
            return ResponseEntity.ok("The product has been deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No product found with article number: " + articleNumber);
        }
    }
}
