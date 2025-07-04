package Esercizio_2;

public class Studente extends Persona {
	
	private String matricola;

	public Studente(String nome, String cognome, String matricola) {
		super(nome, cognome);
		this.matricola = matricola;
	}
	
	@Override
	public void presentati() {
		System.out.println("Sono lo studente " + getNome() + " " + getCognome() + ", matricola: " + matricola);
	}

}
