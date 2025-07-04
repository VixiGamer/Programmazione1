package Esercizio_2;

public class Professore extends Persona {
	
	private String materia;

	public Professore(String nome, String cognome, String materia) {
		super(nome, cognome);
		this.materia = materia;
	}
	
	@Override
	public void presentati() {
		System.out.println("Sono il prof. " + getNome() + " " + getCognome() + ", insegno " + materia);
	}

}
