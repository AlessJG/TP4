import java.io.*;
import java.util.*;
import java.time.*;

public class TestsGestionFichiers {
	
	public static void testsGestionFichiers() throws Exception {
		System.out.println("\n---Tests pour classe GestionFichiers---\n");
		//lireCSV
		TestsGestionFichiers.testLireCSVFichierInexistant();
		TestsGestionFichiers.testLireCSVFichierValide();
		TestsGestionFichiers.testLireCSVFichierVide();
		TestsGestionFichiers.testLireCSVHeaderSeulement();
		
		//ecrireCSV
		TestsGestionFichiers.testEcrireCSVNouveauFichier();
		TestsGestionFichiers.testEcrireCSVRemplaceContenu();
		TestsGestionFichiers.testEcrireCSVPlusieursLignes();
		TestsGestionFichiers.testEcrireCSVTexteVide();
		
		//ajouterCSV
		TestsGestionFichiers.testAjouterCSV();
		TestsGestionFichiers.testAjouterCSVFichierVide();
		TestsGestionFichiers.testAjouterCSVPlusieursLignes();
		TestsGestionFichiers.testAjouterCSVTexteVide();
		
		//elevesCSV
		TestsGestionFichiers.testElevesCSVAucunFichier();
		TestsGestionFichiers.testElevesCSVDateFin();
		TestsGestionFichiers.testElevesCSVFichierExistant();
		TestsGestionFichiers.testElevesCSVFichierVide();
		TestsGestionFichiers.testElevesCSVNouvelleAnnee();
		
		//activitesCSV
		TestsGestionFichiers.testActivitesCSVAucunFichier();
		TestsGestionFichiers.testActivitesCSVFichierExistant();
		TestsGestionFichiers.testActivitesCSVNouvelleAnnee();
		TestsGestionFichiers.testActivitesCSVVoitureEcole();
		TestsGestionFichiers.testActivitesCSVVoitureExterne();
		
		//retirerActiviteCSV
		TestsGestionFichiers.testRetirerActiviteCSV();
		TestsGestionFichiers.testRetirerActiviteCSVInexistant();
		TestsGestionFichiers.testRetirerActiviteCSVVide();
		
		//retirerCalendrierCSV
		TestsGestionFichiers.testRetirerCalendrierCSV();
		TestsGestionFichiers.testRetirerCalendrierCSVCreneauInexistant();
		TestsGestionFichiers.testRetirerCalendrierCSVDateInexistante();
		TestsGestionFichiers.testRetirerCalendrierCSVUnSeulCreneau();
		
		//modifierCalendrierCSV
		TestsGestionFichiers.testModifierCalendrierCSVAucuneIndispo();
		TestsGestionFichiers.testModifierCalendrierCSVAutresDates();
		TestsGestionFichiers.testModifierCalendrierCSVOrdre();
		TestsGestionFichiers.testModifierCalendrierCSVPlusieursCreneaux();
		TestsGestionFichiers.testModifierCalendrierCSVUnCreneau();
		
		//modifierActiviteCSV
		TestsGestionFichiers.testModifierActiviteCSV();
		TestsGestionFichiers.testModifierActiviteCSVID();
		TestsGestionFichiers.testModifierActiviteCSVIntrouvable();
		TestsGestionFichiers.testModifierActiviteCSVUneSeule();
		
		//voituresCSV
		TestsGestionFichiers.testVoituresCSVDerniereVoiture();
		TestsGestionFichiers.testVoituresCSVFichierExistant();
		TestsGestionFichiers.testVoituresCSVFichierVide();
		TestsGestionFichiers.testVoituresCSVFichierVide();
	}

    public static void testLireCSVFichierValide() throws Exception {

        File fichier = new File("test1.csv");

        PrintWriter pw = new PrintWriter(fichier);
        pw.println("Nom,Age");
        pw.println("Bobby,20");
        pw.println("Bob,25");
        pw.println("John,30");
        pw.close();

        ArrayList<String[]> resultat = GestionFichiers.lireCSV(fichier.getPath());

        if (resultat != null &&
            resultat.size() == 3 &&
            resultat.get(0)[0].equals("Bobby") &&
            resultat.get(1)[0].equals("Bob")) {

            System.out.println("OO testLireCSVFichierValide réussi");
        } else {
            System.out.println("XX testLireCSVFichierValide échoué");
        }

        fichier.delete();
    }

    public static void testLireCSVFichierVide() throws Exception {

        File fichier = new File("test2.csv");
        fichier.createNewFile();

        ArrayList<String[]> resultat = GestionFichiers.lireCSV(fichier.getPath());

        if (resultat != null && resultat.isEmpty()) {
            System.out.println("OO testLireCSVFichierVide réussi");
        } else {
            System.out.println("XX testLireCSVFichierVide échoué");
        }

        fichier.delete();
    }

    public static void testLireCSVFichierInexistant() {

        File fichier = new File("test3.csv");

        if (fichier.exists()) {
            fichier.delete();
        }

        ArrayList<String[]> resultat = GestionFichiers.lireCSV(fichier.getPath());

        if (resultat == null && fichier.exists()) {
            System.out.println("OO testLireCSVFichierInexistant réussi");
        } else {
            System.out.println("XX testLireCSVFichierInexistant échoué");
        }

        fichier.delete();
    }

    public static void testLireCSVHeaderSeulement() throws Exception {

        File fichier = new File("test4.csv");

        PrintWriter pw = new PrintWriter(fichier);
        pw.println("Nom,Age");
        pw.close();

        ArrayList<String[]> resultat = GestionFichiers.lireCSV(fichier.getPath());

        if (resultat != null && resultat.isEmpty()) {
            System.out.println("OO testLireCSVHeaderSeulement réussi");
        } else {
            System.out.println("XX testLireCSVHeaderSeulement échoué");
        }

        fichier.delete();
    }
    
    public static void testEcrireCSVNouveauFichier() throws Exception {

        File fichier = new File("test1.csv");

        if (fichier.exists()) {
            fichier.delete();
        }

        GestionFichiers.ecrire(fichier.getPath(), "Nom,Age\nBobby,20");

        BufferedReader reader = new BufferedReader(new FileReader(fichier));
        String ligne = reader.readLine();
        reader.close();

        if (ligne.equals("Nom,Age")) {
            System.out.println("OO testEcrireCSVNouveauFichier réussi");
        } else {
            System.out.println("XX testEcrireCSVNouveauFichier échoué");
        }

        fichier.delete();
    }
    
    public static void testEcrireCSVRemplaceContenu() throws Exception {

        File fichier = new File("test2.csv");

        PrintWriter pw = new PrintWriter(fichier);
        pw.println("Ancien contenu");
        pw.close();

        GestionFichiers.ecrire(fichier.getPath(), "Nouveau contenu");

        BufferedReader reader = new BufferedReader(new FileReader(fichier));
        String ligne = reader.readLine();
        reader.close();

        if (ligne.equals("Nouveau contenu")) {
            System.out.println("OO testEcrireCSVRemplaceContenu réussi");
        } else {
            System.out.println("XX testEcrireCSVRemplaceContenu échoué");
        }

        fichier.delete();
    }
    
    public static void testEcrireCSVPlusieursLignes() throws Exception {

        File fichier = new File("test3.csv");

        String texte = "Nom,Age\nBobby,20\nBob,25";

        GestionFichiers.ecrire(fichier.getPath(), texte);

        BufferedReader reader = new BufferedReader(new FileReader(fichier));

        String l1 = reader.readLine();
        String l2 = reader.readLine();
        String l3 = reader.readLine();

        reader.close();

        if (l1.equals("Nom,Age") &&
            l2.equals("Bobby,20") &&
            l3.equals("Bob,25")) {

            System.out.println("OO testEcrireCSVPlusieursLignes réussi");
        } else {
            System.out.println("XX testEcrireCSVPlusieursLignes échoué");
        }

        fichier.delete();
    }
    
    public static void testEcrireCSVTexteVide() throws Exception {

        File fichier = new File("test4.csv");

        GestionFichiers.ecrire(fichier.getPath(), "");

        if (fichier.exists() && fichier.length() == 0) {
            System.out.println("OO testEcrireCSVTexteVide réussi");
        } else {
            System.out.println("XX testEcrireCSVTexteVide échoué");
        }

        fichier.delete();
    }
    
    public static void testAjouterCSV() {

        String fichier = "./CSV/testAjouter.csv";

        GestionFichiers.ecrire(
            fichier,
            "Nom,Prenom\n" +
            "Dupont,Jean\n"
        );

        GestionFichiers.ajouterCSV(
            fichier,
            "Martin,Paul\n"
        );

        ArrayList<String[]> contenu = GestionFichiers.lireCSV(fichier);

        if (contenu.size() == 2 &&
            contenu.get(0)[0].equals("Dupont") &&
            contenu.get(1)[0].equals("Martin")) {

            System.out.println("OO testAjouterCSV réussi");
        } else {
            System.out.println("XX testAjouterCSV échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testAjouterCSVFichierVide() {

        String fichier = "./CSV/testVide.csv";

        new File(fichier).delete();

        GestionFichiers.ajouterCSV(
            fichier,
            "Nom,Prenom\n"
        );

        ArrayList<String[]> contenu = GestionFichiers.lireCSV(fichier);

        if (contenu != null && contenu.isEmpty()) {
            System.out.println("OO testAjouterCSVFichierVide réussi");
        } else {
            System.out.println("XX testAjouterCSVFichierVide échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testAjouterCSVPlusieursLignes() {

        String fichier = "./CSV/testPlusieurs.csv";

        GestionFichiers.ecrire(
            fichier,
            "Nom,Prenom\n"
        );

        GestionFichiers.ajouterCSV(fichier, "Dupont,Jean\n");
        GestionFichiers.ajouterCSV(fichier, "Martin,Paul\n");
        GestionFichiers.ajouterCSV(fichier, "Roy,Julie\n");

        ArrayList<String[]> contenu = GestionFichiers.lireCSV(fichier);

        if (contenu.size() == 3 &&
            contenu.get(2)[0].equals("Roy")) {

            System.out.println("OO testAjouterCSVPlusieursLignes réussi");
        } else {
            System.out.println("XX testAjouterCSVPlusieursLignes échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testAjouterCSVTexteVide() {

        String fichier = "./CSV/testTexteVide.csv";

        GestionFichiers.ecrire(
            fichier,
            "Nom,Prenom\n" +
            "Dupont,Jean\n"
        );

        GestionFichiers.ajouterCSV(fichier, "");

        ArrayList<String[]> contenu = GestionFichiers.lireCSV(fichier);

        if (contenu.size() == 1 &&
            contenu.get(0)[0].equals("Dupont")) {

            System.out.println("OO testAjouterCSVTexteVide réussi");
        } else {
            System.out.println("XX testAjouterCSVTexteVide échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testElevesCSVFichierExistant() {

        String fichier = "./CSV/eleves/testEleves.csv";

        String texte =
                "NumSAAQ,MotDePasse,Nom,Prenom,Adresse,Telephone,Date,DateFin\n" +
                "1234,mdp,Dupont,Jean,Montreal,5141234567,2025-01-01,\n";

        GestionFichiers.ecrire(fichier, texte);

        ArrayList<Eleve> eleves = GestionFichiers.elevesCSV(fichier);

        if (eleves.size() == 1) {
            System.out.println("OO testElevesCSVFichierExistant réussi");
        } else {
            System.out.println("XX testElevesCSVFichierExistant échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testElevesCSVAucunFichier() {

        String fichier = "./CSV/eleves/testVide.csv";

        File f = new File(fichier);

        if (f.exists()) {
            f.delete();
        }

        ArrayList<Eleve> eleves = GestionFichiers.elevesCSV(fichier);

        if (eleves.isEmpty() && f.exists()) {
            System.out.println("OO testElevesCSVAucunFichier réussi");
        } else {
            System.out.println("XX testElevesCSVAucunFichier échoué");
        }

        f.delete();
    }
    
    public static void testElevesCSVNouvelleAnnee() {

        int anneePrec = LocalDate.now().getYear() - 1;

        String ancien =
                "./CSV/eleves/eleves" + anneePrec + ".csv";

        String nouveau =
                "./CSV/eleves/testNouvelle.csv";

        GestionFichiers.ecrire(
                ancien,
                "NumSAAQ, MotDePasse, Nom, Prenom, Adresse, Telephone, Date, DateFin\n" +
                "1111,abc,Dupont,Jean,Montreal,5141234567,2025-01-01,\n"
        );

        ArrayList<Eleve> eleves = GestionFichiers.elevesCSV(nouveau);

        if (eleves.size() == 1) {
            System.out.println("OO testElevesCSVNouvelleAnnee réussi");
        } else {
            System.out.println("XX testElevesCSVNouvelleAnnee échoué");
        }

        new File(ancien).delete();
        new File(nouveau).delete();
    }
    
    public static void testElevesCSVDateFin() {

        String fichier = "./CSV/eleves/testDateFin.csv";

        GestionFichiers.ecrire(
                fichier,
                "NumSAAQ, MotDePasse, Nom, Prenom, Adresse, Telephone, Date, DateFin\n" +
                "1111,abc,Dupont,Jean,Montreal,5141234567,2025-01-01,\n" +
                "2222,xyz,Tremblay,Paul,Quebec,4181111111,2025-02-02,2025-06-02\n"
        );

        ArrayList<Eleve> eleves = GestionFichiers.elevesCSV(fichier);
       
        if (eleves.size() == 1) {
            System.out.println("OO testElevesCSVDateFin réussi");
        } else {
            System.out.println("XX testElevesCSVDateFin échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testElevesCSVFichierVide() {

        String fichier = "./CSV/eleves/testVide.csv";

        GestionFichiers.ecrire(fichier, "");

        ArrayList<Eleve> eleves = GestionFichiers.elevesCSV(fichier);

        if (eleves.isEmpty()) {
            System.out.println("OO testElevesCSVFichierVide réussi");
        } else {
            System.out.println("XX testElevesCSVFichierVide échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testActivitesCSVFichierExistant() {

        String fichier = "./CSV/activites/testActivites.csv";

        Voiture voiture = new Voiture("Toyota",
        	    2023,
        	    "ABC123",
        	    25000.0,
        	    0,
        	    15000,
        	    Voiture.Etat.D);

        GestionFichiers.ecrire(
            fichier,
            "ID_Activite,Type,NumSAAQ,Date,Heure,Duree,Montant,Statut,Plaques\n" +
            "1,EPL,1234,2026-07-15,09:00,60,50.0,NC,ABC123\n"
        );

        ArrayList<Activite> activites =
                GestionFichiers.activitesCSV(fichier, voiture);

        if (activites.size() == 1) {
            System.out.println("OO testActivitesCSVFichierExistant réussi");
        } else {
            System.out.println("XX testActivitesCSVFichierExistant échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testActivitesCSVAucunFichier() {

        String fichier = "./CSV/activites/testVide.csv";

        File f = new File(fichier);

        if (f.exists()) {
            f.delete();
        }

        Voiture voiture = new Voiture("Toyota",
        	    2023,
        	    "ABC123",
        	    25000.0,
        	    0,
        	    15000,
        	    Voiture.Etat.D);

        ArrayList<Activite> activites =
                GestionFichiers.activitesCSV(fichier, voiture);

        if (activites.isEmpty() && f.exists()) {
            System.out.println("OO testActivitesCSVAucunFichier réussi");
        } else {
            System.out.println("XX testActivitesCSVAucunFichier échoué");
        }

        f.delete();
    }
    
    public static void testActivitesCSVNouvelleAnnee() {

        int anneePrec = LocalDate.now().getYear() - 1;

        String ancien =
                "./CSV/activites/activites" + anneePrec + ".csv";

        String nouveau =
                "./CSV/activites/testNouvelle.csv";

        Voiture voiture = new Voiture("Toyota",
        	    2023,
        	    "ABC123",
        	    25000.0,
        	    0,
        	    15000,
        	    Voiture.Etat.D);

        GestionFichiers.ecrire(
            ancien,
            "ID_Activite,Type,NumSAAQ,Date,Heure,Duree,Montant,Statut,Plaques\n" +
            "1,LPS,1234," + LocalDate.now().getYear() +
            "-01-01,09:00,90,50.0,C,ABC123\n"
        );

        ArrayList<Activite> activites =
                GestionFichiers.activitesCSV(nouveau, voiture);

        if (activites.size() == 1) {
            System.out.println("OO testActivitesCSVNouvelleAnnee réussi");
        } else {
            System.out.println("XX testActivitesCSVNouvelleAnnee échoué");
        }

        new File(ancien).delete();
        new File(nouveau).delete();
    }
    
    public static void testActivitesCSVVoitureEcole() {

        String fichier = "./CSV/activites/testVoiture.csv";

        Voiture voiture = new Voiture("Toyota",
        	    2023,
        	    "ABC123",
        	    25000.0,
        	    0,
        	    15000,
        	    Voiture.Etat.D);

        GestionFichiers.ecrire(
            fichier,
            "ID_Activite,Type,NumSAAQ,Date,Heure,Duree,Montant,Statut,Plaques\n" +
            "1,LPZ,1234,2026-07-15,09:00,90,50.0,NC,ABC123\n"
        );

        ArrayList<Activite> activites =
                GestionFichiers.activitesCSV(fichier, voiture);
        
        if (!activites.get(0).getVoitureExt()) {
            System.out.println("OO testActivitesCSVVoitureEcole réussi");
        } else {
            System.out.println("XX testActivitesCSVVoitureEcole échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testActivitesCSVVoitureExterne() {

        String fichier = "./CSV/activites/testVoitureExt.csv";

        Voiture voiture = new Voiture("Toyota",
        	    2023,
        	    "ABC123",
        	    25000.0,
        	    0,
        	    15000,
        	    Voiture.Etat.D);

        GestionFichiers.ecrire(
            fichier,
            "ID_Activite,Type,NumSAAQ,Date,Heure,Duree,Montant,Statut,Plaques\n" +
            "1,LT,1234,2026-07-15,09:00,60,50.0,C,XYZ999\n"
        );

        ArrayList<Activite> activites =
                GestionFichiers.activitesCSV(fichier, voiture);

        if (activites.size() == 1 &&
        		activites.get(0).getVoitureExt()) {
            System.out.println("OO testActivitesCSVVoitureExterne réussi");
        } else {
            System.out.println("XX testActivitesCSVVoitureExterne échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testRetirerActiviteCSV() {

        String fichier = "./CSV/activites/testRetirer.csv";

        GestionFichiers.ecrire(
            fichier,
            "ID_Activite,Type,NumSAAQ,Date,Heure,Duree,Montant,Statut,Plaques\n" +
            "1,LPZ,1111,2026-07-15,09:00,90,50,NC,ABC123\n" +
            "2,LT,2222,2026-02-01,10:00,60,50,NC,ABC123\n"
        );

        GestionFichiers.retirerActiviteCSV("1111", fichier);

        ArrayList<String[]> activites = GestionFichiers.lireCSV(fichier);

        if (activites.size() == 1 &&
            activites.get(0)[2].equals("2222")) {

            System.out.println("OO testRetirerActiviteCSV réussi");
        } else {
            System.out.println("XX testRetirerActiviteCSV échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testRetirerActiviteCSVInexistant() {

        String fichier = "./CSV/activites/testInexistant.csv";

        GestionFichiers.ecrire(
            fichier,
            "ID_Activite,Type,NumSAAQ,Date,Heure,Duree,Montant,Statut,Plaques\n" +
                    "1,LPZ,1111,2026-01-01,09:00,90,50,NC,ABC123\n"
        );

        GestionFichiers.retirerActiviteCSV("9999", fichier);

        ArrayList<String[]> activites = GestionFichiers.lireCSV(fichier);

        if (activites.size() == 1) {
            System.out.println("OO testRetirerActiviteCSVInexistant réussi");
        } else {
            System.out.println("XX testRetirerActiviteCSVInexistant échoué");
        }

        new File(fichier).delete();
    }
     
    public static void testRetirerActiviteCSVVide() {

        String fichier = "./CSV/activites/testVide.csv";

        GestionFichiers.ecrire(fichier, "");

        GestionFichiers.retirerActiviteCSV("1111", fichier);

        ArrayList<String[]> activites = GestionFichiers.lireCSV(fichier);

        if (activites.isEmpty()) {
            System.out.println("OO testRetirerActiviteCSVVide réussi");
        } else {
            System.out.println("XX testRetirerActiviteCSVVide échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testRetirerCalendrierCSV() {

        String fichier = "./CSV/calendriers/testCalendrier.csv";

        GestionFichiers.ecrire(
            fichier,
            "Date,estIndispo,Creneau1,Creneau2,Creneau3\n" +
            "15,false,09:00-60,10:00-90,11:00-60\n"
        );

        GestionFichiers.retirerCalendrierCSV("15", "10:00-90", fichier);

        ArrayList<String[]> calendrier = GestionFichiers.lireCSV(fichier);
        
        if (calendrier.size() == 1 &&
            calendrier.get(0).length == 4 &&
            calendrier.get(0)[0].equals("15") &&
            calendrier.get(0)[1].equals("false") &&
            calendrier.get(0)[2].equals("09:00-60") &&
            calendrier.get(0)[3].equals("11:00-60")) {

            System.out.println("OO testRetirerCalendrierCSV réussi");
        } else {
            System.out.println("XX testRetirerCalendrierCSV échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testRetirerCalendrierCSVCreneauInexistant() {

        String fichier = "./CSV/calendriers/testCalendrier.csv";

        GestionFichiers.ecrire(
            fichier,
            "Date,estIndispo,Creneau1,Creneau2\n" +
            "15,false,09:00-60,10:00-90\n"
        );

        GestionFichiers.retirerCalendrierCSV("15", "15:00-60", fichier);

        ArrayList<String[]> calendrier = GestionFichiers.lireCSV(fichier);

        if (calendrier.size() == 1 &&
            calendrier.get(0).length == 4 &&
            calendrier.get(0)[2].equals("09:00-60") &&
            calendrier.get(0)[3].equals("10:00-90")) {

            System.out.println("OO testRetirerCalendrierCSVCreneauInexistant réussi");
        } else {
            System.out.println("XX testRetirerCalendrierCSVCreneauInexistant échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testRetirerCalendrierCSVDateInexistante() {

        String fichier = "./CSV/calendriers/testCalendrier.csv";

        GestionFichiers.ecrire(
            fichier,
            "Date,estIndispo,Creneau1,Creneau2\n" +
            "15,false,09:00-60,10:00-90\n"
        );

        GestionFichiers.retirerCalendrierCSV("20", "09:00-60", fichier);

        ArrayList<String[]> calendrier = GestionFichiers.lireCSV(fichier);

        if (calendrier.size() == 1 &&
            calendrier.get(0)[0].equals("15") &&
            calendrier.get(0).length == 4) {

            System.out.println("OO testRetirerCalendrierCSVDateInexistante réussi");
        } else {
            System.out.println("XX testRetirerCalendrierCSVDateInexistante échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testRetirerCalendrierCSVUnSeulCreneau() {

        String fichier = "./CSV/calendriers/testCalendrier.csv";

        GestionFichiers.ecrire(
            fichier,
            "Date,estIndispo,Creneau1\n" +
            "15,false,09:00-60\n"
        );

        GestionFichiers.retirerCalendrierCSV("15", "09:00-60", fichier);

        ArrayList<String[]> calendrier = GestionFichiers.lireCSV(fichier);

        if (calendrier.size() == 1 &&
            calendrier.get(0).length == 2 &&
            calendrier.get(0)[0].equals("15") &&
            calendrier.get(0)[1].equals("false")) {

            System.out.println("OO testRetirerCalendrierCSVUnSeulCreneau réussi");
        } else {
            System.out.println("XX testRetirerCalendrierCSVUnSeulCreneau échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testModifierCalendrierCSVUnCreneau() {

        String fichier = "./CSV/calendriers/testCalendrier.csv";

        GestionFichiers.ecrire(
            fichier,
            "Date,heure1-duree(en min)\n" +
            "15\n" +
            "16\n"
        );

        Date date = new Date(15);
        date.ajouterIndispo(LocalTime.of(14, 0), Duration.ofMinutes(60));

        GestionFichiers.modifierCalendrierCSV(date, fichier);

        ArrayList<String[]> contenu = GestionFichiers.lireCSV(fichier);

        if (contenu.get(0).length == 2 &&
            contenu.get(0)[1].equals("14:00-60")) {

            System.out.println("OO testModifierCalendrierCSVUnCreneau réussi");
        }
        else {
            System.out.println("XX testModifierCalendrierCSVUnCreneau échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testModifierCalendrierCSVPlusieursCreneaux() {

        String fichier = "./CSV/calendriers/testCalendrier2.csv";

        GestionFichiers.ecrire(
            fichier,
            "Date,heure1-duree(en min)\n" +
            "20\n"
        );

        Date date = new Date(20);

        date.ajouterIndispo(LocalTime.of(12,0), Duration.ofMinutes(60));
        date.ajouterIndispo(LocalTime.of(15,0), Duration.ofMinutes(90));

        GestionFichiers.modifierCalendrierCSV(date,fichier);

        ArrayList<String[]> contenu = GestionFichiers.lireCSV(fichier);

        if(contenu.get(0).length == 3 &&
           contenu.get(0)[1].equals("12:00-60") &&
           contenu.get(0)[2].equals("15:00-90")) {

            System.out.println("OO testModifierCalendrierCSVPlusieursCreneaux réussi");
        }
        else {
            System.out.println("XX testModifierCalendrierCSVPlusieursCreneaux échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testModifierCalendrierCSVAutresDates() {

        String fichier = "./CSV/calendriers/testCalendrier3.csv";

        GestionFichiers.ecrire(
            fichier,
            "Date,heure1-duree(en min)\n" +
            "10\n" +
            "11\n" +
            "12\n"
        );

        Date date = new Date(11);
        date.ajouterIndispo(LocalTime.of(13,0), Duration.ofMinutes(60));

        GestionFichiers.modifierCalendrierCSV(date,fichier);

        ArrayList<String[]> contenu = GestionFichiers.lireCSV(fichier);

        boolean ok =
            contenu.get(0)[0].equals("10") &&
            contenu.get(1)[0].equals("11") &&
            contenu.get(2)[0].equals("12");

        if(ok)
            System.out.println("OO testModifierCalendrierCSVAutresDates réussi");
        else
            System.out.println("XX testModifierCalendrierCSVAutresDates échoué");

        new File(fichier).delete();
    }
    
    public static void testModifierCalendrierCSVAucuneIndispo() {

        String fichier = "./CSV/calendriers/testCalendrier4.csv";

        GestionFichiers.ecrire(
            fichier,
            "Date,heure1-duree(en min)\n" +
            "8,12:00-60\n"
        );

        Date date = new Date(8);

        GestionFichiers.modifierCalendrierCSV(date,fichier);

        ArrayList<String[]> contenu = GestionFichiers.lireCSV(fichier);

        if(contenu.get(0).length == 1 &&
           contenu.get(0)[0].equals("8")) {

            System.out.println("OO testModifierCalendrierCSVAucuneIndispo réussi");
        }
        else {
            System.out.println("XX testModifierCalendrierCSVAucuneIndispo échoué");
        }

        new File(fichier).delete();
    }
    public static void testModifierCalendrierCSVOrdre() {

        String fichier = "./CSV/calendriers/testCalendrier5.csv";

        GestionFichiers.ecrire(
            fichier,
            "Date,heure1-duree(en min)\n" +
            "25\n"
        );

        Date date = new Date(25);

        date.ajouterIndispo(LocalTime.of(18,0), Duration.ofMinutes(60));
        date.ajouterIndispo(LocalTime.of(12,0), Duration.ofMinutes(60));
        date.ajouterIndispo(LocalTime.of(15,0), Duration.ofMinutes(90));

        GestionFichiers.modifierCalendrierCSV(date,fichier);

        ArrayList<String[]> contenu = GestionFichiers.lireCSV(fichier);

        boolean ok =
            contenu.get(0)[1].equals("12:00-60") &&
            contenu.get(0)[2].equals("15:00-90") &&
            contenu.get(0)[3].equals("18:00-60");

        if(ok)
            System.out.println("OO testModifierCalendrierCSVOrdre réussi");
        else
            System.out.println("XX testModifierCalendrierCSVOrdre échoué");

        new File(fichier).delete();
    }
    
    public static void testModifierActiviteCSV() {

        String fichier = "./CSV/activites/testModifier.csv";

        GestionFichiers.ecrire(
            fichier,
            "ID,Type,SAAQ,Date,Heure,Duree,Montant,Statut,Plaque\n" +
            "1,LT,1234,15,09:00,60,45.0,NC,ABC123\n" +
            "2,LPA,5678,16,13:00,90,75.0,NC,ABC123\n"
        );

        Activite activite = new Activite(
            LocalDate.of(2026, 7, 15),
            LocalTime.of(9, 0),
            Duration.ofMinutes(60),
            "1234",
            new Moniteur(),
            false,
            Activite.TypeActivite.LT,
            Activite.Statut.C
        );

        GestionFichiers.modifierActiviteCSV(activite, fichier);

        ArrayList<String[]> contenu = GestionFichiers.lireCSV(fichier);

        if (contenu.get(0)[7].equals("C")) {
            System.out.println("OO testModifierActiviteCSV réussi");
        }
        else {
            System.out.println("XX testModifierActiviteCSV échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testModifierActiviteCSVIntrouvable() {

        String fichier = "./CSV/activites/testModifier2.csv";

        GestionFichiers.ecrire(
            fichier,
            "ID,Type,SAAQ,Date,Heure,Duree,Montant,Statut,Plaque\n" +
            "1,LT,1234,15,09:00,60,45.0,NC,ABC123\n"
        );

        Activite activite = new Activite(
            LocalDate.of(2026,7,18),
            LocalTime.of(10,0),
            Duration.ofMinutes(60),
            "9999",
            new Moniteur(),
            false,
            Activite.TypeActivite.LT,
            Activite.Statut.C
        );

        GestionFichiers.modifierActiviteCSV(activite,fichier);

        ArrayList<String[]> contenu = GestionFichiers.lireCSV(fichier);

        if(contenu.get(0)[7].equals("NC")) {
            System.out.println("OO testModifierActiviteCSVIntrouvable réussi");
        }
        else {
            System.out.println("XX testModifierActiviteCSVIntrouvable échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testModifierActiviteCSVUneSeule() {

        String fichier = "./CSV/activites/testModifier3.csv";

        GestionFichiers.ecrire(
            fichier,
            "ID,Type,SAAQ,Date,Heure,Duree,Montant,Statut,Plaque\n" +
            "1,LT,1234,15,09:00,60,45.0,NC,ABC123\n" +
            "2,LT,1234,16,09:00,60,45.0,NC,ABC123\n"
        );

        Activite activite = new Activite(
            LocalDate.of(2026,7,15),
            LocalTime.of(9,0),
            Duration.ofMinutes(60),
            "1234",
            new Moniteur(),
            false,
            Activite.TypeActivite.LT,
            Activite.Statut.C
        );

        GestionFichiers.modifierActiviteCSV(activite,fichier);

        ArrayList<String[]> contenu = GestionFichiers.lireCSV(fichier);

        if(contenu.get(0)[7].equals("C")
                && contenu.get(1)[7].equals("NC")) {

            System.out.println("OO testModifierActiviteCSVUneSeule réussi");
        }
        else {
            System.out.println("XX testModifierActiviteCSVUneSeule échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testModifierActiviteCSVID() {

        String fichier = "./CSV/activites/testModifier4.csv";

        GestionFichiers.ecrire(
            fichier,
            "ID,Type,SAAQ,Date,Heure,Duree,Montant,Statut,Plaque\n" +
            "5,LT,1234,15,09:00,60,45.0,NC,ABC123\n" +
            "9,LT,5678,16,10:00,60,45.0,NC,ABC123\n"
        );

        Activite activite = new Activite(
            LocalDate.of(2026,7,15),
            LocalTime.of(9,0),
            Duration.ofMinutes(60),
            "1234",
            new Moniteur(),
            false,
            Activite.TypeActivite.LT,
            Activite.Statut.C
        );

        GestionFichiers.modifierActiviteCSV(activite,fichier);

        ArrayList<String[]> contenu = GestionFichiers.lireCSV(fichier);

        if(contenu.get(0)[0].equals("1")
                && contenu.get(1)[0].equals("2")) {

            System.out.println("OO testModifierActiviteCSVID réussi");
        }
        else {
            System.out.println("XX testModifierActiviteCSVID échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testVoituresCSVFichierExistant() {

        String fichier = "./CSV/voitures/testVoiture.csv";

        GestionFichiers.ecrire(
            fichier,
            "Marque,Plaque,Annee,Prix,KmAchat,Etat,Kms\n" +
            "Toyota,ABC123,2023,25000,0,D,15000\n"
        );

        Voiture voiture = GestionFichiers.voituresCSV(fichier);

        if (voiture != null &&
            voiture.getPlaque().equals("ABC123")) {

            System.out.println("OO testVoituresCSVFichierExistant réussi");
        } else {
            System.out.println("XX testVoituresCSVFichierExistant échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testVoituresCSVNouvelleAnnee() {

        int anneePrec = LocalDate.now().getYear() - 1;

        String ancien =
            "./CSV/voitures/voitures" + anneePrec + ".csv";

        String nouveau =
            "./CSV/voitures/testNouvelle.csv";

        GestionFichiers.ecrire(
            ancien,
            "Marque,Plaque,Annee,Prix,KmAchat,Etat,Kms\n" +
            "Honda,XYZ999,2022,22000,1000,D,20000\n"
        );

        Voiture voiture = GestionFichiers.voituresCSV(nouveau);

        if (voiture != null &&
            voiture.getPlaque().equals("XYZ999")) {

            System.out.println("OO testVoituresCSVNouvelleAnnee réussi");
        } else {
            System.out.println("XX testVoituresCSVNouvelleAnnee échoué");
        }

        new File(ancien).delete();
        new File(nouveau).delete();
    }
    
    public static void testVoituresCSVDerniereVoiture() {

        String fichier = "./CSV/voitures/testDerniere.csv";

        GestionFichiers.ecrire(
            fichier,
            "Marque,Plaque,Annee,Prix,KmAchat,Etat,Kms\n" +
            "Toyota,AAA111,2022,20000,0,V,12000\n" +
            "Honda,BBB222,2023,25000,500,D,18000\n"
        );

        Voiture voiture = GestionFichiers.voituresCSV(fichier);

        if (voiture != null &&
            voiture.getPlaque().equals("BBB222")) {

            System.out.println("OO testVoituresCSVDerniereVoiture réussi");
        } else {
            System.out.println("XX testVoituresCSVDerniereVoiture échoué");
        }

        new File(fichier).delete();
    }
    
    public static void testVoituresCSVFichierVide() {

        String fichier = "./CSV/voitures/testVide.csv";

        GestionFichiers.ecrire(
            fichier,
            "Marque,Plaque,Annee,Prix,KmAchat,Etat,Kms\n"
        );

        try {
            Voiture voiture = GestionFichiers.voituresCSV(fichier);

            if (voiture == null) {
                System.out.println("OO testVoituresCSVFichierVide réussi");
            } else {
                System.out.println("XX testVoituresCSVFichierVide échoué");
            }

        } catch (Exception e) {
            System.out.println("OO testVoituresCSVFichierVide réussi (exception attendue)");
        }

        new File(fichier).delete();
    }
}