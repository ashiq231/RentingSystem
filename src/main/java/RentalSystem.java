import account.AccountManager;
import product.Product;
import product.ProductManager;
import account.Account;

import java.util.Scanner;

import static notification.Notifications.addNotification;
import static notification.Notifications.showNotifications;
import static product.ProductManager.products;
import static util.FileUtils.storeUserDetails;

public class RentalSystem {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        int loggedInUserId;

        // Sample products
        ProductManager.addProduct("LAPTOP", "Electronics", 50.0f);
        ProductManager.addProduct("BOOK", "Education", 10.0f);
        ProductManager.addProduct("BICYCLE", "Sports", 20.0f);

        while (running) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loggedInUserId = loginUser();
                    if (loggedInUserId != -1) {
                        rentingSystemMenu(AccountManager.accounts.get(loggedInUserId));
                    }
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    static void registerUser() {
        System.out.print("Enter a new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a new password: ");
        String password = scanner.nextLine();

        if (AccountManager.registerUser(username, password)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("User already exists. Try a different username.");
        }
    }

    static int loginUser() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Account account = AccountManager.loginUser(username, password);
        if (account != null) {
            System.out.println("Login successful!");
            return AccountManager.accounts.indexOf(account);
        } else {
            System.out.println("Invalid username or password.");
            return -1;
        }
    }

    static void rentingSystemMenu(Account user) {
        boolean running = true;

        while (running) {
            System.out.println("1. Take Rent");
            System.out.println("2. Give Rent");
            System.out.println("3. Search");
            System.out.println("4. Notifications");
            System.out.println("5. Account");
            System.out.println("6. Store");
            System.out.println("7. Store User Details");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    takeRent(user);
                    break;
                case 2:
                    giveRent();
                    break;
                case 3:
                    search();
                    break;
                case 4:
                    showNotifications();
                    break;
                case 5:
                    Account.accountMenu(user, scanner);
                    break;
                case 6:
                    store();
                    break;
                case 7:
                    storeUserDetails(user);
                    break;
                case 8:
                    running = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        }
    }

    static void giveRent() {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter product category: ");
        String category = scanner.nextLine();
        System.out.print("Enter rent price: ");
        float price = scanner.nextFloat();
        scanner.nextLine();  // Consume newline

        products.add(new Product(productName, category, price));
        addNotification("Added " + productName + " to the store");
    }

    static void takeRent(Account user) {
        if (user.rentedProductIndices.size() >= 5) {
            System.out.println("You have reached the maximum number of rented products.");
            return;
        }

        System.out.println("Select product to rent:");
        for (int i = 0; i < products.size(); i++) {
            if (!products.get(i).isRented) {
                System.out.println((i + 1) + ". " + products.get(i).name + " - $" + products.get(i).price);
            }
        }

        int productChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter renting time (in hours): ");
        int rentingTime = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        float totalPrice = rentingTime * products.get(productChoice - 1).price;
        System.out.println("Total payment: $" + totalPrice);

        user.rentedProductIndices.add(productChoice - 1);
        products.get(productChoice - 1).isRented = true;

        addNotification("Rented " + products.get(productChoice - 1).name + " for $" + totalPrice);
    }

    static void search() {
        System.out.print("Enter product name to search: ");
        String searchTerm = scanner.nextLine();

        boolean found = false;
        for (Product product : products) {
            if (product.name.toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println("Found: " + product.name + " - $" + product.price);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No products found with that name.");
        }
    }
    static void store() {
        ProductManager.listAvailableProducts();

        ProductManager.listRentedProducts();
    }
}
