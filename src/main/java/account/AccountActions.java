package account;

import product.ProductManager;

public class AccountActions {

    public static void viewAccountInfo(Account user) {
        System.out.println("Username: " + user.username);
        System.out.println("Balance: $" + user.balance);
        System.out.println("Currently Rented Products:");
        for (int index : user.rentedProductIndices) {
            System.out.println("- " + ProductManager.getProductByIndex(index).name);
        }
    }

}
