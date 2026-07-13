import java.util.Scanner;

public class Calendrier {
	//a sa creation, lit un fichier calendrier avec 
	private int annee;
	private int mois;
	private Scanner sc;
	private int[] datesDisponibles;
	//moniteur fourni dates indisponibles
	//peut aussi rendre dates redisponible
	//si jour indisponible alors pas affiche
	//quand eleves sinscrive, date devient indisponible
	//erreurs possibles:
	//1.comparer avec date auj pour sassurer que ne pas essaye de inscrire a date passee
	//2.sassurer que n'essaie pas de s'inscrire a date inexistante (ex 0, -, passer max)
	//3.sassurer que n'essaie pas de s'sinscrire a date indisponible

    public void afficherCalendrier() {
    	sc = new Scanner(System.in);

        System.out.print("Entrer l'année voulue: ");
        annee = sc.nextInt();


        System.out.print("Entrer le mois voulu: ");
        mois = sc.nextInt();

        int date = 1;
        int m = 1;
        int a = 1;
        int j = 1;

        String jours[] = {"DIM", "LUN", "MAR", "MER", "JEU", "VEN", "SAM"};
        String nomMois[] = {"JANVIER", "FEVRIER", "MARS", "AVRIL", "MAI", "JUIN", "JUILLET",
        					"AOUT", "SEPTEMBRE", "OCTOBRE", "NOVEMBRE", "DECEMBRE"};

        int nbJours[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        while (true) {

            if (date == 1 && mois == m && annee == a) {
                break;
            }

            if (a % 4 == 0 && a % 100 != 0 || a % 100 == 0) {
                nbJours[1] = 29;
            }

            else {
                nbJours[1] = 28;
            }
            j++;
            date++;

            if (date > nbJours[mois - 1]) {
                mois++;
                date = 1;
            }

            if (mois > 12) {
                mois = 1;
                a++;
            }

            if (j == 7) {
                j = 0;
            }
        }

        int c = j;

        if (a % 4 == 0 && a % 100 != 0 || a % 400 == 0) {
            nbJours[1] = 29;
        }
        else {
            nbJours[1] = 28;
        }

        System.out.println("MOIS:" + nomMois[m - 1]);

        for (int k = 0; k < 7; k++) {
            System.out.print("   " + jours[k]);
        }

        System.out.println();

        for (int i = 1; i <= (nbJours[m - 1] + j); i++) {
            if (i > 6) {
                j = j % 6;
            }
        }

        int espaces = j;
        if (espaces < 0) {
            espaces = 6;
        }

        for (int i = 0; i < espaces; i++)
            System.out.print("      ");
        for (int i = 1; i <= nbJours[m - 1]; i++) {
            System.out.printf(" %4d ", i);

            if (((i + espaces) % 7 == 0)
                || (i == nbJours[m - 1]))
                System.out.println();
        }
    }
    public static void main(String args[]) {
    	Calendrier cal = new Calendrier();
    	cal.afficherCalendrier();
    }
}