
public class TestsVoiture {
	public static void testsVoiture() throws Exception {
		System.out.println("\n---Tests pour classe Voiture---\n");
		
		//constructeur
		TestsVoiture.testConstructeurVoiture();
		
		//getPlaque
		TestsVoiture.testGetPlaque();
	}
	
	/**
	 * On teste le constructeur
	 */
	public static void testConstructeurVoiture() {

	    Voiture voiture = new Voiture(
	            "Toyota",
	            2023,
	            "ABC123",
	            25000.0,
	            0,
	            15000,
	            Voiture.Etat.D);

	    if (voiture.getPlaque().equals("ABC123")) {
	        System.out.println("OO testConstructeurVoiture réussi");
	    } else {
	        System.out.println("XX testConstructeurVoiture échoué");
	    }
	}
	
	/**
	 * On teste que l'accesseur getPlaque retourne la bonne valeur
	 */
	public static void testGetPlaque() {

	    Voiture voiture = new Voiture(
	            "Honda",
	            2022,
	            "XYZ999",
	            22000.0,
	            100,
	            12000,
	            Voiture.Etat.R);

	    if (voiture.getPlaque().equals("XYZ999")) {
	        System.out.println("OO testGetPlaque réussi");
	    } else {
	        System.out.println("XX testGetPlaque échoué");
	    }
	}
}
