import java.time.*;

public class Eleve extends Personne {
	private LocalDate dateInscription; //la date d'inscription à l'auto-école
	private LocalDate dateFin; //la date de fin de service, de la fin des activités de l'élèves
	private String numeroSAAQ; //le numéro SAAQ de l'élève
	private Lecon lecon; //la leçon à laquelle l'étudiant est rendu
	private boolean activitePrevue; //est true si la prochaine activité de l'élève est 
									//déjà planifier, false sinon
	private Activite activite; //la prochaine activité prévue de l'élève
	private Paiement facture; //le prochain paiement de l'élève
	
	//les différentes leçons à effectuer par l'étudiant, sont numérotées 
	private enum Lecon {
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
			String adresse, int numTelephone, int numSAAQ, String motDePasse) {
		super(nom, prenom, adresse, numTelephone, motDePasse);
		this.dateInscription = dateInscription;
		this.numeroSAAQ = numSAAQ;
		this.lecon = Lecon.LT1;
	}
	
	public Lecon getLecon() {
		return this.lecon;
	}
	
	public long getTempsLecon() {
		switch (this.lecon) {
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
	
	public void prochaineLecon() {
		this.lecon = lecon.next();
	}
	
	public void setActivite(Activite activite) {
		this.activite = activite;
	}
	
	public void getActiviteToString() {
		if(this.activitePrevue) {
			System.out.println("Votre prochaine activité est: " + this.lecon.toString() 
								+ " , prévue pour le " + this.activite.getDate() + " à " 
								+ this.activite.getHeure() + "(" + this.activite.getDuree() + "min)");
		}
	}
	
	public Activite getActivite() {
		return this.activite;
	}
	
	public boolean getActivitePrevue() {
		return this.activitePrevue;
	}
	
	public Paiement getFacture() {
		return this.facture;
	}
	
	public boolean impaye() {
		return this.facture.getStatut().equals(Paiement.Statut.Impaye);
	}
	
	
}
