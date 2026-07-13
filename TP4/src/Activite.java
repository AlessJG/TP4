import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class Activite {
	private LocalDate dateActivite; //(format JJ-MM-AAAA)
	private LocalTime heureDebut; //(format HH:MM)
	private double duree; //(en minutes)
	private Eleve eleve;
	private Moniteur moniteur; //(nom, prénom)
	private boolean voitureExt; //(voiture de l'école ou voiture de l'extérieur)
	private TypeActivite type;
	private Statut statut;
	private HashMap<String, String> prix; //(mots = type, clé = prix)
	private enum TypeActivite {
		LPA,
		LPZ,
		LPS,
		LT,
		ET,
		EP,
		EPL;

	    private static final TypeActivite[] vals = values();
	    
	    public TypeActivite next() {
	        return vals[(this.ordinal() + 1) % vals.length];
	    }
	}
	
	private enum Statut{
		C,
		NC
	}
	
	//constructeur
	public Activite() {
		
	}
	public HashMap<String, String> getPrix() { //retourne liste de prix selon activite
		return this.prix;
	}
	
}
