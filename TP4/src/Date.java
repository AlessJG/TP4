import java.time.*;
import java.util.*;

public class Date {
	
	private HashMap<LocalTime, Duration> heuresIndispo; //liste des créneaux d'indisponibilités de la journée (heure de debut, duree)
	private boolean estIndispo; //est true si la date est complètement indisponible, sinon false
	private int jour; //la valeur du jour du mois
	
	/**
	 * Constructeur de la classe, initialise les variables globales
	 * @param jour, la valeur du jour du mois 
	 */
	public Date(int jour) {
		this.heuresIndispo = new HashMap<LocalTime, Duration>();
		this.estIndispo = false;
		this.jour = jour;
	}
	
	/**
	 * Fonction qui sert à ajouter un créneau d'indisponibilité à la liste de créneaux
	 * @param temps, l'heure de début du créneau
	 * @param duree, la durée du créneau
	 */
	public void ajouterIndispo(LocalTime temps, Duration duree) {
		heuresIndispo.putIfAbsent(temps, duree);
	}
	
	/**
	 * Fonction qui sert à enlever un créneau d'indisponibilité à la liste de créneaux
	 * @param temps, l'heure de début du créneau
	 * @param duree, la durée du créneau
	 */
	public void enleverIndispo(LocalTime temps, Duration duree) {
		if(heuresIndispo.containsKey(temps)) {
			heuresIndispo.remove(temps, duree);
		}
	}
	
	/**
	 * Fonction (mutateur) qui sert à rendre la variable booléenne "estIndispo" true
	 */
	public void rendreIndispo() {
		this.estIndispo = true;
	}
	
	/**
	 * Fonction qui sert d'accesseur à la variable "estIndispo"
	 * @return this.estIndispo
	 */
	public boolean checkIndispo() {
		return this.estIndispo;
	}
	
	/**
	 * Fonction qui sert d'accesseur à la variable "jour"
	 * @return this.jour
	 */ 
	public int getJour() {
		return this.jour;
	}
	
	/**
	 * Fonction qui sert d'accesseur au HashMap "heuresIndispo"
	 * @return this.heuresIndispo
	 */
	public HashMap<LocalTime, Duration> getCreneauIndispo(){
		return this.heuresIndispo;
	}
	
	/**
	 * Fonction qui sert à calculer les créneaux de disponibilité de la journée et les ajoutent à 
	 * une liste au format (heure de début, durée), puis retourne cette liste 
	 * @return heuresDispo, la liste des créneaux de disponibilités
	 */
	//puisque heures oueverture = 12PM-8PM and min lecon = 1h
	public HashMap<LocalTime, Duration> getCreneauDispo(){
		HashMap<LocalTime, Duration> heuresDispo = new HashMap<LocalTime, Duration>();

		int i = 0;
		LocalTime previous = null;
		for (LocalTime debut : this.heuresIndispo.keySet()) {
			LocalTime fin = debut.plus(this.heuresIndispo.get(debut));
			
			if (i == 0) {
				LocalTime ouverture = LocalTime.parse("12:00PM");
				Duration temps = Duration.between(ouverture, debut);
				if(temps.compareTo(Duration.ofMinutes(75)) >= 0) {
					heuresDispo.put(ouverture, temps);
				}
			}
			else if(i == this.heuresIndispo.size() - 1) {
				LocalTime fermeture = LocalTime.parse("8:00PM");
				Duration temps = Duration.between(debut, fermeture);
				if(temps.compareTo(Duration.ofMinutes(75)) >= 0) {
					heuresDispo.put(fermeture, temps);
				}
			}
			else {
				Duration temps = Duration.between(previous, debut);
				if(temps.compareTo(Duration.ofMinutes(75)) >= 0) {
					heuresDispo.put(previous, temps);
				}
			}
			previous = debut;
			i++;
		}
		
		return heuresDispo;
	}
}
