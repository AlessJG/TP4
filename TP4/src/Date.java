import java.time.*;
import java.util.*;

public class Date {
	private ArrayList<String> heuresIndispo;
	private boolean estIndispo;
	
	public Date() {
		this.heuresIndispo = new ArrayList<String>();
		this.estIndispo = false;
	}
	
	public void ajouterIndispo(String heure) {
		heuresIndispo.add(heure);
	}
	
	public void enleverIndispo(String heure) {
		if(heuresIndispo.contains(heure)) {
			heuresIndispo.remove(heure);
		}
	}
	
	public void rendreIndispo() {
		estIndispo = true;
	}
}
