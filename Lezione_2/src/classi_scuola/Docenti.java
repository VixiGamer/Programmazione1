package classi_scuola;

public class Docenti extends Person{
	
	float stipendio;
	int matricola;

	public Docenti(String nome, String cognome, int eta, float stipendio, int matricola) {		//Qui si aggiunge 'stipendio' e 'matricola', perchè sono gli attributi di 'studenti', ma aggiungiamo anche quelli di 'person', perchè 'studenti' dipende da 'person'
		super(nome, cognome, eta);
		
		this.stipendio = stipendio;
		this.matricola = matricola;
		
	}
}
