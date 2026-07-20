import java.time.*;
import java.util.*;

public class Date {
	
	private TreeMap<LocalTime, Duration> heuresIndispo; //liste des créneaux d'indisponibilités de la journée (heure de debut, duree)
	private int jour; //la valeur du jour du mois
	
	/**
	 * Constructeur de la classe, initialise les variables globales
	 * @param jour, la valeur du jour du mois 
	 */
	public Date(int jour) {
		this.heuresIndispo = new TreeMap<LocalTime, Duration>();
		this.jour = jour;
	}
	
	/**
	 * Fonction qui sert à ajouter un créneau d'indisponibilité à la liste courante heuresIndispo
	 * @param temps, l'heure de début du créneau
	 * @param duree, la durée du créneau
	 */
	public boolean ajouterIndispo(LocalTime temps, Duration duree) {
		LocalTime fin = temps.plus(duree);

		for(LocalTime h : this.heuresIndispo.keySet()){

		    LocalTime finExistante = h.plus(this.heuresIndispo.get(h));

		    if(temps.isBefore(finExistante)
		            && fin.isAfter(h)){

		        return false;
		    }
		}

		this.heuresIndispo.put(temps,duree);
		return true;
	}
	
	/**
	 * Fonction qui sert à enlever un créneau d'indisponibilité à la liste courante heuresIndispo
	 * @param temps, l'heure de début du créneau
	 */
	public void enleverIndispo(LocalTime temps) {
		if(this.heuresIndispo.containsKey(temps)) {
			this.heuresIndispo.remove(temps);
		}
	}
	
	
	/**
	 * Fonction qui sert à vérifier s'il reste des
	 * disponibilités à la date courante ou non
	 * @return un boolean: true s'il reste des disponibilités, false sinon
	 */
	public boolean checkIndispo() {
	    return getCreneauDispo().isEmpty();
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
	//Note: les heures d'ouverture sont de 12PM-8PM et la durée 
	//minimum d'une leçon est de 60 min (+ 15 min pour donner du temps de préparation 
	//entre chaque eleves) donc 75min au total
	public TreeMap<LocalTime, Duration> getCreneauDispo() {

	    TreeMap<LocalTime, Duration> dispo = new TreeMap<>();

	    LocalTime debut = LocalTime.of(12,0);
	    LocalTime fermeture = LocalTime.of(20,0);

	    for(LocalTime h : this.heuresIndispo.keySet()){

	        Duration libre = Duration.between(debut,h);

	        if(libre.toMinutes() >= 75){
	            dispo.put(debut, libre);
	        }

	        debut = h.plus(this.heuresIndispo.get(h));
	    }

	    Duration dernier = Duration.between(debut, fermeture);

	    if(dernier.toMinutes() >= 75){
	        dispo.put(debut, dernier);
	    }

	    return dispo;
	}
	
	
	/**
	 * Fonction qui sert à afficher les créneaux horaire disponible de la date courante
	 */
	@Override
	public String toString() {
		String s = "";
		TreeMap<LocalTime, Duration> heuresDispo = this.getCreneauDispo();
		for(LocalTime h : heuresDispo.keySet()) {
			LocalTime heureFin = h.plus(heuresDispo.get(h));
			s += "De " + h +
				     " à " + heureFin +
				     "\n";
		}
		return s;
	}
}
