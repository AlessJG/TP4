import java.time.*;
import java.time.format.*;
import java.util.*;

public class AutoEcole {
	static Scanner scanner = new Scanner(System.in); //le scanner de la classe
	private ArrayList<Eleve> eleves; //la liste de tous les eleves inscrit à l'auto-école
	private ArrayList<Activite> activites; //la liste de toutes les activités prévues pour cette années
	private ArrayList<Paiement> factures;
	private Personne utilisateur; //l'utilisateur du programme
	private Calendrier calendrier; //le calendrier lié à cette instance
	private Moniteur moniteur; //le moniteur de l'auto-école
	private Voiture voiture; //la voiture de l'auto-école
	
	private String fActivites; //le nom du fichier CSV des activites de cette année
	private String fCalendrier; //le nom du fichier CSV du calendrier de ce mois
	private String fVoiture; //le nom du fichier CSV des voitures de cette année
	private String fEleves; //le nom du fichier CSV des élèves de cette année
	private String fFactures;
	private String fAutresDepenses;
	private String fDepensesVoiture;
	private String fPaiements;
	

	/**
	 * Constructeur, initisalise les variables globales en récupérant les données 
	 * depuis les fichiers CSV correspondants
	 */
	public AutoEcole() {
		
		//fichiers CSV
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
		
		this.fFactures = "./CSV/factures/factures"
		        + LocalDate.now().getYear() + ".csv";

		this.factures = GestionFichiers.facturesCSV(
		        this.fFactures,
		        this.eleves,
		        this.activites);
		
		//fichiers textes
		
		this.fAutresDepenses = "./CSV/autres_depenses/autres_depenses"
		        + LocalDate.now().getYear() + ".txt";
		
		this.fDepensesVoiture = "./CSV/autres_depenses/depenses_voiture"
		        + LocalDate.now().getYear() + ".txt";
		
		this.fPaiements = "./CSV/autres_depenses/autres_depenses"
		        + LocalDate.now().getYear() + ".txt";
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
        					 + " fera quitter l'application.");
        	
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
    	
    	//On pose les questions et on demande les réponses à l'utilisateur en prenant en compte 
    	//des erreurs possibles des entrées de l'utilisateur
    	while(true) {
    		reponses = new String[6];
    		
    		for (int i = 0; i<questions.length; i++) {
    			System.out.println(questions[i]);
    			entree = getInput();
    			    
    			//Un numéro de téléphone doit contenir 10 chiffres
    			if(i == 3) {
					if(!(entree.length() == 10)) {
						System.out.println("Format incorrect.");
						return null;
					}
    				
    			}
    			
    			//Un numéro SAAQ doit contenir 9 chiffres
    			else if(i == 4) {
					if(!(entree.length() == 9)) {
						System.out.println("Format incorrect.");
						return null;
					}
    			}
    			
    			//On stock les réponses dans un tableau
    			reponses[i] = entree;
    			
    		}
    		
    		//Si l'utilisateur pense avoir rentré les bonnes informations, on l'inscrit
    		//Sinon, on recommence la boucle de questions réponses jusqu'à ce que ce soit bon
    		System.out.println("Les informations suivantes sont-elles correctes? (Oui ou Non)");
    		System.out.println("Nom: " + reponses[0] + " Prénom: " + reponses[1] + 
    						   " Adresse: " + reponses[2] + " Numéro de téléphone: " + reponses[3] + 
    						   " Numéro SAAQ: " + reponses[4]);
    		
    		if(getInput().equalsIgnoreCase("Oui")) {
    			break;
    		}
    	}
    	//Une fois que les informations sont correctes, on créé une nouvelle instance d'Eleve
    	Eleve nouvelEleve = new Eleve(LocalDate.now(), reponses[0], reponses[1], reponses[2],
    								  reponses[3], reponses[4], reponses[5]);
    	
    	//On l'enregistre dans notre base de données (elevesXXXX.csv)
    	String texte = (reponses[4] + "," + reponses[5] + "," + reponses[0] + "," + reponses[1] + "," +
    		    		reponses[2] + "," + reponses[3] + "," + LocalDate.now() + ", " + "\n");
    	
    	GestionFichiers.ajouterCSV(this.fEleves, texte);
    	
    	//On l'ajoute à la liste courante d'élève, 
    	//on laisse savoir à l'utilisateur que l'opération a été un succès
    	//et la nouvelle instance de l'Eleve est retournée
    	this.eleves.add(nouvelEleve);
    	System.out.println("Vos informations ont été enregistrées.");
    	return nouvelEleve;
    }
    
    /**
     * Fonction qui sert à vérifier l'identité de l'élève selon son numéro SAAQ et son mot de passe
     * @param numSAAQ
     * @param motDePasse
     * @return l'élève à qui les informations correspondent, sinon null
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
     * Fonction qui sert à authetifier et démarrer le menu textuel de l'élève
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
                this.utilisateur = e;
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
     * Fonction qui sert à gérer le menu de planification d'une activité (annulation et ajout)
     * Note: il est impossible de planifier plus d'une activité à l'avance et de planifier
     * une activité si celle précédente n'a pas encore été complètement payée 
     */
    public void gererActivite() {
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
    					+ "n'avez déjà aucune activité prévue.");
    			return;
    		}
    	}
    }
    
    /**
     * Fonction qui sert à annuler une activité de l'élève
     */
    public void annulerActivite() {

        Eleve eleve = (Eleve) this.utilisateur;

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
        for (Date d : this.calendrier.getDates()) {

            if (d.getJour() == activite.getDate()) {

                GestionFichiers.modifierCalendrierCSV(d, this.fCalendrier);
                break;
            }
        }

        // Retirer l'activité du fichier activiteXXXX.csv
        GestionFichiers.retirerActiviteCSV(
                eleve.getNumSAAQ(),
                this.fActivites);

        // La retirer de la liste courante des activités 
        this.activites.remove(activite);

        // Modifier les informations de l'élève en conséquence
        eleve.setActivite(null);
        eleve.setActivitePrevue(false);
        eleve.setLecon(eleve.getLecon().previous());

        //Réinitialiser l'instance du calendrier afin 
        //qu'il contienne les nouvelles informations
        this.calendrier = new Calendrier();

        System.out.println("Votre activité a été annulée.");
    }
    
    /**
     * Fonction qui sert à planifier une activité.
     */
    public void planifierActivite() {
        Eleve eleve = (Eleve) this.utilisateur;

        while (true) {

            eleve.getActiviteToString();

            //On affiche le calendrier du mois
            Date[][] semaines = this.calendrier.afficherCalendrierMois();

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

                //On affiche les disponibilités de la semaine choisie
                this.calendrier.afficherCalendrierSemaine(semaines[semaine - 1]);

                System.out.println(
                        "Veuillez entrer le créneau (jour-HH:MM) :");

                String[] choix = getInput().split("-");

                int indexJour = Integer.parseInt(choix[0]) - 1;
                LocalTime heure = LocalTime.parse(choix[1]);

                Date dateChoisie = semaines[semaine - 1][indexJour];

                //On vérifie si la date choisie est disponible
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

                // On vérifie si le créneau est disponible
                if (!dateChoisie.ajouterIndispo(heure, Duration.ofMinutes(duree))) {
                    System.out.println("Ce créneau est déjà réservé.");
                    continue;
                }

                //On vérifie si la voiture de l'auto-cole va être usilisée (sauf pour une leçon ou un examen théorique)
                //Si oui: la plaque liée à l'activité va être celle de l'auto-école
                //et le boolean voitureExt va être false
                //Si non : la plaque liée à l'activité va être celle rentrée par l'utilisateur
                //et le boolean voitureExt va être true
                boolean voitureAutoEcole;
                String plaque;
                
                if(!(eleve.getTypeActivite(false).equals(Activite.TypeActivite.LT) ||
                		eleve.getTypeActivite(false).equals(Activite.TypeActivite.ET))) {
                	System.out.println(
                            "Utiliserez-vous la voiture de l'auto-école ? (Oui ou Non)");

                    String entree = getInput();

                    if (entree.equalsIgnoreCase("oui")) {

                        voitureAutoEcole = true;
                        plaque = this.voiture.getPlaque();

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
                }
                else {
                	 voitureAutoEcole = false;
                	 plaque = this.voiture.getPlaque();
                }
                

              //On met à jour le fichier calendrierX.csv
                GestionFichiers.modifierCalendrierCSV(
                        dateChoisie,
                        this.fCalendrier);

              //Réinitialiser l'instance du calendrier afin 
                //qu'il contienne les nouvelles informations
                this.calendrier = new Calendrier();
                
                System.out.println("Votre créneau horaire a été réservé.");
                
                //On crée un nouvelle instance d'Activite
                LocalDate date = LocalDate.of(
                        LocalDate.now().getYear(),
                        LocalDate.now().getMonth(),
                        dateChoisie.getJour());

                Activite activite = new Activite(
                        date,
                        heure,
                        Duration.ofMinutes(duree),
                        eleve.getNumSAAQ(),
                        this.moniteur,
                        voitureAutoEcole,
                        eleve.getTypeActivite(!voitureAutoEcole),
                        Activite.Statut.NC);

                //On modifie les informations de l'élève en conséquence
                eleve.setActivite(activite);
                eleve.setActivitePrevue(true);
                eleve.setLecon(eleve.getLecon().next());

                //On ajotue l'activité à la liste courante d'activités
                this.activites.add(activite);

                //On enregistre l'activité dans la base de données dans activitesXXXX.csv
                String ligne =
                        "\n" + this.activites.size() + "," +
                        activite.getType() + "," +
                        activite.getNumSAAQ() + "," +
                        date + "," +
                        activite.getHeure() + "," +
                        activite.getDuree() + "," +
                        activite.getMontant() + "," +
                        activite.getStatut() + "," +
                        plaque;

                GestionFichiers.ajouterCSV(this.fActivites, ligne);
                
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
     * Fonction qui sert à gérer un paiement de l'élève.
     */
    public void gererPaiement() {

        Eleve eleve = (Eleve) this.utilisateur;

        // Recherche de la facture impayée ou partiellement payée
        Paiement facture = null;

        for (Paiement p : this.factures) {

            if (p.getEleve().getNumSAAQ().equals(eleve.getNumSAAQ())
                    && p.getStatut() != Paiement.Statut.P) {

                facture = p;
                break;
            }
        }

        if (facture == null) {
            System.out.println("Vous n'avez aucune facture à payer.");
            return;
        }

        // Affichage de la facture
        System.out.println("Votre facture :");
        System.out.println(facture);

        // Lecture du montant
        System.out.println("Veuillez entrer le montant que vous désirez payer :");

        double montantPaye;

        try {
            montantPaye = Double.parseDouble(getInput());

            if (montantPaye <= 0) {
                System.out.println("Montant invalide.");
                return;
            }

        } catch (Exception e) {
            System.out.println("Montant invalide.");
            return;
        }

        // Lecture de la méthode de paiement
        System.out.println("Choisissez une méthode de paiement :");
        System.out.println("1 Espèces");
        System.out.println("2 Carte");
        System.out.println("3 Virement");

        Paiement.Methode methode;

        switch (getInput()) {

            case "1":
                methode = Paiement.Methode.E;
                break;

            case "2":
                methode = Paiement.Methode.C;
                break;

            case "3":
                methode = Paiement.Methode.V;
                break;

            default:
                System.out.println("Méthode invalide.");
                return;
        }

        // Confirmation
        System.out.println("Confirmer le paiement de "
                + montantPaye
                + "$ ? (Oui ou Non)");

        if (!getInput().equalsIgnoreCase("Oui")) {
            System.out.println("Paiement annulé.");
            return;
        }

        // Calcul du montant restant
        double restant = facture.getMontant() - montantPaye;

        if (restant <= 0) {

            facture.setMontant(0);
            facture.setStatut(Paiement.Statut.P);

            System.out.println("Votre facture est maintenant entièrement payée.");

        }
        else {

            facture.setMontant(restant);
            facture.setStatut(Paiement.Statut.PP);

            System.out.println("Paiement enregistré.");
            System.out.println("Il vous reste "
                    + restant
                    + "$ à payer.");
        }

        facture.setMethode(methode);

        // Mise à jour du fichier CSV
        GestionFichiers.modifierFactureCSV(
                facture,
                this.fFactures);

        // Si c'était le dernier examen et que tout est payé,
        // l'élève termine sa formation
        if (facture.getStatut() == Paiement.Statut.P &&
            (facture.getActivite().getType() == Activite.TypeActivite.EP
            || facture.getActivite().getType() == Activite.TypeActivite.EPL)) {

            eleve.setDateFin(LocalDate.now());

            GestionFichiers.modifierEleveCSV(
                    eleve,
                    this.fEleves);

            System.out.println("Félicitations !");
            System.out.println("Votre formation est maintenant terminée.");
        }
    }
    
    /**
     * Génère une facture pour une activité complétée.
     * @param eleve l'élève
     * @param activite l'activité complétée
     */
    public void genererPaiement(Eleve eleve, Activite activite) {

        String numeroUnique = "F-" + LocalDate.now().getYear() + "-";

        for (int i = 0; i < 5; i++) {
            numeroUnique += (int)(Math.random() * 10);
        }

        Paiement facture = new Paiement(
                numeroUnique,
                activite.getMontant(),
                LocalDate.now(),
                eleve,
                activite,
                Paiement.Statut.I,
                null);
        
        eleve.setFacture(facture);
        this.factures.add(facture);

        GestionFichiers.ajouterFactureCSV(
                facture,
                this.fFactures);

        System.out.println("Une facture a été générée.");
    }
    
    /**
     * Fonction qui sert à authentifier l'instructeur et à gérer son menu textuel
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
        				genererRapportEleve();
        				break;
        			case 2:
        				genererRapportRevenus();
        				break;
        			case 3:
        				genererRapportDepensesVoiture();
        				break;
        			case 4:
        				genererRapportDepensesAutres();
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
    
    public void genererRapportEleve() {
    	
    	 System.out.println("Date de début (JJ-MM-AAAA) :");
    	    LocalDate debutEleve = LocalDate.parse(
    	            getInput(),
    	            DateTimeFormatter.ofPattern("dd-MM-yyyy")
    	    );

    	    System.out.println("Date de fin (JJ-MM-AAAA) :");
    	    LocalDate finEleve = LocalDate.parse(
    	            getInput(),
    	            DateTimeFormatter.ofPattern("dd-MM-yyyy")
    	    );

    	    System.out.println("Dossier où enregistrer le rapport :");
    	    String dossierEleve = getInput();

    	    Rapport.rapportEleves(
    	            this.fEleves,
    	            debutEleve,
    	            finEleve,
    	            dossierEleve
    	    );

    	    System.out.println("Rapport des élèves généré avec succès.");
    }
    
    public void genererRapportRevenus() {
    	System.out.println("Date de début (JJ-MM-AAAA) :");
        LocalDate debutRevenus = LocalDate.parse(
                getInput(),
                DateTimeFormatter.ofPattern("dd-MM-yyyy")
        );

        System.out.println("Date de fin (JJ-MM-AAAA) :");
        LocalDate finRevenus = LocalDate.parse(
                getInput(),
                DateTimeFormatter.ofPattern("dd-MM-yyyy")
        );

        System.out.println("Dossier où enregistrer le rapport :");
        String dossierRevenus = getInput();

        Rapport.rapportRevenus(
                this.fPaiements,
                debutRevenus,
                finRevenus,
                dossierRevenus
        );

        System.out.println("Rapport des revenus généré avec succès.");
    }
    
    public void genererRapportDepensesVoiture() {
    	System.out.println("Date de début (JJ-MM-AAAA) :");
        LocalDate debutDepensesVoiture = LocalDate.parse(
                getInput(),
                DateTimeFormatter.ofPattern("dd-MM-yyyy")
        );

        System.out.println("Date de fin (JJ-MM-AAAA) :");
        LocalDate finDepensesVoiture = LocalDate.parse(
                getInput(),
                DateTimeFormatter.ofPattern("dd-MM-yyyy")
        );

        System.out.println("Dossier où enregistrer le rapport :");
        String dossierDepensesVoiture = getInput();

        Rapport.rapportDepensesVoiture(
                this.fDepensesVoiture,
                debutDepensesVoiture,
                finDepensesVoiture,
                dossierDepensesVoiture
        );

        System.out.println("Rapport des dépenses voiture généré avec succès.");
    }
    
    public void genererRapportDepensesAutres() {
    	 System.out.println("Date de début (JJ-MM-AAAA) :");
    	    LocalDate debutAutresDepenses = LocalDate.parse(
    	            getInput(),
    	            DateTimeFormatter.ofPattern("dd-MM-yyyy")
    	    );

    	    System.out.println("Date de fin (JJ-MM-AAAA) :");
    	    LocalDate finAutresDepenses = LocalDate.parse(
    	            getInput(),
    	            DateTimeFormatter.ofPattern("dd-MM-yyyy")
    	    );

    	    System.out.println("Dossier où enregistrer le rapport :");
    	    String dossierAutresDepenses = getInput();

    	    Rapport.rapportAutresDepenses(
    	            this.fAutresDepenses,
    	            debutAutresDepenses,
    	            finAutresDepenses,
    	            dossierAutresDepenses
    	    );

    	    System.out.println("Rapport des autres dépenses généré avec succès.");

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
    		//Si oui, on affiche ses disponibilités
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
    	
    	//On visionne les disponibilités
    	Date[] dates = this.visionnerDispo();
    	if(dates == null) {
    		return;
    	}
    	
    	//On offre les options
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
    	
    	//Réinitialiser l'instance du calendrier afin 
        //qu'il contienne les nouvelles informations
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
    		
    		//On vérifie si le créneaux horaire choisi existe dans les indisponibilités
    		if(!(d.getCreneauIndispo().containsKey(h))) {
    			
    			//On ajoute le créneau horaire choisi aux disponibilités de la date
    			d.enleverIndispo(h);
    			
    			//On modifie le fichier calendrierX.csv en conséquence
    			GestionFichiers.modifierCalendrierCSV(
    	    		    d,
    	    		    this.fCalendrier
    	    		);
        		System.out.println("Le créneau horaire a été ajouté aux disponibilités de la date.");
    		}
    		else {
    			System.out.println("Ce créneau horaire était déjà disponible.");
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
        	
        	System.out.println("Veuillez indiquer l'heure de début de l'indisponibilité à ajouter (au format HH:MM):");
        	entree = getInput();
        	LocalTime h = LocalTime.parse(entree);
        	
        	System.out.println("Veuillez indiquer la durée de l'indisponibilité à ajouter (en min):");
        	entree = getInput();
        	int duree = Integer.parseInt(entree);
    		
    		//On vérifie si le créneaux horaire choisi existe déjà dans les indisponibilités
    		if(!(d.getCreneauIndispo().containsKey(h))) {
    			
    			//On ajoute le créneau horaire choisi aux indisponibilités de la date
    			d.ajouterIndispo(h, Duration.ofMinutes(duree));
    			
    			//On modifie le fichier calendrierX.csv en conséquence
    			GestionFichiers.modifierCalendrierCSV(
        			    d,
        			    this.fCalendrier
        			);
    			
        		System.out.println("Le créneau horaire a été ajouté aux indisponibilités de la date.");
    		}
    		else {
    			System.out.println("Ce créneau était déjà insiponible.");
    		}
    		
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

        LocalDate aujourdHui = LocalDate.now();
        LocalTime maintenant = LocalTime.now();

        // Recherche des activités passées non complétées
        for (Activite a : this.activites) {

            if (a.getStatut() == Activite.Statut.C)
                continue;

            if (a.getLocalDate().isBefore(aujourdHui)) {

                passees.add(a);

            } else if (a.getLocalDate().equals(aujourdHui)) {

                LocalTime heure = LocalTime.parse(a.getHeure());

                if (!heure.isAfter(maintenant))
                    passees.add(a);
            }
        }

        if (passees.isEmpty()) {

            System.out.println("Aucune activité passée à compléter.");
            return;
        }

        System.out.println("Activités à compléter :");

        for (Activite a : passees) {

            System.out.println(
                    "Jour : " + a.getDate()
                    + " | Heure : " + a.getHeure()
                    + " | Élève : " + a.getNumSAAQ()
                    + " | Type : " + a.getType());
        }

        int jour;

        try {

            System.out.println("Veuillez entrer le jour de l'activité :");
            jour = Integer.parseInt(getInput());

        } catch (Exception e) {

            System.out.println("Format incorrect.");
            return;
        }

        LocalTime heure;

        try {

            System.out.println("Veuillez entrer l'heure (HH:MM) :");
            heure = LocalTime.parse(getInput());

        } catch (Exception e) {

            System.out.println("Format incorrect.");
            return;
        }

        Activite activite = null;

        for (Activite a : passees) {

            if (a.getDate() == jour
                    && a.getHeure().equals(heure.toString())) {

                activite = a;
                break;
            }
        }

        if (activite == null) {

            System.out.println("Activité introuvable.");
            return;
        }

        System.out.println("Confirmer que cette activité est complétée ? (Oui ou Non)");

        if (!getInput().equalsIgnoreCase("Oui")) {

            System.out.println("Opération annulée.");
            return;
        }

        activite.setStatut(Activite.Statut.C);

        GestionFichiers.modifierActiviteCSV(
                activite,
                this.fActivites);

        Eleve eleve = null;

        for (Eleve e : this.eleves) {

            if (e.getNumSAAQ().equals(activite.getNumSAAQ())) {

                eleve = e;
                break;
            }
        }

        if (eleve != null) {

            genererPaiement(eleve, activite);

            System.out.println("Une facture a été générée pour l'élève.");
        }

        System.out.println("L'activité a été marquée comme complétée.");
    }
}
