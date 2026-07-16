import java.time.*;

public abstract class Personne {
	private String nom; //le nom de la personne
	private String prenom; //le prénom de la personne
	private String adresse; //l'adresse de la personne
	private int numTelephone; //le numéro de téléphone de la personne
	private String motDePasse; //le mot de passe de la personne
	
	/**
	 * Constructeur de la classe, initialise les variables globales
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param numTelephone
	 * @param motDePasse
	 */
	public Personne(String nom, String prenom, String adresse, int numTelephone, String motDePasse) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numTelephone = numTelephone;
		this.motDePasse = motDePasse;
		
	}
}
