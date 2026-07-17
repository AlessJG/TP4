
public final class Moniteur extends Personne {
	private final String numPermis; //le numéro de permis du moniteur
	
	/**
	 * Constructeur de la classe, initialise les variables globales
	 */
	public Moniteur() {
		super("Toutlemonde", "John", "123 Boulevard blv ville province XXXXXX", "514123456", "Motdepasse9?");
		this.numPermis = "12345678";
	}
	
	public String getNumPermis() {
		return this.numPermis;
	}
}
