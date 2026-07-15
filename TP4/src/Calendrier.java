import java.io.*;
import java.time.*;
import java.util.*;

public class Calendrier {
	//format fichier calendrierXX.CSV -- ou XX est le numero du mois
	//Date, estIndispo, heure1, heure2, heure3, ...
	//1, true, 12h00, 14h00, ...
	private int annee;
	private int mois;
	private int nbJours;
	private ArrayList<Date> dates;
	//moniteur fourni dates indisponibles
	//peut aussi rendre dates redisponible
	//si jour indisponible alors pas affiche
	//quand eleves sinscrive, date devient indisponible
	//erreurs possibles:
	//1.comparer avec date auj pour sassurer que ne pas essaye de inscrire a date passee
	//2.sassurer que n'essaie pas de s'inscrire a date inexistante (ex 0, -, passer max)
	//3.sassurer que n'essaie pas de s'sinscrire a date indisponible
	
	
	public Calendrier(Clock horloge) {
		/*ArrayList<String[]> indispos = AutoEcole.lireCSV("./CSV/calendrier/calendrier" + 
										 LocalDate.now(horloge).getMonth() + ".CSV");
		
		dates = new ArrayList<Date>();*/
		this.nbJours = LocalDate.now(horloge).getMonth().length(LocalDate.now(horloge).isLeapYear());
		
		/*for(int i = 0; i<nbJours; i++) {
			Date date = new Date();
			
			String[] ligne = indispos.get(i);
			if(ligne[1].equals("true")) {
				date.rendreIndispo();
			}
			
			for(int j = 0; j<ligne.length; j++) {
				try {
					date.ajouterIndispo(ligne[j + 2]);
				}
				catch(Exception e) {
					break;
				}
			}
						
			this.dates.add(date);
		}*/
	}
	
	public String getDisponibilites(Clock horloge) {
		
		
		
		return null;
	}

    public void afficherCalendrierMois() {
    	//corriger erreur de formattage
    	
    	Clock horloge = Clock.systemUTC();
    	annee = LocalDate.now(horloge).getYear();
		mois = LocalDate.now(horloge).getMonthValue();
		
        int j = 1;

        String jours[] = {"DIM", "LUN", "MAR", "MER", "JEU", "VEN", "SAM"};
        String nomMois[] = {"JANVIER", "FEVRIER", "MARS", "AVRIL", "MAI", "JUIN", "JUILLET",
        					"AOUT", "SEPTEMBRE", "OCTOBRE", "NOVEMBRE", "DECEMBRE"};

        System.out.println("MOIS:" + nomMois[mois - 1]);

        for (int k = 0; k < 7; k++) {
            System.out.print("   " + jours[k]);
        }

        System.out.println();

        int espaces = j;
        if (espaces < 0) {
            espaces = 6;
        }

        for (int i = 0; i < espaces; i++) {
            System.out.print("      ");
        }
        
        int c = 1;
        
        for (int i = 1; i <= nbJours; i++) {
            System.out.printf(" %4d ", i);

            if (((i + espaces) % 7 == 0) || (i == nbJours)) {
            	System.out.printf("%4s", "(" + c + ")");
                System.out.println();
                c++;
            }
        }
    }
    
    public void afficherCalendrierSemaine() {
    	
    }
    
    public void deleteFile(String fichier) {
    	File file = new File(fichier); 
        if (!(file.delete())) { 
          System.out.println("Erreur du système: le fichier " + fichier + 
        		  			 " n'a pas pu être supprimé.");
        }
    }
    
    public static void main(String args[]) {
    	Calendrier cal = new Calendrier(Clock.systemUTC());
    	cal.afficherCalendrierMois();
    }
}
//System.out.printf("Hey there! Just a reminder that I love you! <3");