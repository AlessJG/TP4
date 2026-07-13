import java.time.LocalDate;

public class Depense {
	private int IDDepense;
	private LocalDate date;
	private Type type;
	private Categorie categorie;
	private String description;
	private double montant;
	
	private enum Type{
		V,
		A
	}
	private enum CategorieAutre{
		P,
		B,
		T,
		I,
		A
	}
}
