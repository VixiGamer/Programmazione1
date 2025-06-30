package costruttori;

public class Persona {
	
	protected String nome;
	protected String cognome;
	protected int eta;
	protected String citta;
	

	Persona(String nome, String cognome, int eta, String citta){
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.citta = citta;
	}

	@Override
	public String toString() {
		return nome + " " + cognome + " " + eta + " " + citta;
	}
	
}
