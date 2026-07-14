import java.time.LocalDate;

public abstract class Personne {
	private String nom;
	private String prenom;
	private String adresse;
	private int numTelephone;
	private String motDePasse;
	
	//constructeur
	public Personne(String nom, String prenom, String adresse, int numTelephone, String motDePasse) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numTelephone = numTelephone;
		this.motDePasse = motDePasse;
		
	}
}
