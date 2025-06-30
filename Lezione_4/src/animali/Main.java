package animali;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		
		Animale c1 = new Cane("cane 1");
		Animale c2 = new Cane("cane 2");
		Animale g1 = new Gatto("gatto 1");
		Animale g2 = new Gatto("gatto 1");
		
		Rifuggio.aggiungiAnimale(c1);
		Rifuggio.aggiungiAnimale(c2);
		Rifuggio.aggiungiAnimale(g1);
		Rifuggio.aggiungiAnimale(g2);
		
		Rifuggio.stampaVersi();
		
		
	}

}
