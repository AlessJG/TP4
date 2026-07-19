import java.time.*;

public class Eleve extends Personne {
	private LocalDate dateInscription; //la date d'inscription à l'auto-école
	private LocalDate dateFin; //la date de fin de service, de la fin des activités de l'élèves
	private String numeroSAAQ; //le numéro SAAQ de l'élève
	private Lecon prochaineLecon; //la prochaine leçon que l'élève doit planifier
	private boolean activitePrevue; //est true si la prochaine activité de l'élève est 
									//déjà planifier, false sinon
	private Activite activite; //la prochaine activité prévue de l'élève
	private Paiement facture; //le prochain paiement de l'élève
	
	//les différentes leçons à effectuer par l'élève, sont numérotées 
	enum Lecon {
		LT1, LT2, LT3, 
		LT4, LT5, ET, 
		LPS, LT6, LPZ1, 
		LPZ2, LPZ3, LPZ4, 
		LT7, LPA1, LT8, 
		LPA2, LT9, LPA3, 
		LT10, LPA4, LPA5, 
		LT11, LT12, EP, EPL;

	    private static final Lecon[] vals = values();
	    
	    /**
	     * Sert à itérer à travers les valeurs de l'énum
	     * @return la prochaine valeur de l'énum
	     */
	    public Lecon next() {
	        return vals[(this.ordinal() + 1) % vals.length];
	    }
	    
	    /**
	     * Sert à itérer à travers les valeurs de l'énum
	     * @return la valeur précédente à celle courante de l'énum
	     */
	    public Lecon previous() {
	        return vals[(this.ordinal() - 1) % vals.length];
	    }
	}
	
	/**
	 * Constructeur de la classe, initialise les variables globales
	 * @param dateInscription
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param numTelephone
	 * @param numSAAQ
	 * @param motDePasse
	 */
	public Eleve(LocalDate dateInscription, String nom, String prenom, 
			String adresse, String numTelephone, String numSAAQ, String motDePasse) {
		super(nom, prenom, adresse, numTelephone, motDePasse);
		this.dateInscription = dateInscription;
		this.numeroSAAQ = numSAAQ;
		this.prochaineLecon = Lecon.LT1;
	}
	
	/**
	 * Accesseur de la variable "prochaineLecon"
	 * @return
	 */
	public Lecon getLecon() {
		return this.prochaineLecon;
	}
	
	/**
	 * Accesseur de la durée (en min) de la prochaine lecon
	 * @return
	 */
	public long getTempsLecon() {
		switch (this.prochaineLecon) {
			case LT1:
			case LT2:
			case LT3:
			case LT4:
			case LT5:
			case LT6:
			case LT7:
			case LT8:
			case LT9:
			case LT10:
			case LT11:
			case LT12:
			case ET:
			case EP:
			case EPL:
				return 60;
			case LPS:
			case LPZ1:
			case LPZ2:
			case LPZ3:
			case LPZ4:
			case LPA1:
			case LPA2:
			case LPA3:
			case LPA4:
			case LPA5:
				return 90;
			default:
				return 0;
		}
	}
	
	/**
	 * Accesseur du type d'activité de la prochaine leçon
	 * @return
	 */
	public Activite.TypeActivite getTypeActivite() {
		switch (this.prochaineLecon) {
			case LT1:
			case LT2:
			case LT3:
			case LT4:
			case LT5:
			case LT6:
			case LT7:
			case LT8:
			case LT9:
			case LT10:
			case LT11:
			case LT12:
				return Activite.TypeActivite.LT;
			case ET:
				return Activite.TypeActivite.ET;
			case EP:
				return Activite.TypeActivite.EP;
			case EPL:
				return Activite.TypeActivite.EPL;
			case LPS:
				return Activite.TypeActivite.LPS;
			case LPZ1:
			case LPZ2:
			case LPZ3:
			case LPZ4:
				return Activite.TypeActivite.LPZ;
			case LPA1:
			case LPA2:
			case LPA3:
			case LPA4:
			case LPA5:
				return Activite.TypeActivite.LPA;
			default:
				return null;
		}
	}
	
	/**
	 * Fonction qui sert à afficher la prochaine activité prévue
	 */
	public void getActivitePrevueToString() {
		if(this.activitePrevue) {
			System.out.println("Votre prochaine activité est: " + this.prochaineLecon.previous().toString()
								+ " , prévue pour le " + this.activite.getDate() + " à " 
								+ this.activite.getHeure() + "(" + this.activite.getDuree() + "min)");
		}
	}
	
	/**
	 * Fonction qui sert à afficher la prochaine activité à planifier
	 */
	public void getActiviteToString() {
		if(this.activitePrevue) {
			System.out.println("Votre prochaine activité à planifier est: " + this.prochaineLecon.previous().toString()
								+ "(" + this.activite.getDuree() + "min)");
		}
	}
	
	/**
	 * Accesseur de la variable "activite"
	 * @return
	 */
	public Activite getActivite() {
		return this.activite;
	}
	
	/**
	 * Accesseur de la variable "activitePrevue"
	 * @return
	 */
	public boolean getActivitePrevue() {
		return this.activitePrevue;
	}
	
	/**
	 * Accesseur de la variable "facture"
	 * @return
	 */
	public Paiement getFacture() {
		return this.facture;
	}
	
	/**
	 * Fonctionne qui retourne true si la facture courante de l'élève est impayée,
	 * sinon false
	 * @return
	 */
	public boolean impaye() {
		if(this.facture == null) {
			return false;
		}
		else {
			return this.facture.getStatut().equals(Paiement.Statut.Impaye);
		}
	}
	
	/**
	 * Accesseur de la variable "numeroSAAQ"
	 * @return
	 */
	public String getNumSAAQ() {
		return this.numeroSAAQ;
	}
	
	/**
	 * Mutateur de la variable "lecon"
	 * @return
	 */
	public void setLecon(Lecon lecon) {
		this.prochaineLecon = lecon;
	}
	
	/**
	 * Mutateur de la variable "setActivite"
	 * @return
	 */
	public void setActivite(Activite activite) {
		this.activite = activite;
	}
	
	/**
	 * Mutateur de la variable "activitePrevue"
	 * @param activitePrevue
	 */
	public void setActivitePrevue(boolean activitePrevue) {
		this.activitePrevue = activitePrevue;
	}
	
	/**
	 * Mutateur de la variable "dateFin"
	 * @param date, la date de fin
	 */
	public void setDateFin(LocalDate date) {
		this.dateFin = date;
	}
}
