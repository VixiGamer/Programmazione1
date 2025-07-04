package dispositivi_diversi;

public class Main {

	public static void main(String[] args) {

		Dispositivi l1 = new Lampada("Lampada letto");
		Dispositivi ta1 = new Tapparella("Tapparella salotto");
		Dispositivi te1 = new Termostato("Termostato bagno");
		
		l1.eseguiAzione();
		ta1.eseguiAzione();
		te1.eseguiAzione();
	}

}
