package Esercizio_3;

public class Segreterio extends Utente{
	
	private String ufficio;

	public Segreterio(String username, String email, String ufficio) {
		super(username, email);
		this.ufficio = ufficio;
	}
	
	@Override
	public void presentati() {
		System.out.println("Personale di segreteria " + getUsername() + ", ufficio: " + ufficio);
	}

}
