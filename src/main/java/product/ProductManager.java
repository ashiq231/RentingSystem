package product;

import java.util.ArrayList;

public class ProductManager {
    public static ArrayList<Product> products = new ArrayList<>();

    public static void addProduct(String name, String category, float price) {
        Product product = new Product(name, category, price);
        products.add(product);
    }

    public static Product getProductByIndex(int index) {
        return products.get(index);
    }

    public static void listAvailableProducts() {
        System.out.println("Available Products:");
        for (Product product : products) {
            if (!product.isRented) {
                System.out.println("- " + product.name + " (" + product.category + ") - $" + product.price);
            }
        }
    }

    public static void listRentedProducts() {
        System.out.println("Rented Products:");
        for (Product product : products) {
            if (product.isRented) {
                System.out.println("- " + product.name + " (" + product.category + ") - $" + product.price);
            }
        }
    }
}
