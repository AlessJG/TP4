import java.time.LocalDate;

public class Eleve extends Personne {
	private LocalDate dateInscription; //la date d'inscription à l'auto-école
	private LocalDate dateFin; //la date de fin de service, de la fin des activités de l'élèves
	private int numeroSAAQ; //le numéro SAAQ de l'élève
	private Lecon lecon; //la leçon à laquelle l'étudiant est rendu
	
	//les différentes leçons à effectuer par l'étudiant, sont numérotées 
	private enum Lecon {
		LT1, LT2, LT3, 
		LT4, LT5, ET, 
		LPS, LT6, LPZ1, 
		LPZ2, LPZ3, LPZ4, 
		LT7, LPA1, LT8, 
		LPA2, LT9, LPA3, 
		LT10, LPA4, LPA5, 
		LT11, LT12;

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
		this.numeroSAAQ = numeroSAAQ;
		this.lecon = Lecon.LT1;
	}
}
