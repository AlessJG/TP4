import java.time.*;
import java.util.*;

public class Calendrier {
	//format fichier calendrierXX.CSV -- où XX est le numero du mois
	//Date, heure1-duree(en min), heure2-duree, heure3-duree, ...
	//1, 12:00-60, 14:00-90, ...
	
	private int mois; //la valeur du mois courant (en nombre)
	private int jour; //la valeur de la date d'aujourd'hui (en nombre)
	private int nbJours; //le nombre de jours du mois courant
	private int nbJoursRestants; //le nombre de jours restants entre aujourd'hui et la fin du mois
	private ArrayList<Date> dates; //une liste des dates (objets Date) du mois
	
	
	/**
	 * Constructeur de la classe, initialise les variables globales
	 */
	public Calendrier() {
		this.dates = new ArrayList<Date>();
		this.jour = LocalDate.now().getDayOfMonth();
		this.mois = LocalDate.now().getMonthValue();
		this.nbJours = LocalDate.now().getMonth().length(LocalDate.now().isLeapYear());
		this.nbJoursRestants = this.nbJours - LocalDate.now().getDayOfMonth() + 1;
		
		String nomFichier = ("./CSV/calendriers/calendrier" + 
				 			this.mois + ".csv");
		
		ArrayList<String[]> calendrier = GestionFichiers.lireCSV(nomFichier);
		
		if(calendrier == null || calendrier.isEmpty()) {
			nouveauCalendrier(nomFichier);
		}
		else {
			for(int i = 0; i<this.nbJoursRestants; i++) {
				
				Date date = new Date(i + this.jour);
				String[] ligne = calendrier.get(i);
				
				for(int j = 1; j<ligne.length; j++) {
					try {
						String[] s = ligne[j ].split("-");
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
	 * rempli des dates (objets Date) restantes au mois et rempli aussi this.dates avec ces dates   
	 * @param nomFichier, le path vers le fichier calendrierXX.CSV de ce mois
	 */
	public void nouveauCalendrier(String nomFichier) {
		String calendrierString = "Date, heure1-duree1, heure2-duree2, heure3-duree3, ...\n";
		for (int x = this.jour; x<=this.nbJours; x++) {
			calendrierString += x + ",\n";
			Date date = new Date(x);
			this.dates.add(date);
		}
		GestionFichiers.ecrire(nomFichier, calendrierString);
	}
	
	/**
	 * Fonction qui sert à obtenir la liste des dates (objets Date) disponibles de ce mois-ci
	 * @return dispo, la liste
	 */
	public ArrayList<Date> getDisponibilites() {

	    ArrayList<Date> dispo = new ArrayList<>();

	    for (Date date : this.dates) {

	        if (!date.getCreneauDispo().isEmpty()) {
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
        
        Date[][] semaines = new Date[6][7];

        //On affiche l'entête
        int semaine = 0;
        int colonne = afficherEntete(this.dates.get(0));

        //On affiche les dates du mois formattées
        for (Date d : this.dates) {
        	if (!d.getCreneauDispo().isEmpty()) {
                semaines[semaine][colonne] = d;
                System.out.printf("%6d", d.getJour());
            } else {
            	//Si une date est indisponible, son numéro n'est pas affiché
                System.out.printf("%6s", "-");
            }

            colonne++;

            //lorsqu'on arrive à la fin d'une semaine, on imprime son numéro et passe à la prochaine ligne
            if (colonne == 7) {
                System.out.println(" (" + (semaine + 1) + ")");
                colonne = 0;
                semaine++;

                if (semaine == semaines.length)
                    break;
            }
        }       		

        if (colonne != 0) {
            System.out.printf(" (%d)", semaine+1);
            System.out.println();
        }
        
        System.out.println();
        return semaines;
    }
    
    /**
     * Fonction qui sert à afficher l'entête d'une semaine. Celui-ci contient les abbréviations des
     * noms des 7 jours de la semaine formattées, ainsi que le nombre d'espace à inclure avant la 
     * date de début
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
        DayOfWeek jourSemaine = LocalDate.now()
                .plusDays(diff)
                .getDayOfWeek();

        int espaces = jourSemaine.getValue() % 7;

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

            if (date == null) {
                i++;
                continue;
            }

            System.out.println("Jour " + i + " : " + date.getJour());
            System.out.println("Heures disponibles:");
            System.out.println(date);

            i++;
        }
    }
    
    /**
     * Accesseur pour la liste de dates (objets Date) du calendrier
     * @return this.dates
     */
    public ArrayList<Date> getDates(){
    	return this.dates;
    }
}