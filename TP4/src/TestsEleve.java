import java.time.*;

public class TestsEleve {
	public static void testsEleve() throws Exception {
		System.out.println("\n---Tests pour classe Eleve---\n");
		
		//constructeur
		TestsEleve.testEleveConstructeur();
		
		//getLecon
		TestsEleve.testGetLecon();
		
		//setLecon
		TestsEleve.testSetLecon();
		
		//getTempsLecon
		TestsEleve.testTempsLecon60();
		TestsEleve.testTempsLecon90();
		
		//getTypeActivite
		TestsEleve.testTypeActiviteLT();
		TestsEleve.testTypeActiviteEP();
		TestsEleve.testTypeActiviteEPL();
		
		//setActivite
		TestsEleve.testSetActivite();
		
		//setActivitePrevue
		TestsEleve.testSetActivitePrevue();
		
		//getNumSAAQ
		TestsEleve.testGetNumSAAQ();
		
		//impaye
		TestsEleve.testImpayeSansFacture();
	}
	
	/**
	 * On teste le constructeur
	 */
	public static void testEleveConstructeur() {

	    Eleve e = new Eleve(
	            LocalDate.of(2026,7,19),
	            "Doe",
	            "John",
	            "123 Rue",
	            "5141234567",
	            "123456789",
	            "MotDePasse");

	    if (e.getLecon() == Eleve.Lecon.LT1 &&
	        e.getNumSAAQ().equals("123456789") &&
	        !e.getActivitePrevue() &&
	        e.getActivite() == null &&
	        e.getFacture() == null) {

	        System.out.println("OO testEleveConstructeur réussi");
	    }
	    else {
	        System.out.println("XX testEleveConstructeur échoué");
	    }
	}
	
	/**
	 * On teste si l'accesseur getLecon retourne la bonne valeur
	 */
	public static void testGetLecon() {

	    Eleve e = new Eleve(
	            LocalDate.now(),
	            "Doe","John","Adresse",
	            "1111111111","123","mdp");

	    if(e.getLecon() == Eleve.Lecon.LT1) {
	        System.out.println("OO testGetLecon réussi");
	    }
	    else {
	        System.out.println("XX testGetLecon échoué");
	    }
	}
	
	/**
	 * On teste si le mutateur setLecon modifie la variable à la bonne valeur
	 */
	public static void testSetLecon() {

	    Eleve e = new Eleve(
	            LocalDate.now(),
	            "Doe","John","Adresse",
	            "1111111111","123","mdp");

	    e.setLecon(Eleve.Lecon.LPA2);

	    if(e.getLecon() == Eleve.Lecon.LPA2) {
	        System.out.println("OO testSetLecon réussi");
	    }
	    else {
	        System.out.println("XX testSetLecon échoué");
	    }
	}
	
	/**
	 * On teste si l'accesseur getTempsLecon retourne la bonne valeur
	 * Cas: une lecon théorique dure 60 minutes
	 */
	public static void testTempsLecon60() {

	    Eleve e = new Eleve(
	            LocalDate.now(),
	            "Doe","John","Adresse",
	            "1111111111","123","mdp");

	    e.setLecon(Eleve.Lecon.LT8);

	    if(e.getTempsLecon() == 60) {
	        System.out.println("OO testTempsLecon60 réussi");
	    }
	    else {
	        System.out.println("XX testTempsLecon60 échoué");
	    }
	}
	
	/**
	 * On teste si l'accesseur getTempsLecon retourne la bonne valeur
	 * Cas: une lecon pratique dure 90 minutes
	 */
	public static void testTempsLecon90() {

	    Eleve e = new Eleve(
	            LocalDate.now(),
	            "Doe","John","Adresse",
	            "1111111111","123","mdp");

	    e.setLecon(Eleve.Lecon.LPA4);

	    if(e.getTempsLecon() == 90) {
	        System.out.println("OO testTempsLecon90 réussi");
	    }
	    else {
	        System.out.println("XX testTempsLecon90 échoué");
	    }
	}
	
	/**
	 * On teste si l'accesseur getTypeActivite retourne la bonne valeur
	 * Cas: le type est LT
	 */
	public static void testTypeActiviteLT() {

	    Eleve e = new Eleve(
	            LocalDate.now(),
	            "Doe","John","Adresse",
	            "1111111111","123","mdp");

	    e.setLecon(Eleve.Lecon.LT5);

	    if(e.getTypeActivite(false) ==
	            Activite.TypeActivite.LT) {

	        System.out.println("OO testTypeActiviteLT réussi");
	    }
	    else {
	        System.out.println("XX testTypeActiviteLT échoué");
	    }
	}
	
	/**
	 * On teste si l'accesseur getTypeActivite retourne la bonne valeur
	 * Cas: le type est EP
	 */
	public static void testTypeActiviteEP() {

	    Eleve e = new Eleve(
	            LocalDate.now(),
	            "Doe","John","Adresse",
	            "1111111111","123","mdp");

	    e.setLecon(Eleve.Lecon.EP);

	    if(e.getTypeActivite(false) ==
	            Activite.TypeActivite.EP) {

	        System.out.println("OO testTypeActiviteEP réussi");
	    }
	    else {
	        System.out.println("XX testTypeActiviteEP échoué");
	    }
	}
	
	/**
	 * On teste si l'accesseur getTypeActivite retourne la bonne valeur
	 * Cas: le type est EPL
	 */
	public static void testTypeActiviteEPL() {

	    Eleve e = new Eleve(
	            LocalDate.now(),
	            "Doe","John","Adresse",
	            "1111111111","123","mdp");

	    e.setLecon(Eleve.Lecon.EP);

	    if(e.getTypeActivite(true) ==
	            Activite.TypeActivite.EPL) {

	        System.out.println("OO testTypeActiviteEPL réussi");
	    }
	    else {
	        System.out.println("XX testTypeActiviteEPL échoué");
	    }
	}
	
	/**
	 * On teste si le mutateur setActivite modifie la variable à la bonne valeur
	 */
	public static void testSetActivite() {

	    Eleve e = new Eleve(
	            LocalDate.now(),
	            "Doe","John","Adresse",
	            "1111111111","123","mdp");

	    Activite a =
	            new Activite(
	                    LocalDate.now(),
	                    LocalTime.of(14,0),
	                    Duration.ofHours(1),
	                    "123",
	                    new Moniteur(),
	                    false,
	                    Activite.TypeActivite.LT,
	                    Activite.Statut.NC);

	    e.setActivite(a);

	    if(e.getActivite() == a) {
	        System.out.println("OO testSetActivite réussi");
	    }
	    else {
	        System.out.println("XX testSetActivite échoué");
	    }
	}
	
	/**
	 * On teste si le mutateur setActivitePrevue modifie la variable à la bonne valeur
	 */
	public static void testSetActivitePrevue() {

	    Eleve e = new Eleve(
	            LocalDate.now(),
	            "Doe","John","Adresse",
	            "1111111111","123","mdp");

	    e.setActivitePrevue(true);

	    if(e.getActivitePrevue()) {
	        System.out.println("OO testSetActivitePrevue réussi");
	    }
	    else {
	        System.out.println("XX testSetActivitePrevue échoué");
	    }
	}
	
	/**
	 * On teste si l'accesseur getNumSAAQ retourne la bonne valeur
	 */
	public static void testGetNumSAAQ() {

	    Eleve e = new Eleve(
	            LocalDate.now(),
	            "Doe","John","Adresse",
	            "1111111111",
	            "987654321",
	            "mdp");

	    if(e.getNumSAAQ().equals("987654321")) {

	        System.out.println("OO testGetNumSAAQ réussi");
	    }
	    else {
	        System.out.println("XX testGetNumSAAQ échoué");
	    }
	}
	
	/**
	 * On teste si l
	 */
	public static void testImpayeSansFacture() {

	    Eleve e = new Eleve(
	            LocalDate.now(),
	            "Doe","John","Adresse",
	            "1111111111","123","mdp");

	    if(!e.impaye()) {

	        System.out.println("OO testImpayeSansFacture réussi");
	    }
	    else {
	        System.out.println("XX testImpayeSansFacture échoué");
	    }
	}
}
