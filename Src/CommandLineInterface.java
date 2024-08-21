import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {

    private static List<Promotion> promotions;
    private static Scanner scanner = new Scanner(System.in);

    public static void start(List<Promotion> loadedPromotions) {
        promotions = loadedPromotions;
        boolean running = true;

        while (running) {
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    InterfaceUtil.sortElevesByPromotion(promotions);
                    break;
                case 2:
                    InterfaceUtil.sortElevesByName(promotions);
                    break;
                case 3:
                    InterfaceUtil.sortElevesByAbsenteeism(promotions);
                    break;
                case 4:
                    InterfaceUtil.listPromotions(promotions);
                    break;
                case 5:
                    InterfaceUtil.searchPromotionByName(promotions, getPromotionName());
                    break;
                case 6:
                    InterfaceUtil.searchEleveByName(promotions, getStudentName());
                    break;
                case 7:
                    adminMenu();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }

        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1. Trier les élèves par promotion");
        System.out.println("2. Trier les élèves par nom");
        System.out.println("3. Trier les élèves par absentéisme");
        System.out.println("4. Liste des promotions");
        System.out.println("5. Rechercher une promotion par nom");
        System.out.println("6. Rechercher un élève par nom");
        System.out.println("7. Menu Admin");
        System.out.println("8. Quitter");
        System.out.print("Choisissez une option: ");
    }

    private static void adminMenu() {
        boolean subMenuRunning = true;

        while (subMenuRunning) {
            displayAdminMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    InterfaceUtil.addEleve(promotions);
                    break;
                case 2:
                    InterfaceUtil.modifyAbsences(promotions);
                    break;
                case 3:
                    InterfaceUtil.deleteEleve(promotions);
                    break;
                case 4:
                    InterfaceUtil.ajouterDelegue(promotions);
                    break;
                case 5:
                    InterfaceUtil.supprimerDelegue(promotions);
                    break;
                case 6:
                    subMenuRunning = false;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private static void displayAdminMenu() {
        System.out.println("\n=== Admin ===");
        System.out.println("1. Ajouter un élève");
        System.out.println("2. Modifier le nombre d'absences d'un élève");
        System.out.println("3. Supprimer un élève");
        System.out.println("4. Ajouter un délégué");
        System.out.println("5. Supprimer un délégué");
        System.out.println("6. Retour au menu principal");
        System.out.print("Choisissez une option: ");
    }

    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            scanner.next();
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private static String getPromotionName() {
        System.out.print("Entrez le nom de la promotion: ");
        return scanner.nextLine();
    }

    private static String getStudentName() {
        System.out.print("Entrez le nom de l'élève: ");
        return scanner.nextLine();
    }
}
