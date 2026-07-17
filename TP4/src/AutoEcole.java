import java.io.*;
import java.time.*;
import java.util.*;

public class AutoEcole {
	static Scanner scanner = new Scanner(System.in); //le scanner de la classe
	private ArrayList<Eleve> eleves; //la liste de tous les eleves inscrit à l'auto-école
	private Eleve utilisateur; //l'utilisateur du programme

	/**
	 * Fonction qui permet de lire les entrées des utilisateurs et qui quit l'application dès que 'Q'
	 * est entré.
	 * @return input, l'entrée de l'utilisateur
	 */
    public static String getInput() {
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("Q")) {
            System.out.println("Au revoir!");
            System.exit(0);
        }

        return input;
    }
    
    public void testGetInput() {
		System.out.println("Tests getInput()");
	}
    
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
    
	/**
	 * Fonction qui sert à gérer l'inscription d'un.e nouvel.le élève
	 * @return nouvelEleve, le nouvel élève (objet Eleve)
	 */
    public Eleve inscriptionEleve() {
    	Clock horloge = Clock.systemUTC();
    	String entree;
    	String[] questions = {"Veuillez rentrer votre nom :", "Veuillez rentrer votre prénom :",
    						  "Veuillez rentrer votre adresse (au format - sans les crochets []: "
    						  + "[numéro de rue] [nom de la rue] [ville] [province] [code postal]) :", 
    						  "Veuillez rentrer votre numéro de téléphone (au format : 514123456) :",
    						  "Veuillez rentrer votre numéro SAAQ (au format : 123456789) :",
    						  "Veuillez rentrer le mot de passe que vous désirez :"};
    	String[] reponses;
    	while(true) {
    		reponses = new String[6];
    		
    		for (int i = 0; i<questions.length; i++) {
    			System.out.println(questions[i]);
    			entree = getInput();
    			
    			if(i == 2) {
    				if(entree.matches("[a-zA-Z0-9]+") == false) {
    					System.out.println("Format incorrect.");
    					return null;
    				}
    			}
    			
    			if(i == 3 || i == 4) {
    				try {
    					Integer.parseInt(entree);
    				}
    				catch(Exception e){
    					System.out.println("Format incorrect.");
    					return null;
    				}
    			}
    			reponses[i] = entree;
    			
    		}
    		
    		System.out.println("Les informations suivantes sont-elles correctes? (Oui ou Non)");
    		System.out.println("Nom: " + reponses[0] + "Prénom: " + reponses[1] + 
    						   "Adresse: " + reponses[2] + "Numéro de téléphone: " + reponses[3] + 
    						   "Numéro SAAQ: " + reponses[4]);
    		
    		if(getInput().equalsIgnoreCase("Oui")) {
    			break;
    		}
    	}
    	int numTelephone = Integer.parseInt(reponses[3]);
    	int numSAAQ = Integer.parseInt(reponses[4]);
    	
    	Eleve nouvelEleve = new Eleve(LocalDate.now(horloge), reponses[0], reponses[1], reponses[2],
    								  numTelephone, numSAAQ, reponses[5]);
    	
    	String texte = String.join(",", reponses[4], reponses[0], reponses[1], reponses[2], reponses[3], LocalDate.now(horloge).toString(), " ");
    	String fichier = "./CSV/eleves" + LocalDate.now(horloge).getYear() + ".CSV";
    	ecrireCSV(fichier, texte);
    	return nouvelEleve;
    }
    
    public void testInscriptionEleve() {
    	System.out.println("Tests inscriptionEleve()");
	}
    
    /**
     * Fonction qui sert à gérer le menu de planification d'une activité (annulation et ajout).
     */
    public void gererActivite() {
    	//pas planifier si: activite deja prevue ou si derniere activite pas encore payee
    	utilisateur = eleves.get(0);
    	System.out.println("Veuillez choisir une option:\n1 Planifier une activité"
    			+ "\n2 Annuler une activité");
    	
    	String entree = getInput();
    	if(entree.equals("1")) {
    		if(utilisateur.getActivitePrevue()) {
        		System.out.println("Vous ne pouvez pas planifier plus d'une activité à l'avance.");
        		return;
        	}
    		else if(utilisateur.impaye()) {
    			System.out.println("Vous ne pouvez pas planifier une activité si vous n'avez pas "
    								+ "fini de payer pour celle précédente.");
        		return;
    		}
    		else {
    			
    		}
    	}
    }
    
    public void annulererActivite() {
    	
    }
    
    public void planifierActivite() {
    	utilisateur = eleves.get(0);
    	Calendrier calendrier = new Calendrier(); //on crée un calendrier
    	
    	while(true) {
    		Date[][] semaines = calendrier.afficherCalendrierMois(); //on affiche le mois
        	System.out.println("Veuillez indiquer le numéro de la semaine voulue:");
        	String entree = getInput();
        	
        	//On gère les erreurs d'entrées possibles de l'utilisateur avec un try/catch
        	try {
        		int semaine = Integer.parseInt(entree); //l'index de la semaine choisie
        		
        		//On vérifie si la semaine existe, sinon on recommence la boucle
        		if(semaine > semaines.length) {
        			System.out.println("Cette semaine est indisponible.");
            		continue;
        		}
        		
        		long duree = utilisateur.getTempsLecon(); //la durée de la lecon
        		System.out.println("Durée de votre prochaine activité: " +  duree);
        		System.out.println("Veuillez indiquer le créneau horaire choisi au format jour-HH:MM"
        							+ "(n'oubliez pas de choisir en fonction de la durée de "
        							+ "votre activité): ");
        		
        		calendrier.afficherCalendrierSemaine(semaines[semaine]); //on affiche les créneaux horaires diponibles 
        																 //de la semaine choisie
        		entree = getInput();
        		
        		//On créer nos variables nécessaires en fonction de l'entrée utilisateur
    			String[] s = entree.split("-");
        		LocalTime h = LocalTime.parse(s[1]);
        		int jour = Integer.parseInt(s[0]);
        		
        		//On vérifie si le créneaux horaire choisi existe, sinon on recommence la boucle
        		if(!(semaines[semaine][jour-1].getCreneauDispo().containsKey(h))) {
        			System.out.println("Ce créneau est indisponible.");
            		continue;
        		}
        		
        		
        	}
        	catch(Exception e) {
        		System.out.println("Format incorrect.");
        		continue;
        	}
    	}
    	
    }
    
    /**
     * Fonction qui sert à démarrer l'application
     */
    public void demarrer() {
    }
}
