package e_commerce_dinamico;

import java.util.ArrayList;

import e_commerce_dinamico.Carrello;

public class Main {

	public static void main(String[] args) {
		/*Carrello ag = new Carrello();*/
		
		ArrayList<String> prodotti = new ArrayList<>();
		
		
		prodotti.add(Carrello.aggiungiProdotti("Mele"));
		prodotti.add(Carrello.aggiungiProdotti("Pere", 4));
		prodotti.add(Carrello.aggiungiProdotti("Banane", 2));
		prodotti.add(Carrello.aggiungiProdotti("Pesche", 10));
		
		for (String i : prodotti) {
			  System.out.println(i);
			}
		
		for (String i : prodotti) {
			int tot;
			
			prodotti[i];
			
		}
		
		
		
	}

}
