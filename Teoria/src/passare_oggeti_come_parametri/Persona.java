package passare_oggeti_come_parametri;

public class Persona {
	
	protected String nome;
	protected String cognome;
	protected int eta;
	
	Persona(String nome, String cognome, int eta){
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
	}
	
	public String toString() {
		return nome + " " + cognome + " " + eta;
	}
	
	//Qui passo 'persona' come parametro
	void Saluta(Persona personaDaSalutare) {
		System.out.println("Ciao " + personaDaSalutare.nome + ", io sono " + this.nome);
	}
}

