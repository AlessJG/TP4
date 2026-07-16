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
	private HashMap<TypeActivite, Double> prix; //un HashMap qui sert à connaitre le prix d'une activité 
										  //selon son type(key=type, value=prix)
	/*les différents types d'activité: 
	 * LPA-Leçon Pratique sur Autoroute
	 * LPZ-Leçon Pratique en Zone résidentielle
	 * LPS-Leçon Pratique sur Stationnement
	 * LT-Leçon Théorique
	 * ET-Examen Théorique
	 * EP-Examen Pratique
	 * EPL-Examen Pratique avec Location extérieure
	 * */
	private enum TypeActivite {
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
	private enum Statut{
		C,
		NC
	}
	
	/**
	 * Constructeur de la classe, initialise les variables globales
	 */
	public Activite() {
		
	}
	
	/**
	 * Accesseur du HashMap "prix"
	 * @return this.prix 
	 */
	public HashMap<TypeActivite, Double> getPrix() {
		return this.prix;
	}
	
}
