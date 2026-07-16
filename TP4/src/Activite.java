import java.time.*;
import java.util.*;

public class Activite {
	private LocalDate dateActivite; //la date de l'activité 
	private LocalTime heureDebut; //l'heure de début de l'activité
	private Duration duree; //la durée de l'activité
	private Eleve eleve; //l'élève qui participe à cette activité
	private Moniteur moniteur; //le moniteur
	private boolean voitureExt; //est false si la voiture de l'auto-école est utilisée, sinon true
	private TypeActivite type; //le type d'activité
	private Statut statut; //le statut de l'activité
	private double montant; //le montant facturé pour cette activité, calculé selon le type et la voiture utilisée

	/*les différents types d'activité: 
	 * LPA-Leçon Pratique sur Autoroute
	 * LPZ-Leçon Pratique en Zone résidentielle
	 * LPS-Leçon Pratique sur Stationnement
	 * LT-Leçon Théorique
	 * ET-Examen Théorique
	 * EP-Examen Pratique
	 * EPL-Examen Pratique avec Location extérieure
	 * */
	enum TypeActivite {
		LPA,
		LPZ,
		LPS,
		LT,
		ET,
		EP,
		EPL;
	    private static final TypeActivite[] vals = values();
	    
	    /**
	     * Sert à itérer à travers les valeurs de l'énum
	     * @return la prochaine valeur de l'énum
	     */
	    public TypeActivite next() {
	        return vals[(this.ordinal() + 1) % vals.length];
	    }
	}
	
	//les différents statuts de l'activité C-Complétée, NC-Non complétée
	enum Statut{
		C,
		NC
	}
	
	/**
	 * Constructeur de la classe, initialise les variables globales
	 * @param dateActivite la date de l'activité
	 * @param heureDebut l'heure de début de l'activité
	 * @param duree la durée de l'activité
	 * @param eleve l'élève qui participe à cette activité
	 * @param moniteur le moniteur
	 * @param voitureExt true si une voiture externe est utilisée, false si c'est celle de l'auto-école
	 * @param type le type d'activité
	 * @param statut le statut de l'activité (complétée ou non)
	 */
	public Activite(LocalDate dateActivite, LocalTime heureDebut, Duration duree, Eleve eleve, 
			Moniteur moniteur, boolean voitureExt, TypeActivite type, Statut statut) {
		this.dateActivite = dateActivite;
		this.heureDebut = heureDebut;
		this.duree = duree;
		this.eleve = eleve;
		this.moniteur = moniteur;
		this.voitureExt = voitureExt;
		this.type = type;
		this.statut = statut;
		this.montant = calculerMontant();
	}
	
	/**
	 * Calcule le montant à facturer pour cette activité selon son type,
	 * la durée et le fait qu'une voiture externe soit utilisée ou non.
	 * Note : pour EP et EPL, le type indique déjà si la voiture est celle
	 * de l'école (EP) ou externe (EPL), donc voitureExt ne sert que pour
	 * les leçons pratiques (LPA, LPZ, LPS).
	 * @return le montant en dollars
	 */
	public double calculerMontant() {
		double heures = this.duree.toMinutes() / 60.0;
		
		switch (this.type) {
			case LPA:
			case LPZ:
			case LPS:
				return (this.voitureExt ? 50.00 : 75.00) * heures;
			case LT:
				return 45.00 * heures;
			case ET:
				return 40.00;
			case EP:
				return 150.00;
			case EPL:
				return 85.00;
			default:
				return 0.0;
		}
	}
	
	/**
	 * Accesseur du montant facturé pour cette activité
	 * @return this.montant
	 */
	public double getMontant() {
		return this.montant;
	}
	
	/**
	 * Accesseur de la date de l'activité
	 * @return this.dateActivite
	 */
	public LocalDate getDateActivite() {
		return this.dateActivite;
	}
	
	/**
	 * Accesseur du type d'activité
	 * @return this.type
	 */
	public TypeActivite getType() {
		return this.type;
	}
	
	/**
	 * Accesseur du statut de l'activité
	 * @return this.statut
	 */
	public Statut getStatut() {
		return this.statut;
	}
	
	/**
	 * Accesseur de l'élève concerné par l'activité
	 * @return this.eleve
	 */
	public Eleve getEleve() {
		return this.eleve;
	}
	
}
