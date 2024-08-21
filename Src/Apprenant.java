import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Apprenant {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String numTel;
    private int nbAbsences;
    private boolean estDelegue;
    private List<LocalDate> absenceDates;
    // Default constructor
    public Apprenant() {
        this.absenceDates = new ArrayList<>();
    }

    // Parameterized constructor
    public Apprenant(int id, String nom, String prenom, String email, String numTel, int nbAbsences, boolean estDelegue) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numTel = numTel;
        this.nbAbsences = nbAbsences;
        this.estDelegue = estDelegue;
        this.absenceDates = new ArrayList<>();
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNumTel() { return numTel; }
    public void setNumTel(String numTel) { this.numTel = numTel; }

    public int getNbAbsences() { return nbAbsences; }
    public void setNbAbsences(int nbAbsences) { this.nbAbsences = nbAbsences; }

    public boolean isDelegue() { return estDelegue; }
    public void setDelegue(boolean estDelegue) { this.estDelegue = estDelegue; }

    public List<LocalDate> getAbsenceDates() { return absenceDates; }
    public void setAbsenceDates(List<LocalDate> absenceDates) { this.absenceDates = absenceDates; }

    public void addAbsenceDate(LocalDate date) {
        this.absenceDates.add(date);
        this.nbAbsences = this.absenceDates.size();
    }

    public void removeAbsenceDate(LocalDate date) {
        if (this.absenceDates.remove(date)) {
            this.nbAbsences = this.absenceDates.size();
        }
    }

    @Override
    public String toString() {
        return "Apprenant{" +
        "id='" + id +
        ",nom='" + nom + '\'' +
        ", prenom='" + prenom + '\'' +
        ", email='" + email + '\'' +
        ", nbAbsences=" + nbAbsences +
        ", estDelegue=" + estDelegue +
        '}';
    }
}