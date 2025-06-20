package arraylist_prodotti;
import java.util.ArrayList;
import java.time.LocalDate;

public class main {
	
	public static void main(String[] args) {
		
		ArrayList<Prodotto> Lista_prodotti = new ArrayList<Prodotto>();
	
		Lista_prodotti.add(new Prodotto("Mela", "Melinda", 3, LocalDate.of(2025,6,14)));
		Lista_prodotti.add(new Prodotto("Biscotti", "Deco", 3.5f, LocalDate.of(2026,3,1)));
		Lista_prodotti.add(new Prodotto("Cornetto", "Algida", 4, LocalDate.of(2015,6,8)));
		Lista_prodotti.add(new Prodotto("Pasta", "Dececco", 1.69f, LocalDate.of(2028,3,1)));
		
		Lista_prodotti.remove(1);
		
		Lista_prodotti.add(1, new Prodotto("Yougurt 0%", "Land", 0.89f, LocalDate.of(2025,6,20)));
		
		
		
		for (Prodotto prodotti : Lista_prodotti) {
		    System.out.println(prodotti);
		    
		    if (prodotti.dateNow()) {
		    	System.out.println("\nScaduto");
		    } else {
		    	System.out.println("\nNon Scaduto");
		    }
		    
		    System.out.println("---------------");
		}
	}
}
