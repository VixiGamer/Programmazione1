package ufficio_postale;

public class Persona {
	
	private String nome;
	
	Persona(String nome) {
		this.nome = nome;
	}
	

	public String getNome() {
		return nome;
	}


	@Override
	public String toString() {
		return nome;
	}
	
}
