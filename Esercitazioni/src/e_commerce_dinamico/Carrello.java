package e_commerce_dinamico;

import java.util.ArrayList;

public class Carrello {
	
	String nome;
	int qt;
	
	public static String aggiungiProdotti(String nome) {
		return "P: " + nome;
	}
	
	public static String aggiungiProdotti(String nome, int qt) {
		return "P: " + nome + " | " + "Qt: " + qt;
	}
	
}
