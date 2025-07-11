package prenotazione_autobus;

public class Autista extends Persona implements UtenteAutobus {
	
	private String patente;
	private int as;

	public Autista(String nome, String cognome, String patente, int as) {
		super(nome, cognome);
		this.patente = patente;
		this.as = as;
	}

	@Override
	public String getTipo() {
		return "Autista";
	}

	
	
	
	
	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public int getAs() {
		return as;
	}

	public void setAs(int as) {
		this.as = as;
	}

	

	
	
}
