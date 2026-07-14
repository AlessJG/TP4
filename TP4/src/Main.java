import java.util.*;
import java.io.*;
import java.time.*;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	ArrayList<Eleve> eleves;

	//Fonction qui permet de lire les entrées des utilisateurs et qui quit l'application dès que 'Q'
	//est entré.
    public static String getInput() {
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("Q")) {
            System.out.println("Au revoir!");
            System.exit(0);
        }

        return input;
    }
    
    public ArrayList<String[]> lireCSV(String fichier) {
    	try {
    		ArrayList<String[]> contenu = new ArrayList<String[]>();
    		FileReader fr = new FileReader(fichier);
    		BufferedReader reader = new BufferedReader(fr);
    		
    		String s;
    		while ((s = reader.readLine()) != null) {
    			contenu.add(s.split(","));
    		}
    		
    		reader.close();
    		return contenu;
    		
    	} catch (IOException ex) {
    		System.out.println("Erreur à l’ouverture du fichier.");
    		return null;
    	}
    }
    
    public void ecrireCSV(String fichier, String texte) {
    	try {
    		FileWriter fw = new FileWriter("fichier");
    		BufferedWriter writer = new BufferedWriter(fw);
    		writer.append(texte);
    		writer.close();
    		
		} catch (IOException ex) {
		System.out.println("Erreur à l’écriture du fichier");
		}
    }
    
    public Eleve inscriptionEleve(Clock horloge) {
    	String entree;
    	String[] questions = {"Veuillez rentrer votre nom :", "Veuillez rentrer votre prénom :",
    						  "Veuillez rentrer votre adresse (au format - sans les crochets []: "
    						  + "[numéro de rue] [nom de la rue] [ville] [province] [code postal]) :", 
    						  "Veuillez rentrer votre numéro de téléphone (au format : 514123456) :",
    						  "Veuillez rentrer votre numéro SAAQ (au format : 123456789) :",
    						  "Veuillez rentrer le mot de passe que vous désirez :"};
    	String[] reponses;
    	while(true) {
    		reponses = new String[6];
    		
    		for (int i = 0; i<questions.length; i++) {
    			System.out.println(questions[i]);
    			entree = getInput();
    			
    			if(i == 2) {
    				if(entree.matches("[a-zA-Z0-9]+") == false) {
    					System.out.println("Format incorrect.");
    					return null;
    				}
    			}
    			
    			if(i == 3 || i == 4) {
    				try {
    					Integer.parseInt(entree);
    				}
    				catch(Exception e){
    					System.out.println("Format incorrect.");
    					return null;
    				}
    			}
    			reponses[i] = entree;
    			
    		}
    		
    		System.out.println("Les informations suivantes sont-elles correctes? (Oui ou Non)");
    		System.out.println("Nom: " + reponses[0] + "Prénom: " + reponses[1] + 
    						   "Adresse: " + reponses[2] + "Numéro de téléphone: " + reponses[3] + 
    						   "Numéro SAAQ: " + reponses[4]);
    		
    		if(getInput().equalsIgnoreCase("Oui")) {
    			break;
    		}
    	}
    	int numTelephone = Integer.parseInt(reponses[3]);
    	int numSAAQ = Integer.parseInt(reponses[4]);
    	
    	Eleve nouvelEleve = new Eleve(LocalDate.now(horloge), reponses[0], reponses[1], reponses[2],
    								  numTelephone, numSAAQ, reponses[5]);
    	
    	String texte = String.join(",", reponses[4], reponses[0], reponses[1], reponses[2], reponses[3], "-");
    	String fichier = "./CSV/eleves" + LocalDate.now(horloge).getYear() + ".CSV";
    	ecrireCSV(fichier, texte);
    	return nouvelEleve;
    }
}
