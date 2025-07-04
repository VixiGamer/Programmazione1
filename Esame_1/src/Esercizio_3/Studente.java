package Esercizio_3;

public class Studente extends Utente implements Autenticabile {
	
	private String matricola;

	public Studente(String username, String email, String matricola) {
		super(username, email);
		this.matricola = matricola;
	}

	@Override
	public void autentica(String password) throws AutenticazioneException{
		
			if(password == "studente123") {
				System.out.println(getUsername() + ": PASSWORD CORRETTA");
			} else {
				System.out.println(getUsername() + ": PASSWORD ERRATA");
			}
	}
	
	@Override
	public void presentati() {
		System.out.println("Studente " + getUsername() + ", mtricola: " + matricola);
	}

}
