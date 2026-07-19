import java.time.*;

public class TestsPersonne {
	public static void testsPersonne() throws Exception {
		System.out.println("\n---Tests pour classe Personne---\n");
		
		//constructeur
		TestsPersonne.testPersonneConstructeur();
		
		//getNom
		TestsPersonne.testGetNom();
		
		//getPrenom
		TestsPersonne.testGetPrenom();
		
		//getMotDePasse
		TestsPersonne.testGetMotDePasse();
		
	}
	
	public static void testPersonneConstructeur() {

	    Eleve e = new Eleve(
	            LocalDate.now(),
	            "Doe",
	            "John",
	            "123 Rue",
	            "5141234567",
	            "123456789",
	            "MotDePasse");

	    if (e.getNom().equals("Doe") &&
	        e.getPrenom().equals("John")) {

	        System.out.println("OO testPersonneConstructeur réussi");
	    }
	    else {
	        System.out.println("XX testPersonneConstructeur échoué");
	    }
	}
	
	public static void testGetNom() {

	    Eleve e = new Eleve(
	            LocalDate.now(),
	            "Smith",
	            "John",
	            "Adresse",
	            "5141111111",
	            "123",
	            "mdp");

	    if (e.getNom().equals("Smith")) {
	        System.out.println("OO testGetNom réussi");
	    }
	    else {
	        System.out.println("XX testGetNom échoué");
	    }
	}
	
	public static void testGetPrenom() {

	    Eleve e = new Eleve(
	            LocalDate.now(),
	            "Smith",
	            "Alice",
	            "Adresse",
	            "5141111111",
	            "123",
	            "mdp");

	    if (e.getPrenom().equals("Alice")) {
	        System.out.println("OO testGetPrenom réussi");
	    }
	    else {
	        System.out.println("XX testGetPrenom échoué");
	    }
	}
	
	public static void testGetMotDePasse() {

	    Eleve e = new Eleve(
	            LocalDate.now(),
	            "Smith",
	            "Alice",
	            "Adresse",
	            "5141111111",
	            "123",
	            "MonMotDePasse");

	    if (e.getMotDePasse().equals("MonMotDePasse")) {
	        System.out.println("OO testGetMotDePasse réussi");
	    }
	    else {
	        System.out.println("XX testGetMotDePasse échoué");
	    }
	}
}
