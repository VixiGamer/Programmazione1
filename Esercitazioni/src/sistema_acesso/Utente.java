package sistema_acesso;

public abstract class Utente {
	
	String username;
	String email;
	
	Utente(String username, String email){
		this.username = username;
		this.email = email;
	}
	
	abstract String getMessaggioBenvenuto();
	
	public void stampaMessaggio() {
		System.out.println(username + ": " + getMessaggioBenvenuto());
	}
	
}
