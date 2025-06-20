package permessi;

import java.util.ArrayList;

public class GestioneUtenti {
	ArrayList<Utente> utenti = new ArrayList<>();
	
	public void aggiungiUtente(Utente u) {
		utenti.add(u);
	}
	
	public void stampaPermessi() {
		for (Utente u : utenti) {
			u.stampaInfo();
			System.out.println("Permessi:" + u.GetPermessi());
		}
	}
	
}
