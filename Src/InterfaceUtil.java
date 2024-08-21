import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InterfaceUtil {

    private static Scanner scanner = new Scanner(System.in);

    public static void sortElevesByPromotion(List<Promotion> promotions) {
        promotions.forEach(promo -> {
            System.out.println("\nPromotion: " + promo.getNom());
            displayApprenants(promo.getApprenants());
        });
    }

    public static void sortElevesByName(List<Promotion> promotions) {
        List<Apprenant> allApprenants = promotions.stream()
                .flatMap(promo -> promo.getApprenants().stream())
                .sorted((a1, a2) -> a1.getNom().compareToIgnoreCase(a2.getNom()))
                .collect(Collectors.toList());

        displayApprenants(allApprenants);
    }

    public static void sortElevesByAbsenteeism(List<Promotion> promotions) {
        List<Apprenant> allApprenants = promotions.stream()
                .flatMap(promo -> promo.getApprenants().stream())
                .sorted((a1, a2) -> Integer.compare(a2.getNbAbsences(), a1.getNbAbsences()))
                .collect(Collectors.toList());

        displayApprenants(allApprenants);
    }

    public static void listPromotions(List<Promotion> promotions) {
        System.out.println("\n=== Liste des Promotions ===");
        for (Promotion promo : promotions) {
            double averageAbsenteeism = calculateAverageAbsenteeism(promo);
            System.out.printf("Promotion: %s, Moyenne d'absentéisme: %.2f\n", promo.getNom(), averageAbsenteeism);
        }
    }

    private static double calculateAverageAbsenteeism(Promotion promotion) {
        List<Apprenant> apprenants = promotion.getApprenants();
        if (apprenants.isEmpty()) {
            return 0.0;
        }

        int totalAbsences = 0;
        for (Apprenant apprenant : apprenants) {
            totalAbsences += apprenant.getNbAbsences();
        }

        return (double) totalAbsences / apprenants.size();
    }

    public static void searchPromotionByName(List<Promotion> promotions, String promotionName) {
        Optional<Promotion> promotion = promotions.stream()
                .filter(promo -> promo.getNom().equalsIgnoreCase(promotionName))
                .findFirst();

        if (promotion.isPresent()) {
            System.out.println("Promotion trouvée: " + promotion.get().getNom());
            displayApprenants(promotion.get().getApprenants());
        } else {
            System.out.println("Promotion non trouvée.");
        }
    }

    public static void searchEleveByName(List<Promotion> promotions, String studentName) {
        Optional<Apprenant> apprenant = promotions.stream()
                .flatMap(promo -> promo.getApprenants().stream())
                .filter(apprenantItem -> apprenantItem.getNom().equalsIgnoreCase(studentName))
                .findFirst();

        if (apprenant.isPresent()) {
            System.out.println("Élève trouvé: " + apprenant.get());
        } else {
            System.out.println("Élève non trouvé.");
        }
    }

    public static void addEleve(List<Promotion> promotions) {
        Promotion selectedPromotion = selectPromotion(promotions);
        if (selectedPromotion == null) {
            System.out.println("Promotion non trouvée.");
            return;
        }

        Apprenant newApprenant = getApprenantDetails();
        GestionAbsencesUtil.addApprenant(selectedPromotion, newApprenant);
        System.out.println("Apprenant ajouté.");

        System.out.println("\nListe des élèves dans " + selectedPromotion.getNom() + " :");
        displayApprenants(selectedPromotion.getApprenants());
    }

    public static void modifyAbsences(List<Promotion> promotions) {
        Promotion selectedPromotion = selectPromotion(promotions);
        if (selectedPromotion == null) {
            System.out.println("Promotion non trouvée.");
            return;
        }

        displayApprenants(selectedPromotion.getApprenants());
        System.out.print("Entrez l'ID de l'élève pour modifier les absences: ");
        int apprenantId = scanner.nextInt();
        scanner.nextLine();

        Apprenant apprenantToModify = selectedPromotion.getApprenants().stream()
                .filter(apprenant -> apprenant.getId() == apprenantId)
                .findFirst()
                .orElse(null);

        if (apprenantToModify != null) {
            System.out.println("Nombre actuel d'absences: " + apprenantToModify.getNbAbsences());
            System.out.print("Entrez le nouveau nombre d'absences: ");
            int nbAbsences = scanner.nextInt();
            scanner.nextLine();

            GestionAbsencesUtil.modifyAbsences(apprenantToModify, nbAbsences);
            System.out.println("Nombre d'absences modifié.");
        } else {
            System.out.println("Apprenant non trouvé.");
        }
    }

    public static void deleteEleve(List<Promotion> promotions) {
        Promotion selectedPromotion = selectPromotion(promotions);
        if (selectedPromotion == null) {
            System.out.println("Promotion non trouvée.");
            return;
        }

        displayApprenants(selectedPromotion.getApprenants());
        System.out.print("Entrez l'ID de l'élève à supprimer: ");
        int apprenantId = scanner.nextInt();
        scanner.nextLine();

        Apprenant apprenantToRemove = selectedPromotion.getApprenants().stream()
                .filter(apprenant -> apprenant.getId() == apprenantId)
                .findFirst()
                .orElse(null);

        if (apprenantToRemove != null) {
            if (apprenantToRemove.isDelegue()) {
                System.out.println("Vous ne pouve7z pas supprimer un délégué. Veuillez d'abord révoquer son statut de délégué.");
                return;
            }
            GestionAbsencesUtil.removeApprenant(selectedPromotion, apprenantToRemove);
            System.out.println("Apprenant supprimé.");

            System.out.println("\nListe des élèves dans " + selectedPromotion.getNom() + " :");
            displayApprenants(selectedPromotion.getApprenants());
        } else {
            System.out.println("Apprenant non trouvé.");
        }
    }

    public static void ajouterDelegue(List<Promotion> promotions) {
        Promotion selectedPromotion = selectPromotion(promotions);
        if (selectedPromotion == null) {
            System.out.println("Promotion non trouvée.");
            return;
        }

        displayApprenants(selectedPromotion.getApprenants());
        System.out.print("Entrez l'ID de l'élève à nommer délégué: ");
        int apprenantId = scanner.nextInt();
        scanner.nextLine();

        Apprenant apprenantToNominate = selectedPromotion.getApprenants().stream()
                .filter(apprenant -> apprenant.getId() == apprenantId)
                .findFirst()
                .orElse(null);

        if (apprenantToNominate != null) {
            GestionAbsencesUtil.setDelegueStatus(apprenantToNominate, true);
            System.out.println(apprenantToNominate.getNom() + " " + apprenantToNominate.getPrenom() + " est maintenant délégué.");
        } else {
            System.out.println("Apprenant non trouvé.");
        }
    }

    public static void supprimerDelegue(List<Promotion> promotions) {
        Promotion selectedPromotion = selectPromotion(promotions);
        if (selectedPromotion == null) {
            System.out.println("Promotion non trouvée.");
            return;
        }

        displayApprenants(selectedPromotion.getApprenants());
        System.out.print("Entrez l'ID du délégué à révoquer: ");
        int apprenantId = scanner.nextInt();
        scanner.nextLine();

        Apprenant apprenantToRevoke = selectedPromotion.getApprenants().stream()
                .filter(apprenant -> apprenant.getId() == apprenantId && apprenant.isDelegue())
                .findFirst()
                .orElse(null);

        if (apprenantToRevoke != null) {
            GestionAbsencesUtil.setDelegueStatus(apprenantToRevoke, false);
            System.out.println(apprenantToRevoke.getNom() + " " + apprenantToRevoke.getPrenom() + " n'est plus délégué.");
        } else {
            System.out.println("Délégué non trouvé ou déjà révoqué.");
        }
    }

    private static Promotion selectPromotion(List<Promotion> promotions) {
        System.out.println("Sélectionnez une promotion:");
        for (int i = 0; i < promotions.size(); i++) {
            System.out.println((i + 1) + ". " + promotions.get(i).getNom());
        }
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= promotions.size()) {
            return promotions.get(choice - 1);
        } else {
            System.out.println("Sélection invalide.");
            return null;
        }
    }

    private static Apprenant getApprenantDetails() {
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Prénom: ");
        String prenom = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Numéro de téléphone: ");
        String numTel = scanner.nextLine();

        return new Apprenant(GestionAbsencesUtil.getNextApprenantId(), nom, prenom, email, numTel, 0, false);
    }

    private static void displayApprenants(List<Apprenant> apprenants) {
        if (apprenants.isEmpty()) {
            System.out.println("Aucun apprenant trouvé.");
        } else {
            apprenants.forEach(System.out::println);
        }
    }
}
