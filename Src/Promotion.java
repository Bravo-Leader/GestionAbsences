import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Promotion implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nom;
    private List<Apprenant> apprenants;

    public Promotion(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.apprenants = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Apprenant> getApprenants() {
        return apprenants;
    }

    public void setApprenants(List<Apprenant> apprenants) {
        this.apprenants = apprenants;
    }
}