package e_commerce;

import java.util.ArrayList;

public class Carrello {
	
	static ArrayList<String> prodotti = new ArrayList<>();
	
	String nome;
	int qt;
	
	public static String aggiungiProdotti(String nome) {
		prodotti.add(nome);
		return "Prodotto: " + nome;
	}
	
	public static String aggiungiProdotti(String nome, int qt) {
		prodotti.add(nome + qt);
		return "Prodotto: " + nome + " | " + "Quantità: " + qt;
	}
	
}
