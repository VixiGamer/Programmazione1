package prenotazione_autobus;

public class Passeggero extends Persona implements UtenteAutobus {
	
	private String cf;
	private String destinazione;
	private boolean luggage;

	public Passeggero(String nome, String cognome, String cf, String destinazione, boolean luggage) {
		super(nome, cognome);
		this.cf = cf;
		this.destinazione = destinazione;
		this.luggage = luggage;
	}

	@Override
	public String getTipo() {
		return "Passegero";
	}
	
	
	
	
	

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

	public boolean isLuggage() {
		return luggage;
	}

	public void setLuggage(boolean luggage) {
		this.luggage = luggage;
	}
	
	
	


}
