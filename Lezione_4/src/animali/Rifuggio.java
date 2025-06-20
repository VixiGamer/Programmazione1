package animali;
import java.util.ArrayList;

public class Rifuggio {
	
	public Animale Animale_a;
	
	private ArrayList<Animale> RifuggioAnimali;

	
	public Rifuggio(Animale animale_a, ArrayList<Animale> rifuggioAnimali) {
		Animale_a = animale_a;
		RifuggioAnimali = rifuggioAnimali;
		
		RifuggioAnimali.add(Animale_a);
	}

	public void stampaVersi() {
	
		for (Animale animale_s : RifuggioAnimali) {
			animale_s.emettiVerso();
		}
		
	}

	
	
	

}
