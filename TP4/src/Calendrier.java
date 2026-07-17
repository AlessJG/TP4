import java.io.*;
import java.time.*;
import java.util.*;

public class Calendrier {
	//format fichier calendrierXX.CSV -- ou XX est le numero du mois
	//Date, estIndispo, heure1-duree(en min), heure2-duree, heure3-duree, ...
	//1, true, 12:00-60, 14:00-90, ...
	
	private int mois; //la valeur du mois courant (en nombre)
	private int jour; //la valeur de la date d'aujourd'hui (en nombre)
	private int nbJours; //le nombre de jours du mois courant
	private int nbJoursRestants; //le nombre de jours restants entre aujourd'hui et la fin du mois
	private ArrayList<Date> dates; //une liste des dates (objets Date) du mois
	
	//moniteur fourni dates indisponibles
	//peut aussi rendre dates redisponible
	//si jour indisponible alors pas affiche
	//quand eleves sinscrive, date devient indisponible
	//erreurs possibles:
	//1.comparer avec date auj pour sassurer que ne pas essaye de inscrire a date passee
	//2.sassurer que n'essaie pas de s'inscrire a date inexistante (ex 0, -, passer max)
	//3.sassurer que n'essaie pas de s'sinscrire a date indisponible
	
	/**
	 * Constructeur de la classe, initialise les variables globales
	 */
	public Calendrier() {
		Clock horloge = Clock.systemUTC();
		this.dates = new ArrayList<Date>();
		this.jour = LocalDate.now(horloge).getDayOfMonth();
		this.mois = LocalDate.now(horloge).getMonthValue();
		this.nbJours = LocalDate.now(horloge).getMonth().length(LocalDate.now(horloge).isLeapYear());
		this.nbJoursRestants = this.nbJours - LocalDate.now(horloge).getDayOfMonth() + 1;
		
		String nomFichier = ("./CSV/calendriers/calendrier" + 
				 			this.mois + ".CSV");
		
		ArrayList<String[]> calendrier = AutoEcole.lireCSV(nomFichier);
		
		if(calendrier == null || calendrier.isEmpty()) {
			nouveauCalendrier(nomFichier);
		}
		else {
			for(int i = 0; i<this.nbJoursRestants; i++) {
				
				Date date = new Date(i + this.jour);
				String[] ligne = calendrier.get(i);
				
				if(ligne[1].trim().equalsIgnoreCase("true")) {
					date.rendreIndispo();
				}
				for(int j = 0; j<ligne.length; j++) {
					try {
						String[] s = ligne[j + 2].split("-");
						LocalTime heureDebut = LocalTime.parse(s[0].trim());
						Duration duree = Duration.ofMinutes(Long.parseLong(s[1].trim()));
						date.ajouterIndispo(heureDebut, duree);;
					}
					catch(Exception e) {
						break;
					}
				}
				
				this.dates.add(date);
			}
		}
		
	}
	
	/**
	 * Fonction qui gère l'absence de fichier calendrierXX.CSV, elle crée un nouveau fichier, le 
	 * rempli des dates (objets Date) restantes au mois initialisées avec aucune indisponibilité
	 * et rempli aussi this.dates avec ces dates   
	 * @param nomFichier, le path vers le fichier calendrierXX.CSV de ce mois
	 */
	public void nouveauCalendrier(String nomFichier) {
		String calendrierString = "Date, estIndispo, heure1-duree1, heure2-duree2, heure3-duree3, ...\n";
		for (int x = this.jour; x<=this.nbJours; x++) {
			calendrierString += x + ", false, \n";
			Date date = new Date(x);
			this.dates.add(date);
		}
		AutoEcole.ecrireCSV(nomFichier, calendrierString);
	}
	
	/**
	 * Fonction qui sert à obtenir la liste des dates (objets Date) disponibles (pour lesquelles 
	 * "estIndispo" est false) de ce mois-ci
	 * @return dispo, la liste
	 */
	public ArrayList<Date> getDisponibilites() {
		ArrayList<Date> dispo = new ArrayList<Date>();
		for(int i = 0; i<this.dates.size(); i++) {
			Date date = this.dates.get(i);
			if(!(date.checkIndispo())) {
				dispo.add(date);
			}
		}
		return dispo;
	}
	
	/**
	 * Fonction qui sert à afficher le calendrier de ce mois-ci avec les semaines du mois numérotées
	 * @return semaines, une liste des dates par chaque semaine
	 */
    public Date[][] afficherCalendrierMois() {
    	
        String nomMois[] = {"JANVIER", "FEVRIER", "MARS", "AVRIL", "MAI", "JUIN", "JUILLET",
        					"AOUT", "SEPTEMBRE", "OCTOBRE", "NOVEMBRE", "DECEMBRE"};

        System.out.println("MOIS:" + nomMois[mois - 1]);
        
        ArrayList<Date> dispo = this.getDisponibilites();
        int espaces = afficherEntete(dispo.get(0));
        
        Date[][] semaines = new Date[4][7];
        int nbSemaines = 1;
        int i = 0;
        
    	for (Date d : this.dates) {
    		int jour = d.getJour();
    		if(!(d.checkIndispo())) {
        		System.out.printf(" %4d ", jour);
        		semaines[nbSemaines - 1][i] = d;
        		i++;
    		}
    		else {
    			System.out.printf(" %4s ", "-");
    		}
    		
            if (((jour + espaces - 1) % 7 == 0) || (jour == nbJours)) {
            	System.out.printf(" %4s", "(");
            	System.out.printf("%d", nbSemaines);
            	System.out.printf(")");
                System.out.println();
                nbSemaines++;
                i=0;
            }    		
    	}
        
        System.out.println();
        return semaines;
    }
    
    /**
     * Fonction qui sert à afficher l'entête d'une semaine. Celui-ci contient les abbréviations des
     * noms des 7 jours de la semaine formattées, ainsi que le nombre d'espace à inclure avant la 
     * date de début.
     * @param start, la date (objet Date) de début, l'affichage commence à partir de celle-ci
     * @return espaces, le nombre d'espace
     */
    public int afficherEntete(Date start) {
    	String[] jours = {"DIM", "LUN", "MAR", "MER", "JEU", "VEN", "SAM"} ;
    	
        for (int k = 0; k < jours.length; k++) {
            System.out.print("   " + jours[k]);
        }
        System.out.println();
        
        int diff = start.getJour() - this.jour;
        int espaces = LocalDate.now().plusDays(diff).getDayOfWeek().getValue();

        for (int i = 0; i < espaces; i++) {
            System.out.print("      ");
        }
        return espaces;
    }
    
    /**
     * Fonction qui sert à afficher le calendrier de la semaine choisie avec les créneaux de 
     * disponibilités de chaque journée.
     * @param semaine, la liste des dates (objets Date) de la semaine à afficher
     */
    public void afficherCalendrierSemaine(Date[] semaine) {
    	int i = 1;
    	for (Date date : semaine) {
    		System.out.println("Jour" + i + ": " + date.getJour());
        	System.out.println("Heures disponibles:\n" + date.toString());
        	i++;
        }
    }
    
    /**
     * Fonction qui sert à supprimer un fichier.
     * @param fichier, le nom du fichier à supprimer
     */
    public void deleteFile(String fichier) {
    	File file = new File(fichier); 
        if (!(file.delete())) { 
          System.out.println("Erreur du système: le fichier " + fichier + 
        		  			 " n'a pas pu être supprimé.");
        }
    }
    
    public static void main(String args[]) {
    	Calendrier cal = new Calendrier();
    	cal.afficherCalendrierMois();
    	
    	
    	Date date1 = new Date(21);
    	date1.ajouterIndispo(LocalTime.parse("12:00"), Duration.ofMinutes(75));
    	date1.ajouterIndispo(LocalTime.parse("14:00"), Duration.ofMinutes(105));
    	
    	Date date2 = new Date(22);
    	
    	Date date3 = new Date(23);
    	date3.ajouterIndispo(LocalTime.parse("18:00"), Duration.ofMinutes(75));
    	
    	Date date4 = new Date(24);
    	date4.ajouterIndispo(LocalTime.parse("12:00"), Duration.ofMinutes(75));
    	date4.ajouterIndispo(LocalTime.parse("17:00"), Duration.ofMinutes(75));
    	
    	Date[] dates = {date1, date2, date3, date4};
    	cal.afficherCalendrierSemaine(dates);
    	cal.deleteFile(("./CSV/calendriers/calendrier" + 
	 			LocalDate.now().getMonthValue() + ".CSV"));
    }
}