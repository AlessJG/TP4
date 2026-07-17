import java.io.*;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class GestionFichiers {
	/**
     * Fonction qui sert à lire un fichier CSV. La liste retournée contient des tableaux de String 
     * et chacun d'entre eux représente une ligne du fichier CSV.
     * @param fichier, le path du fichier CSV
     * @return contenu, la liste de tableaux de String
     */
    public static ArrayList<String[]> lireCSV(String fichier) {
    	try {
    		File f = new File(fichier);
    		if(f.exists() && !f.isDirectory()) { 
    			ArrayList<String[]> contenu = new ArrayList<String[]>();
        		FileReader fr = new FileReader(fichier);
        		BufferedReader reader = new BufferedReader(fr);
        		
        		String s = reader.readLine();
        		while ((s = reader.readLine()) != null) {
        			contenu.add(s.split(","));
        		}
        		
        		reader.close();
        		return contenu;
    		}
    		else {
    			try {
    				f.createNewFile();
    				return null;
    		    } catch (IOException e) {
    		    	System.out.println("Erreur à la création d'un fichier.");
    		    	e.getStackTrace();
    		    	return null;
    		    }
    		}
    		
    	} catch (IOException ex) {
    		System.out.println("Erreur à l’ouverture du fichier.");
    		System.out.println(ex.getStackTrace());
    		return null;
    	}
    }
    
    public void testLireCSV() {
    	System.out.println("Tests lireCSV()");
	}
    
    /**
     * Fonction qui sert à ecrire dans un fichier CSV
     * @param fichier, le path du fichier CSV 
     * @param texte, le texte à écrire dans le fichier
     */
    public static void ecrireCSV(String fichier, String texte) {
    	File f = new File(fichier);
		if(!f.exists()) { 
			try {
				f.createNewFile();
			} catch (IOException e) {
				System.out.println("Erreur à la création d'un fichier.");
				e.printStackTrace();
			}
		}
    	try {
    		FileWriter fw = new FileWriter(fichier);
    		BufferedWriter writer = new BufferedWriter(fw);
    		writer.append(texte);
    		writer.close();
    		
		} catch (IOException ex) {
			System.out.println("Erreur à l’écriture du fichier");
			System.out.println(ex.getStackTrace());
		}
	    
    }
    
	public void testEcrireCSV() {
		System.out.println("Tests EcrireCSV()");
	}
    
	public static ArrayList<Eleve> elevesCSV(String nomFichier) {
		String fEleves = "./CSV/eleves/eleves" + 
						 LocalDate.now(Clock.systemUTC()).getYear() + ".csv";
		ArrayList<String[]> elevesS = lireCSV(fEleves);
		
		//Si le fichier pour l'année courante n'existait pas
		if(elevesS == null || elevesS.isEmpty()) {
			
			String fElevesPrec = "./CSV/eleves/eleves" + 
					 (LocalDate.now(Clock.systemUTC()).getYear() - 1) + ".csv";
			ArrayList<String[]> elevesSPrec = lireCSV(fElevesPrec);
			
			//Cas 1: on vient de créer l'app de l'auto école donc 
			//elle n'a pas encore d'étudiant
			if(elevesSPrec == null || elevesSPrec.isEmpty()) {
				ecrireCSV(fEleves, "NumSAAQ, MotDePasse, Nom, Prenom, Adresse, Telephone, Date, DateFin");
				return new ArrayList<Eleve>();
			}
			//Cas 2: on vient de changer d'année donc les données doivent être mises 
			//à jour
			else {
				ArrayList<Eleve> eleves = new ArrayList<Eleve>();
				ecrireCSV(fEleves, "NumSAAQ, MotDePasse, Nom, Prenom, Adresse, Telephone, Date, DateFin");
				String s = "";
				for(String[] sTab : elevesSPrec ) {
					if(sTab[7].isBlank() || sTab[7].isEmpty()) {
						s += String.join(",", sTab);
						s += "\n";
						String[] dateS = sTab[5].split("-");
						LocalDate dateInscription = LocalDate.of(
								Integer.parseInt(dateS[2]), 
								Integer.parseInt(dateS[1]), 
								Integer.parseInt(dateS[0]));
						
						Eleve eleve = new Eleve(dateInscription,
												sTab[2],
											    sTab[3],
											    sTab[4],
											    sTab[5],
											    sTab[0],
											    sTab[1]);
						eleves.add(eleve);
						
					}
				}
				ecrireCSV(fEleves, s);
				return eleves;
			}
		}
		//Si le fichier existait
		else {
			ArrayList<Eleve> eleves = new ArrayList<Eleve>();
			for(String[] sTab : elevesS) {
				if(sTab[7].isBlank() || sTab[7].isEmpty()) {
					String[] dateS = sTab[5].split("-");
					LocalDate dateInscription = LocalDate.of(
							Integer.parseInt(dateS[2]), 
							Integer.parseInt(dateS[1]), 
							Integer.parseInt(dateS[0]));
					
					Eleve eleve = new Eleve(dateInscription,
											sTab[2],
										    sTab[3],
										    sTab[4],
										    sTab[5],
										    sTab[0],
										    sTab[1]);
					eleves.add(eleve);
				}
			}
			return eleves;
		}
	}
	
	public static ArrayList<Activite> activitesCSV(String nomFichier) {
		String fActivites = "./CSV/activites/activites" + 
						 LocalDate.now(Clock.systemUTC()).getYear() + ".csv";
		ArrayList<String[]> activiteS = lireCSV(fActivites);
		
		//Si le fichier pour l'année courante n'existait pas
		if(activiteS == null || activiteS.isEmpty()) {
			
			String fActivitesPrec = "./CSV/activites/activites" + 
					 (LocalDate.now(Clock.systemUTC()).getYear() - 1) + ".csv";
			ArrayList<String[]> activiteSPrec = lireCSV(fActivitesPrec);
			ecrireCSV(fActivites, "ID_Activite, Type, NumSAAQ, Date, Heure, Duree, Montant, Statut, Plaque");
			
			//Cas 1: on vient de créer l'app de l'auto école donc 
			//elle n'a pas encore d'activites prevues
			if(activiteSPrec == null || activiteSPrec.isEmpty()) {
				return new ArrayList<Activite>();
			}
			//Cas 2: on vient de changer d'année donc les données doivent être mises 
			//à jour
			else {
				ArrayList<Activite> activites = new ArrayList<Activite>();
				String s = "";
				int ID = 1;
				
				for(String[] sTab : activiteSPrec ) {
					String[] dateS = sTab[3].split("-");
					LocalDate date = LocalDate.of(
							Integer.parseInt(dateS[2]), 
							Integer.parseInt(dateS[1]), 
							Integer.parseInt(dateS[0]));
					
					if(date.getYear() == LocalDate.now(Clock.systemUTC()).getYear()) {
						//ID_Activite, Type, NumSAAQ, Date, Heure, Duree, Montant, Statut, Plaque
						sTab[0] = String.valueOf(ID);
						s += String.join(",", sTab);
						s += "\n";
						
						LocalTime h = LocalTime.parse(sTab[4]);
						Duration duree = Duration.parse(sTab[5]);
						
						if(sTab[8].equals())
						//Date date, LocalTime heureDebut, Duration duree, Eleve eleve, 
						//Moniteur moniteur, boolean voitureExt, TypeActivite type, Statut statut
						Activite activite = new Activite(date, h, duree, e, new Moniteur(), );
						activites.add(activite);
						
					}
				}
				ecrireCSV(fActivites, s);
				return activites;
			}
		}
		//Si le fichier existait
		else {
			ArrayList<Eleve> eleves = new ArrayList<Eleve>();
			for(String[] sTab : elevesS) {
				if(sTab[7].isBlank() || sTab[7].isEmpty()) {
					String[] dateS = sTab[5].split("-");
					LocalDate dateInscription = LocalDate.of(
							Integer.parseInt(dateS[2]), 
							Integer.parseInt(dateS[1]), 
							Integer.parseInt(dateS[0]));
					
					Eleve eleve = new Eleve(dateInscription,
											sTab[2],
										    sTab[3],
										    sTab[4],
										    sTab[5],
										    sTab[0],
										    sTab[1]);
					eleves.add(eleve);
				}
			}
			return eleves;
		}
	}
	

}
	

