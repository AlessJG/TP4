
public class Main {
	
	/**
	 * Fonction qui sert à initialiser les tests pour toutes les classes
	 * @throws Exception 
	 */
	public static void tests() throws Exception {
		TestsGestionFichiers.testsGestionFichiers();
		TestsAutoEcole.testsAutoEcole();
		
	}
	
	
	/**
	 * Main, exécute les tests de toutes les classes en commençant la fonction tests()
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		//tests();
		
		AutoEcole autoEcole = new AutoEcole();
		autoEcole.menuPrincipal();
	}
}
