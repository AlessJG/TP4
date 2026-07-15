
public class Main {
	
	public static void tests() {
		System.out.println("---Tests classe AutoEcole---");
		AutoEcole autoEcole = new AutoEcole();		
		autoEcole.testGetInput();
		autoEcole.testLireCSV();
		autoEcole.testEcrireCSV();
		autoEcole.testInscriptionEleve();
		
		
	}
	
	
	
	public static void main(String args) {
		
		tests();
		
		/*AutoEcole autoEcole = new AutoEcole();
		autoEcole.demarrer();*/
	}
}
