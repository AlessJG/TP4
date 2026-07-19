
public class TestsMoniteur {
	public static void testsMoniteur() throws Exception {
		System.out.println("\n---Tests pour classe Moniteur---\n");
		
		//constructeur
		TestsMoniteur.testConstructeurMoniteur();
		
		//getNumPermis
		TestsMoniteur.testGetNumPermis();
		
		//getNom (super)
		TestsMoniteur.testGetNomMoniteur();
		
		//getPrenom (super)
		TestsMoniteur.testGetPrenomMoniteur();
	}
	
	public static void testConstructeurMoniteur() {

	    Moniteur m = new Moniteur();

	    if (m.getNom().equals("Toutlemonde") &&
	        m.getPrenom().equals("John") &&
	        m.getNumPermis().equals("12345678")) {

	        System.out.println("OO testConstructeurMoniteur réussi");
	    } else {
	        System.out.println("XX testConstructeurMoniteur échoué");
	    }
	}
	
	public static void testGetNumPermis() {

	    Moniteur m = new Moniteur();

	    if (m.getNumPermis().equals("12345678")) {
	        System.out.println("OO testGetNumPermis réussi");
	    } else {
	        System.out.println("XX testGetNumPermis échoué");
	    }
	}
	
	public static void testGetNomMoniteur() {

	    Moniteur m = new Moniteur();

	    if (m.getNom().equals("Toutlemonde")) {
	        System.out.println("OO testGetNomMoniteur réussi");
	    } else {
	        System.out.println("XX testGetNomMoniteur échoué");
	    }
	}
	
	public static void testGetPrenomMoniteur() {

	    Moniteur m = new Moniteur();

	    if (m.getPrenom().equals("John")) {
	        System.out.println("OO testGetPrenomMoniteur réussi");
	    } else {
	        System.out.println("XX testGetPrenomMoniteur échoué");
	    }
	}
}
