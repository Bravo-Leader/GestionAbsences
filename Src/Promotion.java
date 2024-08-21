import java.util.ArrayList;
import java.util.List;

public class Promotion {
    private int id;
    private String nom;
    private List<Apprenant> apprenants;

    // Default constructor
    public Promotion() {
        this.apprenants = new ArrayList<>();
    }

    // Parameterized constructor
    public Promotion(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.apprenants = new ArrayList<>();
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public List<Apprenant> getApprenants() { return apprenants; }
    public void addApprenant(Apprenant apprenant) { this.apprenants.add(apprenant); }
    public void removeApprenant(Apprenant apprenant) { this.apprenants.remove(apprenant); }

    @Override
    public String toString() {
        // TODO:
        return "TODO";
    }
}
