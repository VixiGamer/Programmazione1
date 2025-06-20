package animali;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		
		ArrayList<Animale> RifuggioAnimali = new ArrayList<Animale>();
		
		
		Cane c = new Cane("Cane");
		Gatto g = new Gatto("Gatto");
		
		RifuggioAnimali.add(c);
		RifuggioAnimali.add(g);
		
		Rifuggio.stampaVersi();
		
		
	}

}
