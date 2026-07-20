import java.util.*;

public class TestsAutoEcole {
	public static void testsAutoEcole() throws Exception {
		System.out.println("\n---Tests pour classe AutoEcole---\n");
		
		//getInput
		TestsAutoEcole.testGetInputNormal();
		TestsAutoEcole.testGetInputTrim();
		
		//inscriptionEleve
		TestsAutoEcole.testInscriptionEleveConfirmationNon();
		TestsAutoEcole.testInscriptionEleveSAAQInvalide();
		TestsAutoEcole.testInscriptionEleveTelephoneInvalide();
		TestsAutoEcole.testInscriptionEleveValide();
		
		//authentifierEleve
		TestsAutoEcole.testAuthentifierEleveValide();
		TestsAutoEcole.testAuthentifierEleveSAAQInvalide();
		TestsAutoEcole.testAuthentifierEleveMotDePasseInvalide();
		TestsAutoEcole.testAuthentifierEleveDeuxIdentifiantsInvalides();
		TestsAutoEcole.testAuthentifierEleveChampsVides();
	}
	
	/**
	 * On teste que l'accesseur et le gestionnaire des entrées
	 * utilisateur retourne la bonne valeur
	 */
	public static void testGetInputNormal() {

	    AutoEcole.scanner = new Scanner("Bonjour\n");

	    String resultat = AutoEcole.getInput();

	    if (resultat.equals("Bonjour")) {
	        System.out.println("OO testGetInputNormal réussi");
	    } else {
	        System.out.println("XX testGetInputNormal échoué");
	    }
	}
	
	/**
	 * On teste que l'accesseur et le gestionnaire des entrées
	 * utilisateur retourne bien un texte sans espaces 
	 * inutiles avant et après celui-ci (trimmed)
	 */
	public static void testGetInputTrim() {

	    AutoEcole.scanner = new Scanner("   Bonjour   \n");

	    String resultat = AutoEcole.getInput();

	    if (resultat.equals("Bonjour")) {
	        System.out.println("OO testGetInputTrim réussi");
	    } else {
	        System.out.println("XX testGetInputTrim échoué");
	    }
	}
	

	/**
	 * On teste la fonction inscriptionEleve avec des informations valides
	 */
	public static void testInscriptionEleveValide() {

	    AutoEcole.scanner = new Scanner(
	        "Dupont\n" +
	        "Jean\n" +
	        "123 Rue Principale Montreal QC H1H1H1\n" +
	        "5141234567\n" +
	        "123456789\n" +
	        "motdepasse\n" +
	        "Oui\n"
	    );

	    AutoEcole autoEcole = new AutoEcole();

	    Eleve eleve = autoEcole.inscriptionEleve();

	    if (eleve != null) {
	        System.out.println("OO testInscriptionEleveValide réussi");
	    } else {
	        System.out.println("XX testInscriptionEleveValide échoué");
	    }
	}
	
	/**
	 * On teste la fonction inscriptionEleve avec un numéro de téléphone invalide
	 * (contient des lettres)
	 */
	public static void testInscriptionEleveTelephoneInvalide() {

	    AutoEcole.scanner = new Scanner(
	        "Dupont\n" +
	        "Jean\n" +
	        "123 Rue Principale Montreal QC H1H1H1\n" +
	        "abc123\n" +
	        "123456789\n" +
	        "motdepasse\n"
	    );

	    AutoEcole autoEcole = new AutoEcole();

	    Eleve eleve = autoEcole.inscriptionEleve();
	    

	    if (eleve == null) {
	        System.out.println("OO testInscriptionEleveTelephoneInvalide réussi");
	    } else {
	        System.out.println("XX testInscriptionEleveTelephoneInvalide échoué");
	    }
	}
	
	/**
	 * On teste la fonction inscriptionEleve avec un numéro SAAQ invalide
	 */
	public static void testInscriptionEleveSAAQInvalide() {

	    AutoEcole.scanner = new Scanner(
	        "Dupont\n" +
	        "Jean\n" +
	        "123 Rue Principale Montreal QC H1H1H1\n" +
	        "5141234567\n" +
	        "ABC123\n" +
	        "motdepasse\n"
	    );

	    AutoEcole autoEcole = new AutoEcole();

	    Eleve eleve = autoEcole.inscriptionEleve();

	    if (eleve == null) {
	        System.out.println("OO testInscriptionEleveSAAQInvalide réussi");
	    } else {
	        System.out.println("XX testInscriptionEleveSAAQInvalide échoué");
	    }
	}
	
	/**
	 * On teste la fonction inscriptionEleve avec un numéro de téléphone invalide
	 * (7 chiffres au lieu de 10)
	 */
	public static void testInscriptionEleveTelephoneLongueur() {

	    AutoEcole.scanner = new Scanner(
	        "Dupont\n" +
	        "Jean\n" +
	        "123 Rue Principale Montreal QC H1H1H1\n" +
	        "5141234\n" +
	        "123456789\n" +
	        "motdepasse\n"
	    );

	    AutoEcole autoEcole = new AutoEcole();

	    Eleve eleve = autoEcole.inscriptionEleve();

	    if (eleve == null) {
	        System.out.println("OO testInscriptionEleveTelephoneLongueur réussi");
	    } else {
	        System.out.println("XX testInscriptionEleveTelephoneLongueur échoué");
	    }
	}
	
	/**
	 * On teste la fonction inscriptionEleve si l'élève répond que les
	 * informations rentrées étaient incorrectes (devrait ne pas enregistrer ces
	 * données et repartir la boucle de question)
	 */
	public static void testInscriptionEleveConfirmationNon() {

	    AutoEcole.scanner = new Scanner(
	        // Première tentative
	        "Dupont\n" +
	        "Jean\n" +
	        "123 Rue Principale Montreal QC H1H1H1\n" +
	        "5141111111\n" +
	        "111111111\n" +
	        "pass1\n" +
	        "Non\n" +

	        // Deuxième tentative
	        "Martin\n" +
	        "Paul\n" +
	        "456 Rue Sherbrooke Montreal QC H2H2H2\n" +
	        "5142222222\n" +
	        "222222222\n" +
	        "pass2\n" +
	        "Oui\n"
	    );

	    AutoEcole autoEcole = new AutoEcole();

	    Eleve eleve = autoEcole.inscriptionEleve();

	    if (eleve != null) {
	        System.out.println("OO testInscriptionEleveConfirmationNon réussi");
	    } else {
	        System.out.println("XX testInscriptionEleveConfirmationNon échoué");
	    }
	}
	
	/**
	 * On teste la fonction authentifierEleve avec des informations valides
	 */
	public static void testAuthentifierEleveValide() {

	    AutoEcole autoEcole = new AutoEcole();

	    Eleve eleve = autoEcole.authentifierEleve(
	        "123456789",
	        "motdepasse"
	    );

	    if (eleve != null) {
	        System.out.println("OO testAuthentifierEleveValide réussi");
	    } else {
	        System.out.println("XX testAuthentifierEleveValide échoué");
	    }
	}
	
	/**
	 * On teste la fonction authentifierEleve avec un mot de passe invalide
	 */
	public static void testAuthentifierEleveMotDePasseInvalide() {

	    AutoEcole autoEcole = new AutoEcole();

	    Eleve eleve = autoEcole.authentifierEleve(
	        "123456789",
	        "mauvais"
	    );

	    if (eleve == null) {
	        System.out.println("OO testAuthentifierEleveMotDePasseInvalide réussi");
	    } else {
	        System.out.println("XX testAuthentifierEleveMotDePasseInvalide échoué");
	    }
	}
	
	/**
	 * On teste la fonction authentifierEleve avec un numéro SAAQ invalide
	 */
	public static void testAuthentifierEleveSAAQInvalide() {

	    AutoEcole autoEcole = new AutoEcole();

	    Eleve eleve = autoEcole.authentifierEleve(
	        "000000000",
	        "motdepasse"
	    );

	    if (eleve == null) {
	        System.out.println("OO testAuthentifierEleveSAAQInvalide réussi");
	    } else {
	        System.out.println("XX testAuthentifierEleveSAAQInvalide échoué");
	    }
	}
	
	/**
	 * On teste la fonction authentifierEleve avec un numéro SAAQ et un mot de passe invalides
	 */
	public static void testAuthentifierEleveDeuxIdentifiantsInvalides() {

	    AutoEcole autoEcole = new AutoEcole();

	    Eleve eleve = autoEcole.authentifierEleve(
	        "111111111",
	        "abcdef"
	    );

	    if (eleve == null) {
	        System.out.println("OO testAuthentifierEleveDeuxIdentifiantsInvalides réussi");
	    } else {
	        System.out.println("XX testAuthentifierEleveDeuxIdentifiantsInvalides échoué");
	    }
	}
	
	/**
	 * On teste la fonction authentifierEleve si rien n'est entré
	 */
	public static void testAuthentifierEleveChampsVides() {

	    AutoEcole autoEcole = new AutoEcole();

	    Eleve eleve = autoEcole.authentifierEleve(
	        "",
	        ""
	    );

	    if (eleve == null) {
	        System.out.println("OO testAuthentifierEleveChampsVides réussi");
	    } else {
	        System.out.println("XX testAuthentifierEleveChampsVides échoué");
	    }
	}
	

}
