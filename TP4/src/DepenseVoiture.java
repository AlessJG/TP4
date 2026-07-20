import java.time.LocalDate;

//classe representant les depenses lies 
//a la voiture

public class DepenseVoiture {

    private int idDepense;
    private String plaque;
    private LocalDate date;
    private Categorie categorie;
    private String description;
    private double montant;


    //Categories:
    //R= reparation
    //E=entretien
    //C=carburant
    public enum Categorie {
        R,
        E,
        C
    }


    public DepenseVoiture(int idDepense,
                          String plaque,
                          LocalDate date,
                          Categorie categorie,
                          String description,
                          double montant) {

        this.idDepense = idDepense;
        this.plaque = plaque;
        this.date = date;
        this.categorie = categorie;
        this.description = description;
        this.montant = montant;
    }


    // Getters

    public int getIdDepense() {
        return idDepense;
    }

    public String getPlaque() {
        return plaque;
    }

    public LocalDate getDate() {
        return date;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public String getDescription() {
        return description;
    }

    public double getMontant() {
        return montant;
    }


    // Setters

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
    
    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
