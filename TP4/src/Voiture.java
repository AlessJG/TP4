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
		this.listeDepVoit = new LinkedList<>();
	}
	
	/**
	 * Accesseur de la variable "plaque"
	 * @return
	 */
	public String getPlaque() {
		return this.plaque;
	}
	
	/**
	 * Fonction qui sert à ajouter une depense (objet DepenseVoiture) à la liste de dépenses
	 * @param depense, la dépense à ajouter
	 */
	public void ajouterDepense(DepenseVoiture depense) {
	    this.listeDepVoit.add(depense);
	}
	
	/**
	 * Accesseur de la variable marque
	 * @return this.marque
	 */
	public String getMarque() {
	    return this.marque;
	}
	
	/**
	 * Accesseur de la variable annee
	 * @return this.annee
	 */
	public int getAnnee() {
	    return this.annee;
	}
	
	/**
	 * Accesseur de la variable prixAchat
	 * @return this.prixAchat
	 */
	public double getPrixAchat() {
	    return this.prixAchat;
	}
	
	/**
	 * Accesseur de la variable kmAchat
	 * @return this.kmAchat
	 */
	public int getKmAchat() {
	    return this.kmAchat;
	}
	
	/**
	 * Accesseur de la variable etat
	 * @return this.etat
	 */
	public Etat getEtat() {
	    return this.etat;
	}
	
	/**
	 * Accesseur de la variable kilometrage
	 * @return this.kilometrage
	 */
	public int getKilometrage() {
	    return kilometrage;
	}
	
	/**
	 * 
	 * @return
	 */
	public LinkedList<DepenseVoiture> getListeDepVoit(){
		return this.listeDepVoit;
	}
}
 


  




