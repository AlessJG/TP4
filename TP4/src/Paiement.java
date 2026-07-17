import java.time.LocalDate;

public class Paiement {
	private String numéroUnique; //(format : F-AAAA-XXXXX où AAAA est l'année et XXXXX est un numéro composé de 5 chiffres)
	private double montant;
	private LocalDate date;
	private Eleve eleve; //(référence par NumSAAQ, nom, prénom)
	private Activite activite;
	private Statut statut;
	private Methode methode;
	enum Statut{
		Paye, 
		Impaye, 
		PartiellementPaye
	}
	enum Methode{
		Espèces, 
		Carte, 
		Virement
	}
	
	public Statut getStatut() {
		return this.statut;
	}
	
	
	
}
