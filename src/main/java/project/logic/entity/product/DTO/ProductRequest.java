package project.logic.entity.product.DTO;

public class ProductRequest {

    private String name;
    private String description;
    private double price;
    private int stock;
    private String categoryName;

    public ProductRequest(String name, String description, double price, int stock, String categoryName) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
