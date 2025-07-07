package swing_scuola;

public class Studente extends Person {
	
	String matricola;

	public Studente(String nome, String cognome, String matricola) {
		super(nome, cognome);
		this.matricola = matricola;
	}
	
}
