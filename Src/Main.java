import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String PROMOTIONS_FILE = "data/promotions.ser";
    private static final String USERS_FILE = "data/users.ser";

    public static void main(String[] args) {
        List<Promotion> promotions = (List<Promotion>) SerializationUtil.loadObjectFromFile(PROMOTIONS_FILE);
        if (promotions == null) {
            promotions = createSamplePromotions();
        }

        List<User> users = (List<User>) SerializationUtil.loadObjectFromFile(USERS_FILE);
        if (users == null) {
            users = createSampleUsers();
        }

        //login
        User loggedInUser = null;
        while (loggedInUser == null) {
            loggedInUser = LoginUtil.login(users);
        }

        CommandLineInterface.start(promotions);

        SerializationUtil.saveObjectToFile(promotions, PROMOTIONS_FILE);
        SerializationUtil.saveObjectToFile(users, USERS_FILE);
    }

    private static List<Promotion> createSamplePromotions() {
        List<Promotion> promotions = new ArrayList<>();
        Promotion promo1 = new Promotion(1, "Promo Java 2024");
        Promotion promo2 = new Promotion(2, "Promo Python 2024");

        promo1.getApprenants().add(new Apprenant(1, "Doe", "John", "john.doe@example.com", "123456789", 3, false));
        promo2.getApprenants().add(new Apprenant(2, "Smith", "Jane", "jane.smith@example.com", "987654321", 1, false));

        promotions.add(promo1);
        promotions.add(promo2);
        return promotions;
    }

    private static List<User> createSampleUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "a.clain", "admin123"));
        return users;
    }
}
