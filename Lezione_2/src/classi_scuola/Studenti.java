package classi_scuola;

public class Studenti extends Person {
	
	float media;
	int matricola;

	public Studenti(String nome, String cognome, int eta, float media, int matricola) {
		super(nome, cognome, eta);
		
		this.media = media;
		this.matricola = matricola;
		
	}
}
