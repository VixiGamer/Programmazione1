package Esercizio_3;

public class Utente {
	
	private String username;
	private String email;
	
	public Utente(String username, String email) {
		this.username = username;
		this.email = email;
	}
	
	public void presentati() {
		System.out.println("Utente generico: " + username + ", email: " + email);
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void autentica(String string) throws AutenticazioneException {
		
	}

}
