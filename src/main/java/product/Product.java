package product;

public class Product {
    public String name;
    public String category;
    public float price;
    public boolean isRented;

    public Product(String name, String category, float price) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.isRented = false;
    }
}
