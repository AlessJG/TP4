import java.util.LinkedList;

public class Voiture {
	private String marque;
	private int année; //(Date ?)
	private String plaque;
	private double prixAchat;
	private int kmAchat;
	private int kilometrage;
	private LinkedList<DepenseVoiture> listeDepVoit;
	private Etat etat;
	private enum Etat{
		R,
		V,
		D
	}
}
