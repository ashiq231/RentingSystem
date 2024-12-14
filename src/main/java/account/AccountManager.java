package account;

import java.util.ArrayList;

public class AccountManager {
    public static ArrayList<Account> accounts = new ArrayList<>();

    public static boolean registerUser(String username, String password) {
        for (Account account : accounts) {
            if (account.username.equals(username)) {
                return false;  // User already exists
            }
        }
        Account newAccount = new Account(username, password);
        accounts.add(newAccount);
        return true;  // Registration successful
    }

    public static Account loginUser(String username, String password) {
        for (Account account : accounts) {
            if (account.username.equals(username) && account.password.equals(password)) {
                return account;
            }
        }
        return null;  // Invalid credentials
    }
}
