import java.time.LocalDate;

public class Eleve extends Personne {
	private LocalDate dateInscription;
	private LocalDate dateFin;
	private enum Lecon {
		LT1, LT2, LT3, 
		LT4, LT5, ET, 
		LPS, LT6, LPZ1, 
		LPZ2, LPZ3, LPZ4, 
		LT7, LPA1, LT8, 
		LPA2, LT9, LPA3, 
		LT10, LPA4, LPA5, 
		LT11, LT12;

	    private static final Lecon[] vals = values();
	    
	    public Lecon next() {
	        return vals[(this.ordinal() + 1) % vals.length];
	    }
	}
}
