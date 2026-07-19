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
	

	public AutoEcole() {
		this.fEleves = ("./CSV/eleves/eleves" +
				 LocalDate.now().getYear() +
				 ".csv");
		this.eleves = GestionFichiers.elevesCSV(this.fEleves);
	
		this.fVoiture = ("./CSV/voitures/voiture" +
			 LocalDate.now().getYear() +
			 ".csv");
		
		this.voiture = GestionFichiers.voituresCSV(this.fVoiture);
	
		this.fActivites = ("./CSV/activites/activites" +
			 LocalDate.now().getYear() +
			 ".csv");
		this.activites = GestionFichiers.activitesCSV(this.fActivites, this.voiture);
	
		this.fCalendrier = ("./CSV/calendriers/calendrier" +
			 LocalDate.now().getMonthValue() +
			 ".csv");
	
		this.calendrier = new Calendrier();
	
		this.moniteur = new Moniteur();
	}
	
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
    
    
    /**
     * Fonction qui sert à gérer le menu textuel principal de l'utilisateur
     */
    public void menuPrincipal() {
    	System.out.println("Bonjour!");
    	while(true) {
        	System.out.println("Veuillez choisir une option parmi celles suivantes:");
        	System.out.println("1 Inscription d’un(e) nouvel(le) élève");
        	System.out.println("2 Accéder au menu des élèves");
        	System.out.println("3 Accéder au menu instructeur");
        	System.out.println("Veuillez noter qu'en tout temps inscrire la lettre Q vous"
        					 + "fera quitter l'application.");
        	
        	String entree = getInput();
        	
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
    						  "Veuillez rentrer votre numéro de téléphone (au format : 5141234567) :",
    						  "Veuillez rentrer votre numéro SAAQ (au format : 123456789) :",
    						  "Veuillez rentrer le mot de passe que vous désirez :"};
    	String[] reponses;
    	while(true) {
    		reponses = new String[6];
    		
    		for (int i = 0; i<questions.length; i++) {
    			System.out.println(questions[i]);
    			entree = getInput();
    			    			
    			if(i == 3) {
					if(!(entree.length() == 10)) {
						System.out.println("Format incorrect.");
						return null;
					}
    				
    			}
    			else if(i == 4) {
					if(!(entree.length() == 9)) {
						System.out.println("Format incorrect.");
						return null;
					}
    			}
    			reponses[i] = entree;
    			
    		}
    		
    		System.out.println("Les informations suivantes sont-elles correctes? (Oui ou Non)");
    		System.out.println("Nom: " + reponses[0] + " Prénom: " + reponses[1] + 
    						   " Adresse: " + reponses[2] + " Numéro de téléphone: " + reponses[3] + 
    						   " Numéro SAAQ: " + reponses[4]);
    		
    		if(getInput().equalsIgnoreCase("Oui")) {
    			break;
    		}
    	}
    	Eleve nouvelEleve = new Eleve(LocalDate.now(), reponses[0], reponses[1], reponses[2],
    								  reponses[3], reponses[4], reponses[5]);
    	
    	String texte = (reponses[4] + "," + reponses[5] + "," + reponses[0] + "," + reponses[1] + "," +
    		    		reponses[2] + "," + reponses[3] + "," + LocalDate.now() + ", " + "\n");
    	
    	GestionFichiers.ajouterCSV(this.fEleves, texte);
    	this.eleves.add(nouvelEleve);
    	System.out.println("Vos informations ont été enregistrées.");
    	return nouvelEleve;
    }
    
    /**
     * 
     * @param numSAAQ
     * @param motDePasse
     * @return
     */
    public Eleve authentifierEleve(String numSAAQ, String motDePasse) {

        for (Eleve e : this.eleves) {
            if (e.getNumSAAQ().equals(numSAAQ)
                && e.getMotDePasse().equals(motDePasse)) {
                return e;
            }
        }

        return null;
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
        	
        	Eleve e = authentifierEleve(eleve[0], eleve[1]);

            if (e != null) {
                utilisateur = e;
                optionsEleve();
                return;
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

        Eleve eleve = (Eleve) this.utilisateur;

        if (!eleve.getActivitePrevue()) {
            System.out.println("Vous n'avez aucune activité prévue.");
            return;
        }

        eleve.getActivitePrevueToString();
        System.out.println("Voulez-vous l'annuler? (Oui ou Non)");

        String entree = getInput();

        if (!entree.equalsIgnoreCase("oui")) {

            if (entree.equalsIgnoreCase("non")) {
                System.out.println("Votre activité n'a pas été annulée.");
            }
            else {
                System.out.println("Format incorrect.");
            }

            return;
        }

        Activite activite = eleve.getActivite();

        // Libérer le créneau dans le calendrier
        for (Date d : calendrier.getDates()) {

            if (d.getJour() == activite.getDate()) {

                GestionFichiers.modifierCalendrierCSV(d, this.fCalendrier);
                break;
            }
        }

        // Retirer l'activité du fichier CSV
        GestionFichiers.retirerActiviteCSV(
                eleve.getNumSAAQ(),
                this.fActivites);

        // Retirer de la liste
        this.activites.remove(activite);

        // Modifier l'élève
        eleve.setActivite(null);
        eleve.setActivitePrevue(false);
        eleve.setLecon(eleve.getLecon().previous());

        calendrier = new Calendrier();

        System.out.println("Votre activité a été annulée.");
    }
    
    /**
     * Fonction qui sert à planifier une activité.
     */
    public void planifierActivite() {
    	//GERER EXAM
        Eleve eleve = (Eleve) utilisateur;

        while (true) {

            eleve.getActiviteToString();

            Date[][] semaines = calendrier.afficherCalendrierMois();

            System.out.println("Veuillez indiquer le numéro de la semaine voulue:");

            try {

                int semaine = Integer.parseInt(getInput());

                if (semaine < 1 || semaine > semaines.length) {
                    System.out.println("Cette semaine est indisponible.");
                    continue;
                }

                long duree = eleve.getTempsLecon();

                System.out.println("Durée de votre prochaine activité : "
                        + duree + " minutes");

                calendrier.afficherCalendrierSemaine(semaines[semaine - 1]);

                System.out.println(
                        "Veuillez entrer le créneau (jour-HH:MM) :");

                String[] choix = getInput().split("-");

                int indexJour = Integer.parseInt(choix[0]) - 1;
                LocalTime heure = LocalTime.parse(choix[1]);

                Date dateChoisie = semaines[semaine - 1][indexJour];

                if (dateChoisie == null) {
                    System.out.println("Jour invalide.");
                    continue;
                }

                TreeMap<LocalTime, Duration> dispo = dateChoisie.getCreneauDispo();

                boolean possible = false;

                for(LocalTime debut : dispo.keySet()){

                    LocalTime fin = debut.plus(dispo.get(debut));

                    if(!heure.isBefore(debut)
                        && heure.plusMinutes(duree).compareTo(fin) <= 0){

                        possible = true;
                        break;
                    }
                }

                if(!possible){
                    System.out.println("Ce créneau est indisponible.");
                    continue;
                }

                // Réserver le créneau
                if (!dateChoisie.ajouterIndispo(heure, Duration.ofMinutes(duree))) {
                    System.out.println("Ce créneau est déjà réservé.");
                    continue;
                }

                System.out.println("Votre créneau horaire a été réservé.");

                GestionFichiers.modifierCalendrierCSV(
                        dateChoisie,
                        fCalendrier);

                calendrier = new Calendrier();

                System.out.println(
                        "Utiliserez-vous la voiture de l'auto-école ? (Oui ou Non)");

                String entree = getInput();

                boolean voitureAutoEcole;
                String plaque;

                if (entree.equalsIgnoreCase("oui")) {

                    voitureAutoEcole = true;
                    plaque = voiture.getPlaque();

                }
                else if (entree.equalsIgnoreCase("non")) {

                    voitureAutoEcole = false;

                    System.out.println(
                            "Veuillez entrer votre numéro d'immatriculation :");

                    plaque = getInput();
                }
                else {

                    System.out.println("Format incorrect.");
                    continue;
                }

                LocalDate date = LocalDate.of(
                        LocalDate.now().getYear(),
                        LocalDate.now().getMonth(),
                        dateChoisie.getJour());

                Activite activite = new Activite(
                        date,
                        heure,
                        Duration.ofMinutes(duree),
                        eleve.getNumSAAQ(),
                        moniteur,
                        voitureAutoEcole,
                        eleve.getTypeActivite(),
                        Activite.Statut.NC);

                eleve.setActivite(activite);
                eleve.setActivitePrevue(true);
                eleve.setLecon(eleve.getLecon().next());

                activites.add(activite);

                String ligne =
                        activites.size() + "," +
                        activite.getType() + "," +
                        activite.getNumSAAQ() + "," +
                        date + "," +
                        activite.getHeure() + "," +
                        activite.getDuree() + "," +
                        activite.getMontant() + "," +
                        activite.getStatut() + "," +
                        plaque;

                GestionFichiers.ajouterCSV(fActivites, ligne);

                System.out.println("Votre activité a été enregistrée.");

                return;

            }
            catch (Exception e) {
                System.out.println("Format incorrect.");
                return;
            }
        }
    }
    
    /**
     * 
     */
    public void gererPaiement() {
    	//if exam + paye, alors eleve date fin de leleve est ajoute au fichier CSV et 
    	//on le retire de la liste actuelle d'eleves
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
    			return;
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
        				break;
        			case 2:
        				//generer rapport revenus
        				break;
        			case 3:
        				//generer rapport depenses voiture
        				break;
        			case 4:
        				//generer rapport depenses autres
        				break;
        			case 5:
        				this.visionnerDispo();
        				break;
        			case 6:
        				this.modifierDispo();
        				break;
        			case 7:
        				this.completerActivite();
        				break;
        		}
        	}
        	catch(Exception e) {
        		System.out.println("Option indisponible.");
        		e.printStackTrace();
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
    		this.calendrier.afficherCalendrierSemaine(dates[entree-1]);
    		return dates[entree-1];
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
        	
        	System.out.println("Veuillez indiquer l'heure de début de la disponibilité à ajouter (au format HH:MM):");
        	entree = getInput();
        	LocalTime h = LocalTime.parse(entree);
        	
        	System.out.println("Veuillez indiquer la durée de la disponibilité à ajouter (en min):");
        	entree = getInput();
        	int duree = Integer.parseInt(entree);
    		
    		//On vérifie si le créneaux horaire choisi existe dans les indisponibilités
    		if(!(d.getCreneauIndispo().containsKey(h))) {
    			
    			//On ajoute le créneau horaire choisi aux disponibilités de la date
    			//d.enleverIndispo(h, Duration.ofMinutes(duree));
        		System.out.println("Le créneau horaire a été ajouté aux disponibilités de la date.");
    		}
    		
    		d.enleverIndispo(h);
    		GestionFichiers.modifierCalendrierCSV(
    		    d,
    		    this.fCalendrier
    		);
    		
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
        	
        	System.out.println("Veuillez indiquer l'heure de début de l'indisponibilité à ajouter (au format HH:MM):");
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
    		
    		GestionFichiers.modifierCalendrierCSV(
    			    d,
    			    this.fCalendrier
    			);
    		
    		return;
    	}
    	catch (Exception e) {
    		System.out.println("Format incorrecte.");
    	} 	
    }
    
    
    
    /**
     * Fonction qui sert à marquer une activité comme complétée.
     */
    public void completerActivite() {

        ArrayList<Activite> passees = new ArrayList<>();

        int aujourdHui = LocalDate.now().getDayOfMonth();
        LocalTime maintenant = LocalTime.now();

        // On récupère toutes les activités passées qui ne sont pas encore complétées
        for (Activite a : this.activites) {

            if (a.getStatut() == Activite.Statut.C) {
                continue;
            }

            // Activité d'un jour précédent
            if (a.getDate() < aujourdHui) {
                passees.add(a);
            }

            // Activité aujourd'hui, mais déjà commencée
            LocalTime heureActivite = LocalTime.parse(a.getHeure());

            if (a.getDate() == aujourdHui &&
                !heureActivite.isAfter(maintenant)) {

                passees.add(a);
            }
        }

        if (passees.isEmpty()) {
            System.out.println("Aucune activité passée à compléter.");
            return;
        }

        System.out.println("Liste des activités passées :");

        for (Activite a : passees) {
            System.out.println(
                "Jour : " + a.getDate()
                + " | Heure : " + a.getHeure()
                + " | Élève : " + a.getNumSAAQ()
                + " | Type : " + a.getType()
            );
        }

        System.out.println("Veuillez entrer le jour de l'activité :");
        int jour;

        try {
            jour = Integer.parseInt(getInput());
        }
        catch (Exception e) {
            System.out.println("Format incorrect.");
            return;
        }

        System.out.println("Veuillez entrer l'heure de l'activité (HH:MM) :");
        LocalTime heure;

        try {
            heure = LocalTime.parse(getInput());
        }
        catch (Exception e) {
            System.out.println("Format incorrect.");
            return;
        }

        Activite activite = null;

        for (Activite a : passees) {

            if (a.getDate() == jour &&
                a.getHeure().equals(heure.toString())) {

                activite = a;
                break;
            }
        }

        if (activite == null) {
            System.out.println("Aucune activité trouvée.");
            return;
        }

        System.out.println("Marquer l'activité du "
                + activite.getDate()
                + " à "
                + activite.getHeure()
                + " comme complétée ? (Oui ou Non)");

        if (!getInput().equalsIgnoreCase("Oui")) {
            System.out.println("Opération annulée.");
            return;
        }

        // Modification du statut
        activite.setStatut(Activite.Statut.C);

        // Mise à jour du CSV
        GestionFichiers.modifierActiviteCSV(activite, this.fActivites);

        // Recherche de l'élève
        Eleve eleve = null;

        for (Eleve e : this.eleves) {
            if (e.getNumSAAQ().equals(activite.getNumSAAQ())) {
                eleve = e;
                break;
            }
        }

        if (eleve != null) {

            // Générer la facture
            // eleve.ajouterFacture(activite);

            // Si tu décides finalement de faire avancer la leçon ici
            // eleve.setLecon(eleve.getLecon().next());

            // Si c'est le dernier examen, tu pourras aussi ici
            // marquer l'élève comme diplômé.
        }

        System.out.println("L'activité a été marquée comme complétée.");
        System.out.println("Une facture a été envoyée à l'élève.");
    }
}
