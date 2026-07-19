import java.util.*;
import java.time.*;
import java.io.*;

public class TestsCalendrier {
	public static void testsCalendrier() throws Exception {
		System.out.println("\n---Tests pour classe Calendrier---\n");
		
		//constructeur
		TestsCalendrier.testConstructeurCalendrier();
		
		//nouveauCalendrier
		TestsCalendrier.testNouveauCalendrier();
		
		//getDisponibilites
		TestsCalendrier.testGetDisponibilitesToutes();
		TestsCalendrier.testGetDisponibilitesUneIndispo();
		
		//afficherEntete
		TestsCalendrier.testAfficherEntete();
		
		//afficherCalendrierMois
		TestsCalendrier.testAfficherCalendrierMois();
		
		//afficherCalendrierSemaine
		TestsCalendrier.testAfficherCalendrierSemaine();
		
		//getDates
		TestsCalendrier.testGetDates();
		
		//ajouterIndispo
		TestsCalendrier.testCalendrierPlusieursIndispo();
		TestsCalendrier.testGetDisponibilitesUneIndispo();
	}
	
	public static void testConstructeurCalendrier() {

	    Calendrier c = new Calendrier();

	    if (c.getDates() != null && !c.getDates().isEmpty()) {
	        System.out.println("OO testConstructeurCalendrier réussi");
	    }
	    else {
	        System.out.println("XX testConstructeurCalendrier échoué");
	    }
	}
	
	public static void testNouveauCalendrier() {

	    String fichier = "./CSV/calendriers/testCalendrier.csv";

	    new File(fichier).delete();

	    Calendrier c = new Calendrier();
	    c.nouveauCalendrier(fichier);

	    ArrayList<String[]> contenu = GestionFichiers.lireCSV(fichier);

	    if (contenu != null && !contenu.isEmpty()) {
	        System.out.println("OO testNouveauCalendrier réussi");
	    }
	    else {
	        System.out.println("XX testNouveauCalendrier échoué");
	    }

	    new File(fichier).delete();
	}
	
	public static void testGetDisponibilitesToutes() {

	    Calendrier c = new Calendrier();

	    if (c.getDisponibilites().size() == c.getDates().size()) {
	        System.out.println("OO testGetDisponibilitesToutes réussi");
	    }
	    else {
	        System.out.println("XX testGetDisponibilitesToutes échoué");
	    }
	}
	
	public static void testGetDisponibilitesUneIndispo() {

	    Calendrier c = new Calendrier();

	    Date d = c.getDates().get(0);

	    d.ajouterIndispo(LocalTime.of(12,0), Duration.ofHours(8));

	    if (c.getDisponibilites().size() == c.getDates().size()-1) {
	        System.out.println("OO testGetDisponibilitesUneIndispo réussi");
	    }
	    else {
	        System.out.println("XX testGetDisponibilitesUneIndispo échoué");
	    }
	}
	
	public static void testAfficherEntete() {

	    Calendrier c = new Calendrier();

	    int espaces = c.afficherEntete(c.getDates().get(0));

	    if (espaces >= 0 && espaces <= 6) {
	        System.out.println("OO testAfficherEntete réussi");
	    }
	    else {
	        System.out.println("XX testAfficherEntete échoué");
	    }
	}
	
	public static void testAfficherCalendrierMois() {

	    Calendrier c = new Calendrier();

	    Date[][] semaines = c.afficherCalendrierMois();

	    if (semaines.length == 6 &&
	        semaines[0].length == 7) {

	        System.out.println("OO testAfficherCalendrierMois réussi");
	    }
	    else {
	        System.out.println("XX testAfficherCalendrierMois échoué");
	    }
	}
	
	public static void testAfficherCalendrierSemaine() {

	    Calendrier c = new Calendrier();

	    Date[][] semaines = c.afficherCalendrierMois();

	    try {

	        c.afficherCalendrierSemaine(semaines[0]);

	        System.out.println("OO testAfficherCalendrierSemaine réussi");

	    } catch(Exception e) {

	        System.out.println("XX testAfficherCalendrierSemaine échoué");
	    }
	}
	
	public static void testGetDates() {

	    Calendrier c = new Calendrier();

	    if (c.getDates() != null &&
	        c.getDates().size() > 0) {

	        System.out.println("OO testGetDates réussi");
	    }
	    else {
	        System.out.println("XX testGetDates échoué");
	    }
	}
	
	public static void testCalendrierJourComplet() {

	    Calendrier c = new Calendrier();

	    Date d = c.getDates().get(0);

	    d.ajouterIndispo(LocalTime.of(12,0), Duration.ofHours(8));

	    Date[][] semaines = c.afficherCalendrierMois();

	    if (semaines != null)
	        System.out.println("OO testCalendrierJourComplet réussi");
	    else
	        System.out.println("XX testCalendrierJourComplet échoué");
	}
	
	public static void testCalendrierPlusieursIndispo() {

	    Calendrier c = new Calendrier();

	    Date d = c.getDates().get(0);

	    d.ajouterIndispo(LocalTime.of(12,0), Duration.ofHours(1));
	    d.ajouterIndispo(LocalTime.of(15,0), Duration.ofMinutes(90));

	    if (!d.getCreneauDispo().isEmpty())
	        System.out.println("OO testCalendrierPlusieursIndispo réussi");
	    else
	        System.out.println("XX testCalendrierPlusieursIndispo échoué");
	}
}
