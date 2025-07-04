package Esercizio_3;

public class Professore extends Utente implements Autenticabile {
	
	private String materia;

	public Professore(String username, String email, String materia) {
		super(username, email);
		this.materia = materia;
	}

	@Override
	public void autentica(String password) throws AutenticazioneException{
		
			if(password == "prof2024") {
				System.out.println(getUsername() + ": PASSWORD CORRETTA");
			} else {
				System.out.println(getUsername() + ": PASSWORD ERRATA");
			}
	}
	
	@Override
	public void presentati() {
		System.out.println("Professore " + getUsername() + ", insegna: " + materia);
	}

}
