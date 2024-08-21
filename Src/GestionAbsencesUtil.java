import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class GestionAbsencesUtil {

    private static final String PARAMETERS_FILE_PATH = "data/Parameters.txt";

    private static int getNextId(String key) {
        int id = 1;
        File file = new File(PARAMETERS_FILE_PATH);

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                char[] buffer = new char[(int) file.length()];
                reader.read(buffer);
                String content = new String(buffer);
                String[] lines = content.split("\n");

                for (String line : lines) {
                    if (line.contains(key)) {
                        String[] parts = line.split("=");
                        if (parts.length > 1) {
                            id = Integer.parseInt(parts[1].replace(";", "").trim());
                        }
                        break;
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Erreur lors de la lecture du fichier des paramètres, l'ID commencera à 1.");
            }
        }

        return id;
    }

    private static void updateNextId(String key, int nextId) {
        File file = new File(PARAMETERS_FILE_PATH);
        StringBuilder content = new StringBuilder();

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                char[] buffer = new char[(int) file.length()];
                reader.read(buffer);
                String fileContent = new String(buffer);
                String[] lines = fileContent.split("\n");

                for (String line : lines) {
                    if (line.contains(key)) {
                        content.append(key).append("=").append(nextId).append(";\n");
                    } else {
                        content.append(line).append("\n");
                    }
                }
            } catch (IOException e) {
                System.out.println("Erreur lors de la lecture du fichier des paramètres.");
            }
        }

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content.toString());
        } catch (IOException e) {
            System.out.println("Erreur lors de la mise à jour du fichier des paramètres.");
        }
    }

    public static int getNextPromotionId() {
        int nextId = getNextId("nextPromotionId");
        updateNextId("nextPromotionId", nextId + 1);
        return nextId;
    }

    public static int getNextApprenantId() {
        int nextId = getNextId("nextApprenantId");
        updateNextId("nextApprenantId", nextId + 1);
        return nextId;
    }

    public static int getNextUserId() {
        int nextId = getNextId("nextUserId");
        updateNextId("nextUserId", nextId + 1);
        return nextId;
    }
    public static void addApprenant(Promotion promotion, Apprenant apprenant) {
        promotion.getApprenants().add(apprenant);
    }

    public static void modifyAbsences(Apprenant apprenant, int nbAbsences) {
        apprenant.setNbAbsences(nbAbsences);
    }
    
    public static void setDelegueStatus(Apprenant apprenant, boolean isDelegue) {
        apprenant.setDelegue(isDelegue);
    }

    public static void removeApprenant(Promotion promotion, Apprenant apprenant) {
        promotion.getApprenants().remove(apprenant);
    }

    public static List<Apprenant> sortApprenantsByName(Promotion promotion) {
        List<Apprenant> apprenants = promotion.getApprenants();
        apprenants.sort(Comparator.comparing(Apprenant::getNom, String.CASE_INSENSITIVE_ORDER));
        return apprenants;
    }

    public static List<Apprenant> sortApprenantsByAbsences(Promotion promotion) {
        List<Apprenant> apprenants = promotion.getApprenants();
        apprenants.sort(Comparator.comparingInt(Apprenant::getNbAbsences).reversed());
        return apprenants;
    }
}
