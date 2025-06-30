package animali;
import java.util.ArrayList;

public class Rifuggio {

	static ArrayList<Animale> animali = new ArrayList<Animale>();
	
	static void aggiungiAnimale(Animale a){
		animali.add(a);
	}
	
	static void stampaVersi() {
		for (Animale a: animali) {
			a.emettiVerso();
		}
	}
	
	
	

}
