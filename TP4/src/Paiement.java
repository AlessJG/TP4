import java.time.LocalDate;

public class Paiement {
	private String numeroUnique; //(format : F-AAAA-XXXXX où AAAA est l'année et XXXXX est un numéro composé de 5 chiffres)
	private double montant;
	private LocalDate date;
	private Eleve eleve; //(référence par NumSAAQ, nom, prénom)
	private Activite activite;
	private Statut statut;
	private Methode methode;
	
	public Paiement(String numeroUnique, double montant, LocalDate date, Eleve eleve, Activite activite,
					Statut statut, Methode methode) {
		this.numeroUnique = numeroUnique;
		this.montant = montant;
		this.date = date;
		this.eleve = eleve;
		this.activite = activite;
		this.statut = statut;
		this.methode = methode;
	}
	
	enum Statut{
		P, 
		I, 
		PP
	}
	enum Methode{
		E, 
		C, 
		V
	}
	
	public Statut getStatut() {
		return this.statut;
	}
	
	public double getMontant() {
	    return this.montant;
	}

	public void setMontant(double montant) {
	    this.montant = montant;
	}

	public void setStatut(Statut statut) {
	    this.statut = statut;
	}

	public void setMethode(Methode methode) {
	    this.methode = methode;
	}

	public Activite getActivite() {
	    return this.activite;
	}
	
	public String getNumeroUnique() {
	    return this.numeroUnique;
	}

	public LocalDate getDate() {
	    return this.date;
	}

	public Eleve getEleve() {
	    return this.eleve;
	}

	public Methode getMethode() {
	    return this.methode;
	}
		
	@Override
	public String toString() {
		String s = "";
		s += "Numéro unique: " + this.numeroUnique + "\n";
		s += "Montant: " + this.montant + "\n";
		s += "Date: " + this.date.toString() + "\n";
		s += "Activité: " + this.activite.getType() + "\n";
		return s;
		
	}
}
