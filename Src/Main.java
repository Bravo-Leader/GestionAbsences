import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Promotion> promotions = new ArrayList<>();

        Promotion promo1 = new Promotion(GestionAbsencesUtil.getNextPromotionId(), "Promo Java 2024");
        Promotion promo2 = new Promotion(GestionAbsencesUtil.getNextPromotionId(), "Promo Python 2024");

        promo1.getApprenants().add(new Apprenant(GestionAbsencesUtil.getNextApprenantId(), "Doe", "John", "john.doe@example.com", "123456789", 3, false));
        promo2.getApprenants().add(new Apprenant(GestionAbsencesUtil.getNextApprenantId(), "Smith", "Jane", "jane.smith@example.com", "987654321", 1, false));

        promotions.add(promo1);
        promotions.add(promo2);

        CommandLineInterface.start(promotions);
    }
}
