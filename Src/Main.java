import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Initialize some test data
        List<Promotion> promotions = new ArrayList<>();

        Promotion promo1 = new Promotion(GestionAbsencesUtil.getNextPromotionId(), "Promo Java 2024");
        Promotion promo2 = new Promotion(GestionAbsencesUtil.getNextPromotionId(), "Promo Python 2024");

        // Add some students to these promotions (though this won't be executed yet)
        promo1.getApprenants().add(new Apprenant(GestionAbsencesUtil.getNextApprenantId(), "Doe", "John", "john.doe@example.com", "123456789", 3, false));
        promo2.getApprenants().add(new Apprenant(GestionAbsencesUtil.getNextApprenantId(), "Smith", "Jane", "jane.smith@example.com", "987654321", 1, false));

        // Add promotions to the list
        promotions.add(promo1);
        promotions.add(promo2);

        // Start the command line interface with the loaded promotions
        CommandLineInterface.start(promotions);
    }
}
