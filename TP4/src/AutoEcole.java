import java.io.*;
import java.time.*;
import java.util.*;

public class AutoEcole {
	static Scanner scanner = new Scanner(System.in); //le scanner de la classe
	private ArrayList<Eleve> eleves; //la liste de tous les eleves inscrit à l'auto-école
	private Eleve utilisateur; //l'utilisateur du programme
	private Calendrier calendrier; //le calendrier lié à cette instance
	private ArrayList<Activite> activites;
	private Moniteur moniteur;
	private Voiture voiture;

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
     * Fonction qui sert à démarrer l'application
     */
    public void demarrer() {
    	String fEleves = "./CSV/eleves/eleves" +
    					 LocalDate.now(Clock.systemUTC()).getYear() +
    					 ".csv";
    	this.eleves = GestionFichiers.elevesCSV(fEleves);
    	
    	String fActivites = "./CSV/activites/activites" +
				 LocalDate.now(Clock.systemUTC()).getYear() +
				 ".csv";
    	this.activites = GestionFichiers.elevesCSV(fActivites);
    	
    	this.menuPrincipal();
    }
    
    /**
     * 
     */
    public void menuPrincipal() {
    	while(true) {
    		System.out.println("Bonjour!");
        	System.out.println("Veuillez choisir une option parmi celles suivantes:");
        	System.out.println("1 Inscription d’un(e) nouvel(le) élève");
        	System.out.println("2 Accéder au menu des élèves");
        	System.out.println("3 Accéder au menu instructeur");
        	System.out.println("Veuillez noter quand tout temps inscrire la lettre Q vous"
        					 + "fera quitter l'application.");
        	
        	String entree = getInput().trim();
        	
        	if(entree.equals("1")) {
        		this.inscriptionEleve();
        	}
        	else if(entree.equals("2")) {
        		this.menuEleve();
    		}
        	else if(entree.equals("3")) {
        		//this.menuInstructeur();
    		}
        	else {
        		System.out.println("Cette option est indisponible.");
        		continue;
        	}
    	}
    	
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
    	
    	Eleve nouvelEleve = new Eleve(LocalDate.now(horloge), reponses[0], reponses[1], reponses[2],
    								  reponses[3], reponses[4], reponses[5]);
    	
    	String texte = String.join(",", reponses[4], reponses[0], reponses[1], reponses[2], reponses[3], LocalDate.now(horloge).toString(), " ");
    	String fichier = "./CSV/eleves" + LocalDate.now(horloge).getYear() + ".CSV";
    	GestionFichiers.ecrireCSV(fichier, texte);
    	return nouvelEleve;
    }
    
    public void testInscriptionEleve() {
    	System.out.println("Tests inscriptionEleve()");
	}
    
    /**
     * 
     */
    public void menuEleve() {
    	while(true) {
    		String[] eleve = new String[2];
        	System.out.println("Veuillez rentrer votre numéro SAAQ:");
        	eleve[0] = getInput();
        	System.out.println("Veuillez rentrer votre mot de passe:");
        	eleve[1] = getInput();
        	
        	for(Eleve e : this.eleves) {
        		if (e.getNumSAAQ().equals(eleve[0]) && e.getMotDePasse().equals(eleve[1])) {
        			this.utilisateur = e;
        			this.optionsEleve();
        		}
        	}
        	System.out.println("Numéro SAAQ et/ou mot de passe incorrecte.");
        	continue;
    	}
    	
    }
    
    /**
     * 
     */
    public void optionsEleve() {
    	System.out.println("Bienvenue " + this.utilisateur.getPrenom() + " " 
    						+ this.utilisateur.getNom());
    	while(true) {
    		System.out.println("Veuillez choisir une option parmi celles suivantes:");
        	System.out.println("1 Planifier/annuler une activité");
        	System.out.println("2 Faire un paiement");
        	
        	String entree = getInput();
        	
        	if(entree.equals("1")) {
        		this.gererActivite();
        	}
        	else if(entree.equals("2")) {
        		this.gererPaiement();
        	}
        	else {
        		System.out.println("Cette option est indisponible.");
        		continue;
        	}
    	}
    }
    
    /**
     * Fonction qui sert à gérer le menu de planification d'une activité (annulation et ajout).
     */
    public void gererActivite() {
    	//pas planifier si: activite deja prevue ou si derniere activite pas encore payee
    	utilisateur = eleves.get(0);
    	System.out.println("Veuillez choisir une option: ");
    	System.out.println("1 Planifier une activité");
        System.out.println("2 Annuler une activité");
    	
    	String entree = getInput();
    	
    	//on veut planifier une activite
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
    			this.planifierActivite();
    		}
    	}
    	//on veut annuler une activite
    	else if(entree.equals("2")) {
    		if(utilisateur.getActivitePrevue()) {
    			this.annulerActivite();
    		}
    		else {
    			System.out.println("Vous ne pouvez pas annuler une activité si vous"
    					+ "n'avez aucune activité prévue.");
    			return;
    		}
    	}
    }
    
    public void annulerActivite() {
    	//annuler activite dans le rapport
    	//annuler activite pour leleve
    	//delete l'instance de l'activite
    }
    
    public void planifierActivite() {
    	//GERE SI EXAMEN
    	utilisateur = eleves.get(0);
    	this.calendrier = new Calendrier(); //on crée un calendrier
    	
    	while(true) {
    		utilisateur.getActiviteToString();
    		Date[][] semaines = this.calendrier.afficherCalendrierMois(); //on affiche le mois
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
        		
        		this.calendrier.afficherCalendrierSemaine(semaines[semaine]); //on affiche les créneaux horaires diponibles 
        																 //de la semaine choisie
        		entree = getInput();
        		
        		//On créer nos variables nécessaires en fonction de l'entrée utilisateur
    			String[] s = entree.split("-");
        		LocalTime h = LocalTime.parse(s[1]);
        		int jour = Integer.parseInt(s[0]);
        		
        		Date d = semaines[semaine][jour-1];
        		//On vérifie si le créneaux horaire choisi existe, sinon on recommence la boucle
        		if(!(d.getCreneauDispo().containsKey(h))) {
        			System.out.println("Ce créneau est indisponible.");
            		continue;
        		}
        		
        		d.ajouterIndispo(h, Duration.ofMinutes(duree));
        		System.out.println("Allez-vous utiliser la voiture de l'auto-école? (Oui ou Non");
        		entree = getInput();
        		
        		String plaque;
        		boolean voitureExt;
        		if(entree.equalsIgnoreCase("oui")) {
        			voitureExt = false;
        			System.out.println("Veuillez rentrer le numéro d'immatriculation de "
        					+ "votre véhicule:");
        			plaque = getInput();
        			System.out.println("Le numéro d'immatriculation de votre véhicule est"
        					+ " " + plaque);
        		}
        		else if(entree.equalsIgnoreCase("non")) {
        			voitureExt = true;
        		}
        		else {
        			System.out.println("Format incorrecte.");
        			continue;
        		}
        		
        		Activite activite = new Activite(d, h, Duration.ofMinutes(duree), this.utilisateur, 
        				new Moniteur(), voitureExt, this.utilisateur.getTypeActivite(), Activite.Statut.NC);
        		
        		this.utilisateur.setLecon(this.utilisateur.getLecon().next());
        		this.utilisateur.setActivite(activite);
        		this.utilisateur.setActivitePrevue(true);
        		
        		String fCalendrier = ("./CSV/calendriers/calendrier" +
        							 LocalDate.now(Clock.systemUTC()).getYear() +
        							 ".csv");
        		
        		TreeMap<LocalTime, Duration> dispo = d.getCreneauDispo();
        		if (dispo.isEmpty()) {
        			d.rendreIndispo();
        		}
        		
        		//Date, estIndispo, heure1-duree(en min), heure2-duree, heure3-duree, ...
        		String s1 = (d.getJour() + ", " + String.valueOf(d.checkIndispo()) + ", ");
        		
        		for(LocalTime t : dispo.keySet()) {
        			String st = t.toString();
        			String sd = String.valueOf(dispo.get(t).toMinutes());
        			
        			s1 += (st + "-" + sd + ", ");
        		}
        		GestionFichiers.ecrireCSV(fCalendrier, s1);
        		this.calendrier = new Calendrier();
        		
        		
        		//ID_Activite,Type,NumSAAQ,Date,Heure,Duree,Montant,Statut,Plaque
        		//changer activite.csv
        		
        	}
        	catch(Exception e) {
        		System.out.println("Format incorrect.");
        		continue;
        	}
    	}
    	
    }
    
    /**
     * 
     */
    public void gererPaiement() {
    	
    }
    
    
    
    
}
