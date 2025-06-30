package permessi;

public class Main {

	public static void main(String[] args) {
		
		GestioneUtenti sistema = new GestioneUtenti();
		
		//Creo 2 Clineti
		Cliente c1 = new Cliente("EA", "ea@gmai.com");
		Cliente c2 = new Cliente("PG", "pg@gmail.com");
		
		//Creo 2 Admin
		Admin a1 = new Admin("VPN", "vpn@gmail.com");
		Admin a2 = new Admin("SPN", "spn@gmail.com");
		
		//Li aggiungo al sistema
		sistema.aggiungiUtente(c1);
		sistema.aggiungiUtente(c2);
		sistema.aggiungiUtente(a1);
		sistema.aggiungiUtente(a2);
		
		//Stampo i permessi
		sistema.stampaPermessi();
		
		/*----------------------DA COMPLETARE---------------------------*/
	}

}
