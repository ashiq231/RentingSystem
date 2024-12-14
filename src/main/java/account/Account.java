package account;

import java.util.ArrayList;
import java.util.Scanner;

import static account.AccountActions.viewAccountInfo;
import static notification.Notifications.addNotification;
import static product.ProductManager.products;

public class Account {
    public String username;
    public String password;
    public float balance;
    public ArrayList<Integer> rentedProductIndices = new ArrayList<>();

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0f;
    }

    // Account menu for user options
    public static void accountMenu(Account user, Scanner scanner) {
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("\nAccount Menu:");
            System.out.println("1. View Account Info");
            System.out.println("2. Change Password");
            System.out.println("3. Cancel Renting");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewAccountInfo(user);
                    validChoice = true;
                    break;
                case 2:
                    changePassword(user, scanner);
                    validChoice = true;
                    break;
                case 3:
                    cancelRenting(user, scanner);
                    validChoice = true;
                    break;
                case 4:
                    validChoice = true;
                    System.out.println("Exiting account menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid number.");
            }
        }
    }

    static void changePassword(Account user, Scanner scanner) {
        boolean validPassword = false;

        while (!validPassword) {
            System.out.print("Enter old password: ");
            String oldPassword = scanner.nextLine();

            if (user.password.equals(oldPassword)) {
                System.out.print("Enter new password: ");
                user.password = scanner.nextLine();
                System.out.println("Password changed successfully!");
                validPassword = true;
            } else {
                System.out.println("Incorrect old password. Try again.");
            }
        }
    }

    // Cancel renting a product
    static void cancelRenting(Account user, Scanner scanner) {
        if (user.rentedProductIndices.isEmpty()) {
            System.out.println("No product is currently rented.");
            return;
        }

        System.out.println("\nCurrently Rented Products:");
        for (int i = 0; i < user.rentedProductIndices.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(user.rentedProductIndices.get(i)).name);
        }

        boolean validCancelChoice = false;

        while (!validCancelChoice) {
            System.out.print("Enter the number of the product you want to cancel: ");
            int cancelChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (cancelChoice < 1 || cancelChoice > user.rentedProductIndices.size()) {
                System.out.println("Invalid choice. Please enter a valid number.");
            } else {
                int cancelIndex = user.rentedProductIndices.get(cancelChoice - 1);
                products.get(cancelIndex).isRented = false;
                user.rentedProductIndices.remove(cancelChoice - 1);

                addNotification("Canceled renting of " + products.get(cancelIndex).name);
                System.out.println("Renting of " + products.get(cancelIndex).name + " has been canceled.");
                validCancelChoice = true;
            }
        }
    }
}
