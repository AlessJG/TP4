import java.util.*;

public final class Voiture {
	private final String marque; //la marque du véhicule
	private final int annee; //l'année du véhicule
	private final String plaque; //la plaque du véhicule
	private final double prixAchat; //le prix d'achat du véhicule
	private final int kmAchat; //le kilométrage à l'achat du véhicule
	private final int kilometrage; //le kilométrage courant du véhicule
	private LinkedList<DepenseVoiture> listeDepVoit; //la liste des dépenses (objets DepenseVoiture)
													 //liées à la voiture
	private Etat etat; //l'état de la voiture
	
	//les différents états possibles de la voiture, R-En réparation, V-Vendu, D-Disponible
	enum Etat{ 
		R,				
		V,
		D
	}
	
	/**
	 * Constructeur de la classe, initialise les variables globales
	 * @param marque
	 * @param annee
	 * @param plaque
	 * @param prixAchat
	 * @param kmAchat
	 * @param kilometrage
	 * @param etat
	 */
	public Voiture(String marque, int annee, String plaque, double prixAchat, int kmAchat, 
					int kilometrage, Etat etat) {
		this.marque = marque;
		this.annee = annee;
		this.plaque = plaque;
		this.prixAchat = prixAchat;
		this.kmAchat = kmAchat;
		this.kilometrage = kilometrage;
		this.etat = etat;
	}
	
	/**
	 * Accesseur de la variable "plaque"
	 * @return
	 */
	public String getPlaque() {
		return this.plaque;
	}
}
