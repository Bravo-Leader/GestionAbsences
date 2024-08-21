public class Main {
    public static void main(String[] args) {
  
        Apprenant apprenant = new Apprenant(1, "Virgile", "Coudert", "v.coudert@sabesoftwares.com", "0623279713", 0, true);


        Promotion promotion = new Promotion(1, "Promo DI 2022");
        promotion.addApprenant(apprenant);

        User user = new User(1, "a.clain", "password123");

        System.out.println(apprenant);
        System.out.println(promotion);
        System.out.println(user);
    }
}
