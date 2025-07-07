package swing_scuola;

public class Professore extends Person {
	
	String materia;

	public Professore(String nome, String cognome, String materia) {
		super(nome, cognome);
		this.materia = materia;
	}

}
