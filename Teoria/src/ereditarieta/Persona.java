package ereditarieta;

public class Persona {

	String nome;
	String cognome;
	
	Persona(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}
	
	void Saluta() {
		System.out.println("Ciao");
	}
}

