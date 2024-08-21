import java.util.List;
import java.util.Scanner;

public class LoginUtil {

    public static User login(List<User> users) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Login ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                System.out.println("Login successful! Welcome, " + user.getUsername() + ".");
                return user;
            }
        }

        System.out.println("Invalid username or password.");
        return null;
    }
}
