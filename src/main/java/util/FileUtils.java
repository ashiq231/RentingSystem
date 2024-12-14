package util;

import account.Account;
import product.ProductManager;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    public static void storeUserDetails(Account user) {
        try (FileWriter writer = new FileWriter("user_details.txt")) {
            writer.write("Username: " + user.username + "\n");
            writer.write("Password: " + user.password + "\n");
            writer.write("Balance: $" + user.balance + "\n");
            writer.write("Currently Rented Products:\n");
            for (int index : user.rentedProductIndices) {
                writer.write("- " + ProductManager.getProductByIndex(index).name + "\n");
            }
            System.out.println("User details stored successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while storing user details.");
        }
    }
}
