import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;


//Cette classe sert à générer les différents rapports de gestion de l'auto-école.

 //Elle lit les informations contenues dans les fichiers CSV,

 //filtre les données selon une période donnée (date de début et date de fin),

 //crée un texte contenant les informations demandées et sauvegarde le rapport dans un fichier texte à l'emplacement choisi par l'utilisateur

public class Rapport {

    private static final DateTimeFormatter FORMAT_DATE =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");


   //Rapport des eleves
    public static void rapportEleves(String fichierCSV,
                                     LocalDate dateDebut,
                                     LocalDate dateFin,
                                     String dossier) {

        ArrayList<String[]> eleves =
                GestionFichiers.lireCSV(fichierCSV);

        String texte = "===== RAPPORT DES ÉLÈVES =====\n\n";


        for (String[] ligne : eleves) {

            LocalDate dateInscription =
                    LocalDate.parse(ligne[6], FORMAT_DATE);


            if (!dateInscription.isBefore(dateDebut)
                    && !dateInscription.isAfter(dateFin)) {


                texte += "Numéro SAAQ : " + ligne[0] + "\n";
                texte += "Nom : " + ligne[2] + "\n";
                texte += "Prénom : " + ligne[3] + "\n";
                texte += "Adresse : " + ligne[4] + "\n";
                texte += "Téléphone : " + ligne[5] + "\n";
                texte += "Date inscription : " + ligne[6] + "\n";
                texte += "Date fin : " + ligne[7] + "\n";
                texte += "-----------------------------\n";
            }
        }


        String fichier = dossier + File.separator
                + "rapportEleves"
                + dateDebut + "-"
                + dateFin
                + ".txt";


        GestionFichiers.ecrire(fichier, texte);
    }


    // Rapport des revenus
    public static void rapportRevenus(String fichierCSV,
                                      LocalDate dateDebut,
                                      LocalDate dateFin,
                                      String dossier) {


        ArrayList<String[]> paiements =
                GestionFichiers.lireCSV(fichierCSV);


        String texte = "===== RAPPORT DES REVENUS =====\n\n";

        double total = 0;
        int nbLecons = 0;
        int nbExamens = 0;


        for (String[] ligne : paiements) {


            LocalDate date =
                    LocalDate.parse(ligne[4], FORMAT_DATE);


            if (!date.isBefore(dateDebut)
                    && !date.isAfter(dateFin)) {


                double montant =
                        Double.parseDouble(ligne[5]);

                total += montant;


                if (ligne[3].equals("LPA")
                        || ligne[3].equals("LPZ")
                        || ligne[3].equals("LPS")
                        || ligne[3].equals("LT")) {

                    nbLecons++;

                } else if (ligne[3].equals("ET")
                        || ligne[3].equals("EP")
                        || ligne[3].equals("EPL")) {

                    nbExamens++;
                }


                texte += "Numéro paiement : "
                        + ligne[1] + "\n";

                texte += "Numéro SAAQ : "
                        + ligne[2] + "\n";

                texte += "Type : "
                        + ligne[3] + "\n";

                texte += "Date : "
                        + ligne[4] + "\n";

                texte += "Montant : "
                        + ligne[5] + " $\n";

                texte += "Statut : "
                        + ligne[6] + "\n";

                texte += "Méthode : "
                        + ligne[7] + "\n";

                texte += "-----------------------------\n";
            }
        }


        texte += "\n===== RÉSUMÉ =====\n";
        texte += "Total perçu : " + total + " $\n";
        texte += "Nombre de leçons : " + nbLecons + "\n";
        texte += "Nombre d'examens : " + nbExamens + "\n";


        String fichier = dossier + File.separator
                + "rapportRevenus"
                + dateDebut + "-"
                + dateFin
                + ".txt";


        GestionFichiers.ecrire(fichier, texte);
    }



    //Rapport pour depense voitures
    
    public static void rapportDepensesVoiture(String fichierCSV,
                                              LocalDate dateDebut,
                                              LocalDate dateFin,
                                              String dossier) {


        ArrayList<String[]> depenses =
                GestionFichiers.lireCSV(fichierCSV);


        String texte =
                "===== RAPPORT DÉPENSES VOITURE =====\n\n";

        double total = 0;


        for (String[] ligne : depenses) {


            LocalDate date =
                    LocalDate.parse(ligne[2], FORMAT_DATE);


            if (!date.isBefore(dateDebut)
                    && !date.isAfter(dateFin)) {


                double montant =
                        Double.parseDouble(ligne[5]);

                total += montant;


                texte += "Plaque : "
                        + ligne[1] + "\n";

                texte += "Catégorie : "
                        + ligne[3] + "\n";

                texte += "Description : "
                        + ligne[4] + "\n";

                texte += "Montant : "
                        + ligne[5] + " $\n";

                texte += "-----------------------------\n";
            }
        }


        texte += "\nTotal dépenses voiture : "
                + total + " $\n";


        String fichier = dossier + File.separator
                + "rapportDepensesVoiture"
                + dateDebut + "-"
                + dateFin
                + ".txt";


        GestionFichiers.ecrire(fichier, texte);
    }



    
    
    //Rapport des autres depenses 
    
    public static void rapportAutresDepenses(String fichierCSV,
                                             LocalDate dateDebut,
                                             LocalDate dateFin,
                                             String dossier) {


        ArrayList<String[]> depenses =
                GestionFichiers.lireCSV(fichierCSV);


        String texte =
                "===== RAPPORT AUTRES DÉPENSES =====\n\n";


        double total = 0;


        for (String[] ligne : depenses) {


            LocalDate date =
                    LocalDate.parse(ligne[1], FORMAT_DATE);


            if (!date.isBefore(dateDebut)
                    && !date.isAfter(dateFin)) {


                double montant =
                        Double.parseDouble(ligne[4]);


                total += montant;


                texte += "Catégorie : "
                        + ligne[2] + "\n";

                texte += "Description : "
                        + ligne[3] + "\n";

                texte += "Montant : "
                        + ligne[4] + " $\n";

                texte += "-----------------------------\n";
            }
        }


        texte += "\nTotal autres dépenses : "
                + total + " $\n";


        String fichier = dossier + File.separator
                + "rapportAutresDepenses"
                + dateDebut + "-"
                + dateFin
                + ".txt";


        GestionFichiers.ecrire(fichier, texte);
    }
}