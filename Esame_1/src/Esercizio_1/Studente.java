package Esercizio_1;

public class Studente {
	
	private String nome;
	private String cognome;
	private int annoNascita;
	
	public Studente(String nome, String cognome, int annoNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.annoNascita = annoNascita;
	}
	
	public void stampaScheda() {
		System.out.println(nome + " " + cognome + ", nato nel " + annoNascita);
	}
	

}
