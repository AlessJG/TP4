import java.util.Scanner;

public class Main {
	static Scanner scanner = new Scanner(System.in);

	//Fonction qui permet de lire les entrées des utilisateurs et qui quit l'application dès que 'Q'
	//est entré.
    public static String getInput() {
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("Q")) {
            System.out.println("Goodbye!");
            System.exit(0);
        }

        return input;
    }
    
    public void 
}
