
public abstract class Personne {
	private String nom; //le nom de la personne
	private String prenom; //le prénom de la personne
	private String adresse; //l'adresse de la personne
	private String numTelephone; //le numéro de téléphone de la personne
	protected String motDePasse; //le mot de passe de la personne
	
	/**
	 * Constructeur de la classe, initialise les variables globales
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param numTelephone
	 * @param motDePasse
	 */
	public Personne(String nom, String prenom, String adresse, String numTelephone, String motDePasse) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numTelephone = numTelephone;
		this.motDePasse = motDePasse;
		
	}
	
	/**
	 * Accesseur de la variable "motDePasse"
	 * @return this.motDePasse
	 */
	protected String getMotDePasse() {
		return this.motDePasse;
	}
	
	/**
	 * Accesseur de la variable "nom"
	 * @return this.nom
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * Accesseur de la variable "prenom"
	 * @return this.prenom
	 */
	public String getPrenom() {
		return this.prenom;
	}
	
	public String getAdresse() {
	    return this.adresse;
	}

	public String getNumTelephone() {
	    return this.numTelephone;
	}
}
