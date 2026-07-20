import java.util.*;
import java.time.*;

public class TestsDate {
	public static void testsDate() {
		System.out.println("\n---Tests pour classe Date---\n");
		
		//constructeur
		TestsDate.testDateConstructeur();
		
		//ajouterIndispo
		TestsDate.testAjouterIndispo();
		TestsDate.testAjouterIndispoChevauchement();
		
		//enleverIndispo
		TestsDate.testEnleverIndispo();
		
		//checkIndispo
		TestsDate.testCheckIndispoDisponible();
		TestsDate.testCheckIndispoIndisponible();
		
		//getJour
		TestsDate.testGetJour();
		
		//getCreneauIndispo
		TestsDate.testGetCreneauIndispo();
		
		//getCreneauDispo
		TestsDate.testGetCreneauDispoDeuxReservations();
		TestsDate.testGetCreneauDispoUneReservation();
		TestsDate.testGetCreneauDispoVide();
		
		//toString
		TestsDate.testToStringDate();
	}
	
	/**
	 * On teste le constructeur
	 */
	public static void testDateConstructeur() {

	    Date d = new Date(15);

	    if (d.getJour() == 15 &&
	        d.getCreneauIndispo().isEmpty()) {

	        System.out.println("OO testDateConstructeur réussi");
	    } else {
	        System.out.println("XX testDateConstructeur échoué");
	    }
	}
	
	/**
	 * On teste la fonction ajouterIndispo
	 * Cas: on ajoute une indisponibilite a une date complètement disponible de base
	 */
	public static void testAjouterIndispo() {

	    Date d = new Date(10);

	    boolean resultat =
	        d.ajouterIndispo(LocalTime.of(13, 0), Duration.ofMinutes(60));

	    if (resultat &&
	        d.getCreneauIndispo().size() == 1) {

	        System.out.println("OO testAjouterIndispo réussi");
	    } else {
	        System.out.println("XX testAjouterIndispo échoué");
	    }
	}
	
	/**
	 * On teste la fonction ajouterIndispo
	 * Cas: on ajoute plusieurs indisponibilités qui se chevauche à une même date
	 */
	public static void testAjouterIndispoChevauchement() {

	    Date d = new Date(10);

	    d.ajouterIndispo(LocalTime.of(13,0), Duration.ofMinutes(60));

	    boolean resultat =
	        d.ajouterIndispo(LocalTime.of(13,30), Duration.ofMinutes(60));

	    if (!resultat &&
	        d.getCreneauIndispo().size() == 1) {

	        System.out.println("OO testAjouterIndispoChevauchement réussi");
	    } else {
	        System.out.println("XX testAjouterIndispoChevauchement échoué");
	    }
	}
	
	/**
	 * On teste la fonction enleverIndispo
	 */
	public static void testEnleverIndispo() {

	    Date d = new Date(10);

	    d.ajouterIndispo(LocalTime.of(13,0), Duration.ofMinutes(60));
	    d.enleverIndispo(LocalTime.of(13,0));

	    if (d.getCreneauIndispo().isEmpty()) {

	        System.out.println("OO testEnleverIndispo réussi");
	    } else {
	        System.out.println("XX testEnleverIndispo échoué");
	    }
	}
	
	/**
	 * On teste la fonction checkInsipo
	 * Cas: la date est disponible
	 */
	public static void testCheckIndispoDisponible() {

	    Date d = new Date(10);

	    if (!d.checkIndispo()) {

	        System.out.println("OO testCheckIndispoDisponible réussi");
	    } else {
	        System.out.println("XX testCheckIndispoDisponible échoué");
	    }
	}
	
	/**
	 * On teste la fonction checkInsipo
	 * Cas: la date est indisponible
	 */
	public static void testCheckIndispoIndisponible() {

	    Date d = new Date(10);

	    d.ajouterIndispo(
	            LocalTime.of(12,0),
	            Duration.ofHours(8));

	    if (d.checkIndispo()) {

	        System.out.println("OO testCheckIndispoIndisponible réussi");
	    } else {
	        System.out.println("XX testCheckIndispoIndisponible échoué");
	    }
	}
	
	/**
	 * On teste si l'accesseur getJour retourne la bonne valeur
	 */
	public static void testGetJour() {

	    Date d = new Date(27);

	    if (d.getJour() == 27) {

	        System.out.println("OO testGetJour réussi");
	    } else {
	        System.out.println("XX testGetJour échoué");
	    }
	}
	
	/**
	 * On teste si l'accesseur getCreneauIndispo retourne les bonnes valeurs
	 */
	public static void testGetCreneauIndispo() {

	    Date d = new Date(5);

	    d.ajouterIndispo(LocalTime.of(14,0), Duration.ofMinutes(90));

	    TreeMap<LocalTime, Duration> map = d.getCreneauIndispo();

	    if (map.containsKey(LocalTime.of(14,0)) &&
	        map.get(LocalTime.of(14,0)).toMinutes() == 90) {

	        System.out.println("OO testGetCreneauIndispo réussi");
	    } else {
	        System.out.println("XX testGetCreneauIndispo échoué");
	    }
	}
	
	/**
	 * On teste si l'accesseur getCreneauDispo retourne les bonnes valeurs
	 * Cas: la date est complètement disponible
	 */
	public static void testGetCreneauDispoVide() {

	    Date d = new Date(10);

	    TreeMap<LocalTime, Duration> dispo = d.getCreneauDispo();

	    boolean ok =
	        dispo.size() == 1 &&
	        dispo.containsKey(LocalTime.of(12,0)) &&
	        dispo.get(LocalTime.of(12,0)).toHours() == 8;

	    if (ok) {
	        System.out.println("OO testGetCreneauDispoVide réussi");
	    } else {
	        System.out.println("XX testGetCreneauDispoVide échoué");
	    }
	}
	
	/**
	 * On teste si l'accesseur getCreneauIndispo retourne les bonnes valeurs
	 * Cas: la date contient une indisponibilité
	 */
	public static void testGetCreneauDispoUneReservation() {

	    Date d = new Date(10);

	    d.ajouterIndispo(LocalTime.of(13,0), Duration.ofHours(1));

	    TreeMap<LocalTime, Duration> dispo = d.getCreneauDispo();

	    boolean ok =
	        dispo.size() == 1 &&
	        dispo.containsKey(LocalTime.of(14,0)) &&
	        dispo.get(LocalTime.of(14,0)).toHours() == 6;

	    if (ok) {
	        System.out.println("OO testGetCreneauDispoUneReservation réussi");
	    } else {
	        System.out.println("XX testGetCreneauDispoUneReservation échoué");
	    }
	}
	
	/**
	 * On teste si l'accesseur getCreneauIndispo retourne les bonnes valeurs
	 * Cas: la date contient deux indisponibilités
	 */
	public static void testGetCreneauDispoDeuxReservations() {

	    Date d = new Date(10);

	    d.ajouterIndispo(LocalTime.of(13,0), Duration.ofHours(1));
	    d.ajouterIndispo(LocalTime.of(16,0), Duration.ofHours(1));

	    TreeMap<LocalTime, Duration> dispo = d.getCreneauDispo();

	    boolean ok =
	        dispo.size() == 2 &&
	        dispo.containsKey(LocalTime.of(14,0)) &&
	        dispo.containsKey(LocalTime.of(17,0));

	    if (ok) {
	        System.out.println("OO testGetCreneauDispoDeuxReservations réussi");
	    } else {
	        System.out.println("XX testGetCreneauDispoDeuxReservations échoué");
	    }
	}
	
	/**
	 * On teste la fonction toString
	 */
	public static void testToStringDate() {

	    Date d = new Date(10);

	    d.ajouterIndispo(LocalTime.of(15,0), Duration.ofHours(1));

	    String s = d.toString();

	    if (s.contains("12:00") &&
	        s.contains("15:00") &&
	        s.contains("16:00")) {

	        System.out.println("OO testToStringDate réussi");
	    } else {
	        System.out.println("XX testToStringDate échoué");
	    }
	}
}
