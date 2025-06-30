package arraylist;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList <String> persone = new ArrayList<>();
		//L'arraylist e vuoto, aggiungiamo degli elementi
		
		persone.add("Viggo");
		persone.add("Silas");
		persone.add("Ofi");
		
		System.out.println(persone.get(2));
		//Si usa .get() invece di [], che si usano negli array
		
		//Se vogliamo modificare un elemento
		persone.set(2, "Ofelia");
		System.out.println(persone.get(2));

	}

}


