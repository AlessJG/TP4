import java.time.*;

public class TestsActivite {
	public static void testsActivite() throws Exception {
		System.out.println("\n---Tests pour classe Activite---\n");
		//constructeur
		TestsActivite.testActiviteConstructeur();
		
		//calculerMontant
		TestsActivite.testCalculerMontantLT();
		TestsActivite.testCalculerMontantLPAEcole();
		TestsActivite.testCalculerMontantLPAExterne();
		TestsActivite.testCalculerMontantET();
		TestsActivite.testCalculerMontantEP();
		TestsActivite.testCalculerMontantEPL();
		
		//getDate
		TestsActivite.testGetDate();
		
		//getHeure
		TestsActivite.testGetHeure();
		
		//getDuree
		TestsActivite.testGetDuree();
		
		//getType
		TestsActivite.testGetType();
		
		//getStatut
		TestsActivite.testGetStatut();
		
		//setStatut
		TestsActivite.testSetStatut();
		
		//getNumSAAQ
		TestsActivite.testGetNumSAAQ();
		
		//getVoitureExt
		TestsActivite.testGetVoitureExt();
		
		//TypeActivite next
		TestsActivite.testTypeActiviteNext();
	}
	
	public static Activite creerActivite(
	        Activite.TypeActivite type,
	        boolean voitureExt,
	        Activite.Statut statut) {

	    return new Activite(
	            LocalDate.of(2026, 7, 20),
	            LocalTime.of(14, 0),
	            Duration.ofMinutes(60),
	            "123456789",
	            new Moniteur(),
	            voitureExt,
	            type,
	            statut);
	}
	
	public static void testActiviteConstructeur() {

	    Activite a = creerActivite(
	            Activite.TypeActivite.LT,
	            false,
	            Activite.Statut.NC);

	    if (a.getDate() == 20 &&
	        a.getHeure().equals("14:00") &&
	        a.getDuree() == 60 &&
	        a.getNumSAAQ().equals("123456789") &&
	        a.getType() == Activite.TypeActivite.LT &&
	        a.getStatut() == Activite.Statut.NC &&
	        !a.getVoitureExt()) {

	        System.out.println("OO testActiviteConstructeur réussi");
	    }
	    else {
	        System.out.println("XX testActiviteConstructeur échoué");
	    }
	}
	
	public static void testCalculerMontantLT() {

	    Activite a = creerActivite(
	            Activite.TypeActivite.LT,
	            false,
	            Activite.Statut.NC);

	    if (a.getMontant() == 45.0) {
	        System.out.println("OO testCalculerMontantLT réussi");
	    }
	    else {
	        System.out.println("XX testCalculerMontantLT échoué");
	    }
	}
	
	public static void testCalculerMontantLPAEcole() {

	    Activite a = creerActivite(
	            Activite.TypeActivite.LPA,
	            false,
	            Activite.Statut.NC);

	    if (a.getMontant() == 75.0) {
	        System.out.println("OO testCalculerMontantLPAEcole réussi");
	    }
	    else {
	        System.out.println("XX testCalculerMontantLPAEcole échoué");
	    }
	}
	
	public static void testCalculerMontantLPAExterne() {

	    Activite a = creerActivite(
	            Activite.TypeActivite.LPA,
	            true,
	            Activite.Statut.NC);

	    if (a.getMontant() == 50.0) {
	        System.out.println("OO testCalculerMontantLPAExterne réussi");
	    }
	    else {
	        System.out.println("XX testCalculerMontantLPAExterne échoué");
	    }
	}
	
	public static void testCalculerMontantET() {

	    Activite a = creerActivite(
	            Activite.TypeActivite.ET,
	            false,
	            Activite.Statut.NC);

	    if (a.getMontant() == 40.0) {
	        System.out.println("OO testCalculerMontantET réussi");
	    }
	    else {
	        System.out.println("XX testCalculerMontantET échoué");
	    }
	}
	
	public static void testCalculerMontantEP() {

	    Activite a = creerActivite(
	            Activite.TypeActivite.EP,
	            false,
	            Activite.Statut.NC);

	    if (a.getMontant() == 150.0) {
	        System.out.println("OO testCalculerMontantEP réussi");
	    }
	    else {
	        System.out.println("XX testCalculerMontantEP échoué");
	    }
	}
	
	public static void testCalculerMontantEPL() {

	    Activite a = creerActivite(
	            Activite.TypeActivite.EPL,
	            true,
	            Activite.Statut.NC);

	    if (a.getMontant() == 85.0) {
	        System.out.println("OO testCalculerMontantEPL réussi");
	    }
	    else {
	        System.out.println("XX testCalculerMontantEPL échoué");
	    }
	}
	
	public static void testGetDate() {

	    Activite a = creerActivite(
	            Activite.TypeActivite.LT,
	            false,
	            Activite.Statut.NC);

	    if (a.getDate() == 20) {
	        System.out.println("OO testGetDate réussi");
	    }
	    else {
	        System.out.println("XX testGetDate échoué");
	    }
	}
	
	public static void testGetHeure() {

	    Activite a = creerActivite(
	            Activite.TypeActivite.LT,
	            false,
	            Activite.Statut.NC);

	    if (a.getHeure().equals("14:00")) {
	        System.out.println("OO testGetHeure réussi");
	    }
	    else {
	        System.out.println("XX testGetHeure échoué");
	    }
	}
	
	public static void testGetDuree() {

	    Activite a = creerActivite(
	            Activite.TypeActivite.LT,
	            false,
	            Activite.Statut.NC);

	    if (a.getDuree() == 60) {
	        System.out.println("OO testGetDuree réussi");
	    }
	    else {
	        System.out.println("XX testGetDuree échoué");
	    }
	}
	
	public static void testGetType() {

	    Activite a = creerActivite(
	            Activite.TypeActivite.LPS,
	            false,
	            Activite.Statut.NC);

	    if (a.getType() == Activite.TypeActivite.LPS) {
	        System.out.println("OO testGetType réussi");
	    }
	    else {
	        System.out.println("XX testGetType échoué");
	    }
	}
	
	public static void testGetStatut() {

	    Activite a = creerActivite(
	            Activite.TypeActivite.LT,
	            false,
	            Activite.Statut.C);

	    if (a.getStatut() == Activite.Statut.C) {
	        System.out.println("OO testGetStatut réussi");
	    }
	    else {
	        System.out.println("XX testGetStatut échoué");
	    }
	}
	
	public static void testSetStatut() {

	    Activite a = creerActivite(
	            Activite.TypeActivite.LT,
	            false,
	            Activite.Statut.NC);

	    a.setStatut(Activite.Statut.C);

	    if (a.getStatut() == Activite.Statut.C) {
	        System.out.println("OO testSetStatut réussi");
	    }
	    else {
	        System.out.println("XX testSetStatut échoué");
	    }
	}
	
	public static void testGetNumSAAQ() {

	    Activite a = creerActivite(
	            Activite.TypeActivite.LT,
	            false,
	            Activite.Statut.NC);

	    if (a.getNumSAAQ().equals("123456789")) {
	        System.out.println("OO testGetNumSAAQ réussi");
	    }
	    else {
	        System.out.println("XX testGetNumSAAQ échoué");
	    }
	}
	
	public static void testGetVoitureExt() {

	    Activite a = creerActivite(
	            Activite.TypeActivite.LPA,
	            true,
	            Activite.Statut.NC);

	    if (a.getVoitureExt()) {
	        System.out.println("OO testGetVoitureExt réussi");
	    }
	    else {
	        System.out.println("XX testGetVoitureExt échoué");
	    }
	}
	
	public static void testTypeActiviteNext() {

	    if (Activite.TypeActivite.LPA.next() ==
	            Activite.TypeActivite.LPZ &&
	        Activite.TypeActivite.EPL.next() ==
	            Activite.TypeActivite.LPA) {

	        System.out.println("OO testTypeActiviteNext réussi");
	    }
	    else {
	        System.out.println("XX testTypeActiviteNext échoué");
	    }
	}
}
