package project.rest.product;

import project.logic.entity.product.DTO.ProductRequest;
import project.logic.entity.category.Category;
import project.logic.entity.category.CategoryRepository;
import project.logic.entity.product.Product;
import project.logic.entity.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'USER')")
    public ResponseEntity<List<Product>> getAllProductsWithCategory() {
        List<Product> products = productRepository.findByCategoryName();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build(); // Returns a 204 if there are no products
        }
        return ResponseEntity.ok(products); // Returns a 200 with the list of products
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest request) {

        Optional<Category> categoryObject = Optional.ofNullable(categoryRepository.findByName(request.getCategoryName()));
        if (categoryObject.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category not found");
        }
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setStockQuantity(request.getStock());
        product.setPrice(request.getPrice());
        product.setCategory(categoryObject.get());

        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setStockQuantity(product.getStockQuantity());
                    existingProduct.setCategory(product.getCategory());
                    return productRepository.save(existingProduct);
                })
                .orElseGet(() -> {
                    product.setId(id);
                    return productRepository.save(product);
                });
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
