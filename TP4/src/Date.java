import java.time.*;
import java.util.*;

public class Date {
	
	private TreeMap<LocalTime, Duration> heuresIndispo; //liste des créneaux d'indisponibilités de la journée (heure de debut, duree)
	private boolean estIndispo; //est true si la date est complètement indisponible, sinon false
	private int jour; //la valeur du jour du mois
	
	/**
	 * Constructeur de la classe, initialise les variables globales
	 * @param jour, la valeur du jour du mois 
	 */
	public Date(int jour) {
		this.heuresIndispo = new TreeMap<LocalTime, Duration>();
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
	 * Fonction qui sert d'accesseur au TreeMap "heuresIndispo"
	 * @return this.heuresIndispo
	 */
	public TreeMap<LocalTime, Duration> getCreneauIndispo(){
		return this.heuresIndispo;
	}
	
	/**
	 * Fonction qui sert à calculer les créneaux de disponibilité de la journée et les ajoutent à 
	 * une liste au format (heure de début, durée), puis retourne cette liste 
	 * @return heuresDispo, la liste des créneaux de disponibilités
	 */
	//puisque heures oueverture = 12PM-8PM and min lecon = 1h + 15 min entre chaque eleves
	public TreeMap<LocalTime, Duration> getCreneauDispo(){
		TreeMap<LocalTime, Duration> heuresDispo = new TreeMap<LocalTime, Duration>();

		int i = 0;
		LocalTime previous = null;

		if(this.heuresIndispo.isEmpty()) {
			heuresDispo.put(LocalTime.parse("12:00"), Duration.ofHours(8));
			return heuresDispo;
		}
		for (LocalTime h : this.heuresIndispo.keySet()) {
			
			LocalTime fin = h.plus(this.heuresIndispo.get(h));
			
			if(previous != null) {
				Duration temps = Duration.between(previous, h);
				if(temps.toMinutes() >= 75) {
					heuresDispo.put(previous, temps);
				}
			}
			
			if (i == 0) {
				LocalTime ouverture = LocalTime.parse("12:00");
				Duration temps = Duration.between(ouverture, h);
				if(temps.toMinutes() >= 75) {
					heuresDispo.put(ouverture, temps);
				}
			}
			else if(i == (this.heuresIndispo.keySet().size()) - 1) {
				LocalTime fermeture = LocalTime.parse("20:00");
				Duration temps = Duration.between(fin, fermeture);
				if(temps.toMinutes() >= 60) {
					heuresDispo.put(fin, temps);
				}
			}
			
			previous = fin;
			i++;
		}
		
		return heuresDispo;
	}
	
	
	/**
	 * Fonction qui sert à afficher les créneaux horaire disponible de la date courante
	 */
	@Override
	public String toString() {
		String s = "";
		TreeMap<LocalTime, Duration> heuresDispo = this.getCreneauDispo();
		for(LocalTime h : heuresDispo.keySet()) {
			s += "De: " + h.toString() + "\njusqu'à";
			LocalTime heureFin = h.plus(heuresDispo.get(h));
			s += "\n" + heureFin.toString() + "\n\n";
		}
		return s;
	}
}
