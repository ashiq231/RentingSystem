package notification;

import java.util.ArrayList;

public class Notifications {
    public static ArrayList<String> notifications = new ArrayList<>();

    public static void addNotification(String message) {
        notifications.add(message);
    }

    public static void showNotifications() {
        System.out.println("Notifications:");
        for (String notification : notifications) {
            System.out.println(notification);
        }
    }
}
