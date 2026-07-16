
public class Main {
	
	/**
	 * Fonction qui sert à initialiser les tests pour toutes les classes
	 */
	public static void tests() {
		System.out.println("---Tests classe AutoEcole---");
		AutoEcole autoEcole = new AutoEcole();		
		autoEcole.testGetInput();
		autoEcole.testLireCSV();
		autoEcole.testEcrireCSV();
		autoEcole.testInscriptionEleve();
		//à compléter
		
	}
	
	
	/**
	 * Main, exécute les tests de toutes les classes en commençant la fonction tests()
	 * @param args
	 */
	public static void main(String args) {
		
		tests();
		
		/*AutoEcole autoEcole = new AutoEcole();
		autoEcole.demarrer();*/
	}
}
