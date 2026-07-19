import java.io.*;
import java.time.*;
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
    
    public static void ajouterCSV(String fichier, String texte) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fichier, true));
            writer.append(texte);
            writer.close();
        } catch (IOException e) {
            System.out.println("Erreur à l'écriture du fichier");
            e.printStackTrace();
        }
    }
    
	/**
	 * Fonction qui sert à créer la liste des élèves à partir des fichiers elevesXXXX.csv (ou XXXX est l'année voulue)
	 * Permet aussi de gérer les fichiers CSV des élèves: mise à jour des données si l'année vient de changer, 
	 * création d'un nouveau fichier si celui de cette année n'existe pas encore.
	 * @param nomFichier, le nom du fichier CSV des élèves de l'année voulue
	 * @return la liste des élèves (provenant du fichier CSV de l'année voulue)
	 */
	public static ArrayList<Eleve> elevesCSV(String nomFichier) {
		
		ArrayList<String[]> elevesS = lireCSV(nomFichier);
		ArrayList<Eleve> eleves = new ArrayList<Eleve>();
		
		//Si le fichier pour l'année courante n'existait pas
		if(elevesS == null || elevesS.isEmpty()) {
			
			String fElevesPrec = "./CSV/eleves/eleves" + 
					 (LocalDate.now(Clock.systemUTC()).getYear() - 1) + ".csv";
			ArrayList<String[]> elevesSPrec = lireCSV(fElevesPrec);
			ecrireCSV(nomFichier, "NumSAAQ, MotDePasse, Nom, Prenom, Adresse, Telephone, Date, DateFin\n");
			
			//Cas 1: on vient de créer l'app de l'auto école donc 
			//elle n'a pas encore d'étudiant
			if(elevesSPrec == null || elevesSPrec.isEmpty()) {
				return eleves;
			}
			//Cas 2: on vient de changer d'année donc les données doivent être mises 
			//à jour
			else {
				String s = "";
				for(String[] sTab : elevesSPrec ) {
					if(sTab.length < 8 || sTab[7].trim().isEmpty()) {
						s += String.join(",", sTab);
						s += "\n";
						String[] dateS = sTab[6].split("-");
						LocalDate dateInscription = LocalDate.of(
								Integer.parseInt(dateS[0]), 
								Integer.parseInt(dateS[1]), 
								Integer.parseInt(dateS[2]));
						
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
				ecrireCSV(nomFichier, s);
				return eleves;
			}
		}
		//Si le fichier existait
		else {
			for(String[] sTab : elevesS) {
				if(sTab.length < 8 || sTab[7].trim().isEmpty()) {
					String[] dateS = sTab[6].split("-");
					
					LocalDate dateInscription = LocalDate.of(
							Integer.parseInt(dateS[0]), 
							Integer.parseInt(dateS[1]), 
							Integer.parseInt(dateS[2]));
					
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
	
	/**
	 * Fonction qui sert à créer la liste des activités à partir des fichiers activitésXXXX.csv. 
	 * Permet aussi de gérer les fichiers CSV des activités: mise à jour des données si l'année vient de changer, 
	 * création d'un nouveau fichier si celui de cette année n'existe pas encore.
	 * 
	 * @param nomFichier, le nom du fichier CSV des activités de l'année voulue
	 * @param voiture, l'instance du véhicule courant
	 * @return la liste des activités (provenant du fichier CSV de l'année voulue)
	 */
	public static ArrayList<Activite> activitesCSV(String nomFichier, Voiture voiture) {
		
		ArrayList<String[]> activiteS = lireCSV(nomFichier);
		ArrayList<Activite> activites = new ArrayList<Activite>();
		
		//Si le fichier pour l'année courante n'existait pas
		if(activiteS == null || activiteS.isEmpty()) {
			
			String fActivitesPrec = "./CSV/activites/activites" + 
					 (LocalDate.now(Clock.systemUTC()).getYear() - 1) + ".csv";
			ArrayList<String[]> activiteSPrec = lireCSV(fActivitesPrec);
			ecrireCSV(nomFichier, "ID_Activite, Type, NumSAAQ, Date, Heure, Duree, Montant, Statut, Plaques\n");
			
			//Cas 1: on vient de créer l'app de l'auto école donc 
			//elle n'a pas encore d'activites prevues
			if(activiteSPrec == null || activiteSPrec.isEmpty()) {
				return activites;
			}
			//Cas 2: on vient de changer d'année donc les données doivent être mises 
			//à jour
			else {
				String s = "";
				int ID = 1;
				
				for(String[] sTab : activiteSPrec ) {
					String[] dateS = sTab[3].split("-");
					LocalDate date = LocalDate.of(
							Integer.parseInt(dateS[0]), 
							Integer.parseInt(dateS[1]), 
							Integer.parseInt(dateS[2]));
					
					if(date.getYear() == LocalDate.now(Clock.systemUTC()).getYear()) {
						sTab[0] = String.valueOf(ID);
						s += String.join(",", sTab);
						s += "\n";
						
						LocalTime h = LocalTime.parse(sTab[4]);
						Duration duree = Duration.ofMinutes((long) Double.parseDouble(sTab[5]));
						boolean voitureExt;
						
						if(sTab[8].equals(voiture.getPlaque().toString())) {
							voitureExt = false;
						}
						else {
							voitureExt = true;
						}
						Activite activite = new Activite(date, h, duree, sTab[2], new Moniteur(), voitureExt, 
														 Activite.TypeActivite.valueOf(sTab[1]), Activite.Statut.valueOf(sTab[7]));
						activites.add(activite);
						ID++;
					}
				}
				ecrireCSV(nomFichier, s);
				return activites;
			}
		}
		//Si le fichier existait
		else {
			for(String[] sTab : activiteS) {
			
				String[] dateS = sTab[3].split("-");
				LocalDate date = LocalDate.of(
						Integer.parseInt(dateS[0]), 
						Integer.parseInt(dateS[1]), 
						Integer.parseInt(dateS[2]));
									
				LocalTime h = LocalTime.parse(sTab[4]);
				Duration duree = Duration.ofMinutes((long) Double.parseDouble(sTab[5]));
				boolean voitureExt;
				
				if(sTab[8].equals(voiture.getPlaque().toString())) {
					voitureExt = false;
				}
				else {
					voitureExt = true;
				}
				
				Activite activite = new Activite(date, h, duree, sTab[2], new Moniteur(), voitureExt, 
												 Activite.TypeActivite.valueOf(sTab[1]), Activite.Statut.valueOf(sTab[7]));
				activites.add(activite);
				
			}
			return activites;
		}
	}
	
	/**
	 * Fonction qui sert à retirer une activité du fichier activitesXXXX.CSV de l'année voulue.
	 * @param numSAAQ, numéro SAAQ de l'élève qui avait réservé l'activité à annuler
	 * @param nomFichier, nom du fichier CSV des activités de l'année voulue
	 */
	public static void retirerActiviteCSV(String numSAAQ, String nomFichier) {
		ArrayList<String[]> activitesTab = GestionFichiers.lireCSV(nomFichier);
		int ID = 1;
		String nouvS = "ID_Activite,Type,NumSAAQ,Date,Heure,Duree,Montant,Statut,Plaques\n";

		for (String[] s : activitesTab) {

		    if (s[2].equals(numSAAQ)) {
		        continue;
		    }

		    s[0] = String.valueOf(ID++);
		    nouvS += String.join(",", s) + "\n";
		}

		ecrireCSV(nomFichier, nouvS);
	}
	
	/**
	 * Fonction qui sert à retirer un créneau horaire du fichier calendrierXXXX.CSV de l'année voulue.
	 * @param date, la date du créneau horaire à retirer
	 * @param creneau, le créneau horaire à retirer
	 * @param nomFichier, le nom du fichier du calendrier en CSV de l'année voulue
	 */
	public static void retirerCalendrierCSV(String date, String creneau, String nomFichier) {
		ArrayList<String[]> datesTab = GestionFichiers.lireCSV(nomFichier);
		String nouvS = "Date,heure1-duree(en min), heure2-duree, heure3-duree, ...\n";
		
		for (int i = 0; i < datesTab.size(); i++) {
	        String[] s = datesTab.get(i);

	        if (s[0].equals(date)) {
	            ArrayList<String> ligne = new ArrayList<>(Arrays.asList(s));

	            for (int j = 2; j < ligne.size(); j++) {
	                if (ligne.get(j).equals(creneau)) {
	                    ligne.remove(j);
	                    break;
	                }
	            }

	            s = ligne.toArray(new String[0]);
	        }
	        nouvS += String.join(",", s) + "\n";
		}
		ecrireCSV(nomFichier, nouvS);
	}
	
	/**
	 * Fonction qui sert à mettre à jour une journée du calendrier dans le fichier CSV
	 * @param date, objet Date contenant les nouvelles informations
	 * @param nomFichier, nom du fichier calendrier
	 */
	public static void modifierCalendrierCSV(Date date, String nomFichier) {

	    ArrayList<String[]> calendrier = lireCSV(nomFichier);
	    
	    String nouveau = "Date,heure1-duree(en min)\n";

	    for(int i=0; i<calendrier.size(); i++) {

	        String[] ligne = calendrier.get(i);

	        if (Integer.parseInt(ligne[0].trim()) == date.getJour()) {

	            TreeMap<LocalTime, Duration> indispo = date.getCreneauIndispo();

	            String s = "" + date.getJour();

	            for(LocalTime h : indispo.keySet()){
	                s += "," + h + "-" + indispo.get(h).toMinutes();
	            }


	            nouveau += s + "\n";
	        }
	        else {
	            nouveau += String.join(",", ligne) + "\n";
	        }
	    }

	    ecrireCSV(nomFichier, nouveau);
	}
	
	/**
	 * Modifie le statut d'une activité dans le fichier CSV.
	 * @param activite activité à modifier
	 * @param nomFichier nom du fichier CSV
	 */
	public static void modifierActiviteCSV(Activite activite, String nomFichier) {

	    ArrayList<String[]> activitesTab = lireCSV(nomFichier);

	    String nouvS =
	        "ID,Type,SAAQ,Date,Heure,Duree,Montant,Statut,Plaque\n";

	    int id = 1;

	    for (int i = 0; i < activitesTab.size(); i++) {

	        String[] s = activitesTab.get(i);

	        if (s[2].equals(activite.getNumSAAQ())
	                && s[3].equals(String.valueOf(activite.getDate()))
	                && s[4].equals(activite.getHeure().toString())) {

	            s[7] = activite.getStatut().toString();
	        }

	        s[0] = String.valueOf(id++);
	        nouvS += String.join(",", s) + "\n";
	    }

	    ecrireCSV(nomFichier, nouvS);
	}
	
	/**
	 * Fonction qui sert à créer et retourner l'instance de la voiture courante à partir des fichiers voituresXXXX.csv. 
	 * Permet aussi de gérer les fichiers CSV des voitures: mise à jour des données si l'année vient de changer, 
	 * création d'un nouveau fichier si celui de cette année n'existe pas encore.
	 * @param nomFichier, le nom du fichier CSV des voitures de l'année courante
	 * @return voiture, l'instance de la voiture courante
	 */
	public static Voiture voituresCSV(String nomFichier) {
		
		ArrayList<String[]> voitureS = lireCSV(nomFichier);
		Voiture voiture;

		//Si le fichier pour l'année courante n'existait pas, alors on vient de changer d'année
		//donc les données doivent être mises à jour
		if(voitureS == null || voitureS.isEmpty()) {
	
			String fVoiturePrec = ("./CSV/voitures/voitures" + 
									(LocalDate.now(Clock.systemUTC()).getYear() - 1) + ".csv");
			
			ArrayList<String[]> voitureSPrec = lireCSV(fVoiturePrec);
			
			String[] sTab = voitureSPrec.get(voitureSPrec.size() - 1);
			String s = String.join(",", sTab);
			s += "\n";
			
			ecrireCSV(nomFichier, "Marque,Plaque,Annee,Prix,KmAchat,Etat,Kms\n" + s);
			
			voiture = new Voiture(sTab[0], Integer.parseInt(sTab[2]), sTab[1], Double.parseDouble(sTab[3]), Integer.parseInt(sTab[4]), 
								  Integer.parseInt(sTab[6]), Voiture.Etat.valueOf(sTab[5]));
			return voiture;
		}
		//Si le fichier existait
		else {
						
			String[] sTab = voitureS.get(voitureS.size() - 1);
						
			voiture = new Voiture(sTab[0], Integer.parseInt(sTab[2]), sTab[1], Double.parseDouble(sTab[3]), Integer.parseInt(sTab[4]), 
							  Integer.parseInt(sTab[6]), Voiture.Etat.valueOf(sTab[5]));
			return voiture;
		}
	}

}
	

