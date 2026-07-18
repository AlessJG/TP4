import java.io.*;
import java.time.*;
import java.util.*;

public class AutoEcole {
	static Scanner scanner = new Scanner(System.in); //le scanner de la classe
	private ArrayList<Eleve> eleves; //la liste de tous les eleves inscrit à l'auto-école
	private ArrayList<Activite> activites; //la liste de toutes les activités prévues pour cette années
	private Personne utilisateur; //l'utilisateur du programme
	private Calendrier calendrier; //le calendrier lié à cette instance
	private Moniteur moniteur; //le moniteur de l'auto-école
	private Voiture voiture; //la voiture de l'auto-école
	private String fActivites; //le nom du fichier CSV des activites de cette année
	private String fCalendrier; //le nom du fichier CSV du calendrier de ce mois
	private String fVoiture; //le nom du fichier CSV des voitures de cette année
	private String fEleves; //le nom du fichier CSV des élèves de cette année
	

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
     * Fonction qui sert à démarrer l'application, elle initialise les variables globales de la classe
     */
    public void demarrer() {
    	this.fEleves = "./CSV/eleves/eleves" +
    					 LocalDate.now(Clock.systemUTC()).getYear() +
    					 ".csv";
    	this.eleves = GestionFichiers.elevesCSV(this.fEleves);
    	
    	this.fVoiture = "./CSV/voitures/voiture" +
				 LocalDate.now(Clock.systemUTC()).getYear() +
				 ".csv";
    	this.voiture = GestionFichiers.voituresCSV(this.fVoiture);
    	
    	this.fActivites = "./CSV/activites/activites" +
				 LocalDate.now(Clock.systemUTC()).getYear() +
				 ".csv";
    	this.activites = GestionFichiers.activitesCSV(this.fActivites, this.voiture);
    	
    	this.fCalendrier = ("./CSV/calendriers/calendrier" +
				 LocalDate.now(Clock.systemUTC()).getYear() +
				 ".csv");
    	
    	this.calendrier = new Calendrier();
    	
    	this.moniteur = new Moniteur();
    	this.menuPrincipal();
    }
    
    /**
     * Fonction qui sert à gérer le menu textuel principal de l'utilisateur
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
        		this.menuInstructeur();
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
    	
    	Eleve nouvelEleve = new Eleve(LocalDate.now(Clock.systemUTC()), reponses[0], reponses[1], reponses[2],
    								  reponses[3], reponses[4], reponses[5]);
    	
    	String texte = (reponses[4] + "," + reponses[0] + "," + reponses[1] + "," + reponses[2] + "," + reponses[3] + "," 
    				   + LocalDate.now(Clock.systemUTC()) + "," + " " + "\n");
    	GestionFichiers.ecrireCSV(this.fEleves, texte);
    	return nouvelEleve;
    }
    
    public void testInscriptionEleve() {
    	System.out.println("Tests inscriptionEleve()");
	}
    
    /**
     * Fonction qui sert à gérer le menu textuel de l'élève
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
     * Fonction qui sert à gérer le menu textuel des options de l'élève
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
    	System.out.println("Veuillez choisir une option: ");
    	System.out.println("1 Planifier une activité");
        System.out.println("2 Annuler une activité");
    	
    	String entree = getInput();
    	
    	//on veut planifier une activite
    	if(entree.equals("1")) {
    		if(((Eleve) this.utilisateur).getActivitePrevue()) {
        		System.out.println("Vous ne pouvez pas planifier plus d'une activité à l'avance.");
        		return;
        	}
    		else if(((Eleve) this.utilisateur).impaye()) {
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
    		if(((Eleve) this.utilisateur).getActivitePrevue()) {
    			this.annulerActivite();
    		}
    		else {
    			System.out.println("Vous ne pouvez pas annuler une activité si vous"
    					+ "n'avez aucune activité prévue.");
    			return;
    		}
    	}
    }
    
    /**
     * Fonction qui sert à annuler une activité de l'élève
     */
    public void annulerActivite() {
    	//On vérifie si l'utilisateur a bel et bien une activité à annuler
    	if(!(((Eleve) this.utilisateur).getActivitePrevue())) {
    		System.out.println("Vous n’avez déjà aucune activité prévue.");
    		return;
    	}
    	else {
    		((Eleve) this.utilisateur).getActivitePrevueToString();
    		System.out.println("Voulez-vous l'annuler? (Oui ou Non)");
    		String entree = getInput();
    		
    		//On vérifie si l'élève veut vraiment annuler sa prochaine activité
    		if(entree.equalsIgnoreCase("oui")) {
    			
    			//On modifie les informations de l'élève concernant l'activité
    			((Eleve) this.utilisateur).setActivite(null);
    			((Eleve) this.utilisateur).setActivitePrevue(false);
    			((Eleve) this.utilisateur).setLecon(((Eleve) this.utilisateur).getLecon().previous());
    			
    			//On retire l'activité du fichier CSV dans lequelle elle était enregistrée
    			GestionFichiers.retirerActiviteCSV(((Eleve) this.utilisateur).getNumSAAQ(), fActivites);
    			
    			//On libère le créneau horaire du calendrier et de son fichier CSV
    			//Date, estIndispo, heure1-duree(en min), heure2-duree, heure3-duree, ...
    			String creneau = ((Eleve) this.utilisateur).getActivite().getHeure() + "-" + ((Eleve) this.utilisateur).getActivite().getDuree();
    			GestionFichiers.retirerCalendrierCSV(String.valueOf(((Eleve) this.utilisateur).getActivite().getDate()), 
    												 creneau, this.fCalendrier);
    			
    			//On retire l'activité de la liste des activités courantes
    			this.activites.remove(((Eleve) this.utilisateur).getActivite());
    			
    			System.out.println("Votre activité a été annulée.");
    		}
    		else if(entree.equalsIgnoreCase("non")) {
    			System.out.println("Votre activité n'a pas été annulée.");
    		}
    		else {
    			System.out.println("Format incorrecte.");
    		}
    		return;
    		
    	}
    }
    
    /**
     * Fonction qui sert à planifier une activité de l'élève
     */
    public void planifierActivite() {
    	//GERE SI EXAMEN
    	
    	while(true) {
    		((Eleve) this.utilisateur).getActiviteToString();
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
        		
        		long duree = ((Eleve) this.utilisateur).getTempsLecon(); //la durée de la lecon
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
        		
        		//On ajoute le créneau horaire choisi aux indisponibilités de la date
        		d.ajouterIndispo(h, Duration.ofMinutes(duree));
        		System.out.println("Votre créneau horaire a été reservé.");
        		
        		//On vérifie si l'élève utilisera le véhicule de l'auto-école
        		System.out.println("Allez-vous utiliser la voiture de l'auto-école? (Oui ou Non");
        		entree = getInput();
        		
        		String plaque;
        		boolean voitureExt;
        		if(entree.equalsIgnoreCase("oui")) {
        			voitureExt = false;
        			System.out.println("Veuillez rentrer le numéro d'immatriculation de "
        					+ "votre véhicule:");
        			plaque = getInput();
        		}
        		else if(entree.equalsIgnoreCase("non")) {
        			voitureExt = true;
        			plaque = this.voiture.getPlaque();
        		}
        		else {
        			System.out.println("Format incorrecte.");
        			continue;
        		}
        		
        		LocalDate date = LocalDate.of(LocalDate.now(Clock.systemUTC()).getYear(),
        									  LocalDate.now(Clock.systemUTC()).getMonth(), 
        									  d.getJour());
        		
        		//On crée une nouvelle activité (objet) en fonction des informations données
        		Activite activite = new Activite(date, h, Duration.ofMinutes(duree), ((Eleve) this.utilisateur).getNumSAAQ(), 
        				this.moniteur, voitureExt, ((Eleve) this.utilisateur).getTypeActivite(), Activite.Statut.NC);
        		
        		//On modifie les informations de l'élèves concernant une nouvelle activité prévue
        		((Eleve) this.utilisateur).setLecon(((Eleve) this.utilisateur).getLecon().next());
        		((Eleve) this.utilisateur).setActivite(activite);
        		((Eleve) this.utilisateur).setActivitePrevue(true);
        		
        		//On vérifie si l'ajout d'une activité à cette date rend cette date indisponible
        		TreeMap<LocalTime, Duration> dispo = d.getCreneauDispo();
        		if (dispo.isEmpty()) {
        			d.rendreIndispo();
        		}
        		
        		//On ajoute dans le fichier CSV du calendrier le créneau horaire choisi aux indisponibilités de la date
        		String s1 = (d.getJour() + ", " + String.valueOf(d.checkIndispo()) + ", ");
        		
        		for(LocalTime t : dispo.keySet()) {
        			String st = t.toString();
        			String sd = String.valueOf(dispo.get(t).toMinutes());
        			
        			s1 += (st + "-" + sd + ", ");
        		}
        		s1 += "\n";
        		GestionFichiers.ecrireCSV(this.fCalendrier, s1);
        		this.calendrier = new Calendrier();
        		
        		//On enregistre la nouvelle activité créée dans le fichier CSV des activités
        		String dateS = (date.getDayOfMonth() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth());
        		String s2 = ((activites.size() + 1) + ", " + activite.getType().toString() + ", " + activite.getNumSAAQ() + ", " + 
        					  dateS + ", " + activite.getHeure() + ", " + activite.getMontant() + ", " + activite.getStatut().toString() +
        					  plaque + "\n");
        		GestionFichiers.ecrireCSV(fActivites, s2);
        		
        		//On ajoute la nouvelle activité à la liste des activités de cette instance
        		this.activites.add(activite);
        		
        		System.out.println("Votre activité a été enregistrée.");
        		
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
    
    /**
     * Fonction qui sert à gérer le menu textuel de l'instructeur
     */
    public void menuInstructeur() {
    	while(true) {
    		String[] instructeur = new String[2];
    		System.out.println("Veuillez rentrer votre numero de permis :");
        	instructeur[0] = getInput();
        	System.out.println("Veuillez rentrer votre mot de passe:");
        	instructeur[1] = getInput();
        	
    		if (this.moniteur.getNumPermis().equals(instructeur[0]) && this.moniteur.getMotDePasse().equals(instructeur[1])) {
    			this.utilisateur = this.moniteur;
    			this.optionsInstructeur();
    		}
        	
        	System.out.println("Numéros de permis et/ou mot de passe incorrecte.");
        	continue;
    	}
    }
    
    /**
     * Fonction qui sert à gérer le menu textuel des options de l'instructeur
     */
    public void optionsInstructeur() {
    	System.out.println("Bienvenue " + this.utilisateur.getPrenom() + " " + this.utilisateur.getNom());
    	while(true) {
    		System.out.println("Veuillez choisir une option parmi celles suivantes:");
        	System.out.println("1 Générer un rapport des élèves");
        	System.out.println("2 Générer un rapport des revenus");
        	System.out.println("3 Générer un rapport des dépenses de la voiture");
        	System.out.println("4 Générer un rapport des autres dépenses");
        	System.out.println("5 Visionner les disponibilités d’une semaine");
        	System.out.println("6 Modifier vos disponibilités de la semaine");
        	System.out.println("7 Marquer une activité comme complétée");
        	
        	try {
        		int entree = Integer.parseInt(getInput());
        		switch (entree){
        			case 1:
        				//generer rapport eleve
        			case 2:
        				//generer rapport revenus
        			case 3:
        				//generer rapport depenses voiture
        			case 4:
        				//generer rapport depenses autres
        			case 5:
        				this.visionnerDispo();
        			case 6:
        				this.modifierDispo();
        			case 7:
        				this.completerActivite();
        		}
        	}
        	catch(Exception e) {
        		System.out.println("Option indisponible.");
        		continue;
        	}
    	}
    }
    
    /**
     * Fonction qui sert à visionner les disponibilités de la semaine
     * @return une liste des dates de la semaine
     */
    public Date[] visionnerDispo() {
    	Date[][] dates = this.calendrier.afficherCalendrierMois();
    	System.out.println("Veuillez indiquer la semaine choisie:");
    	try {
    		int entree = Integer.parseInt(getInput());
    		
    		//On vérifie si la semaine existe
    		if(entree > dates.length) {
    			System.out.println("Cette semaine est indisponible.");
        		return null;
    		}
    		System.out.println("Voici les disponibilités de la semaine:");
    		this.calendrier.afficherCalendrierSemaine(dates[entree]);
    		return dates[entree];
    	}
    	catch(Exception e) {
    		System.out.println("Format incorrecte.");
    		return null;
    	}
    }
    
    /**
     * Fonction qui sert à gérer le menu textuel qui sert à modifier 
 	 * une disponibilité de l'instructeur
     */
    public void modifierDispo() {
    	Date[] dates = this.visionnerDispo();
    	if(dates == null) {
    		return;
    	}
    	
    	System.out.println("Veuillez choisir une option parmi celles suivantes:");
    	System.out.println("1 Ajouter une disponibilité");
    	System.out.println("2 Retirer une disponibilité");
    	
    	String entree = getInput();
    	
    	if(entree.equals("1")) {
    		this.ajouterDispo(dates);
    	}
    	else if(entree.equals("2")) {
    		this.retirerDispo(dates);
    	}
    	else {
    		System.out.println("Option indisponible.");
    		return;
    	}
    	this.calendrier = new Calendrier();
    }
    
    /**
     * Fonction qui sert à ajouter une disponibilité au calendrier
     * @param dates, les dates de la semaine choisie
     */
    public void ajouterDispo(Date[] dates) {
    	try {
    		System.out.println("Veuillez indiquer le jour correspondant à la date de la disponibilité à ajouter (au format J):");
        	String entree = getInput();
        	int j = Integer.parseInt(entree);
        	Date d = dates[j-1];
        	
        	System.out.println("Veuillez indiquer l'heure de début de la disponibilité à ajouter (au format HH-MM):");
        	entree = getInput();
        	LocalTime h = LocalTime.parse(entree);
        	
        	System.out.println("Veuillez indiquer la durée de la disponibilité à ajouter (en min):");
        	entree = getInput();
        	int duree = Integer.parseInt(entree);
    		
    		//On vérifie si le créneaux horaire choisi existe dans les indisponibilités
    		if(!(d.getCreneauIndispo().containsKey(h))) {
    			
    			//On ajoute le créneau horaire choisi aux disponibilités de la date
    			d.enleverIndispo(h, Duration.ofMinutes(duree));
        		System.out.println("Le créneau horaire a été ajouté aux disponibilités de la date.");
    		}
    		return;
    	}
    	catch (Exception e) {
    		System.out.println("Format incorrecte.");
    	} 	
    }
    
    /**
     * Fonction qui sert à retirer une disponibilité du calendrier
     * @param dates, les dates de la semaine choisie
     */
    public void retirerDispo(Date[] dates) {
    	try {
    		System.out.println("Veuillez indiquer le jour correspondant à la date de l'indisponibilité à ajouter (au format J):");
        	String entree = getInput();
        	int j = Integer.parseInt(entree);
        	Date d = dates[j-1];
        	
        	System.out.println("Veuillez indiquer l'heure de début de l'indisponibilité à ajouter (au format HH-MM):");
        	entree = getInput();
        	LocalTime h = LocalTime.parse(entree);
        	
        	System.out.println("Veuillez indiquer la durée de l'indisponibilité à ajouter (en min):");
        	entree = getInput();
        	int duree = Integer.parseInt(entree);
    		
    		//On vérifie si le créneaux horaire choisi existe dans les indisponibilités
    		if(!(d.getCreneauIndispo().containsKey(h))) {
    			
    			//On ajoute le créneau horaire choisi aux disponibilités de la date
    			d.ajouterIndispo(h, Duration.ofMinutes(duree));
        		System.out.println("Le créneau horaire a été ajouté aux indisponibilités de la date.");
    		}
    		return;
    	}
    	catch (Exception e) {
    		System.out.println("Format incorrecte.");
    	} 	
    }
    
    /**
     * Fonction qui sert à rendre une activité choisie par l'instructeur complétée
     */
    public void completerActivite() {
    	
    }
}
