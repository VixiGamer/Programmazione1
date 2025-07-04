package sistema_acesso;

public class Main {

	public static void main(String[] args) {
		
		Utente a1 = new Admin("Viggo", "vpn@gmail.com");
		Utente a2 = new Admin("Silas", "sls@gmail.com");
		Utente a3 = new Admin("Ofelia", "ofl@gmail.com");
		Utente c1 = new Cliente("Anna", "anna@gmail.com");
		Utente v1 = new Visitatore("Marco", "marco@gmail.com");
		
		a1.stampaMessaggio();
		a2.stampaMessaggio();
		a3.stampaMessaggio();
		c1.stampaMessaggio();
		v1.stampaMessaggio();

	}

}
