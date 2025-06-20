package e_commerce;

public class Main {

	public static void main(String[] args) {
		Carrello agiungiProdotti = new Carrello();
		
		System.out.println(Carrello.aggiungiProdotti("Mele"));
		
		System.out.println(Carrello.aggiungiProdotti("Pere", 4));

	}

}
