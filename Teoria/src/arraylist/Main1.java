package arraylist;

import java.util.ArrayList;

public class Main1 {

	public static void main(String[] args) {
		
		ArrayList <String> persone = new ArrayList<>();
		//L'arraylist e vuoto, aggiungiamo degli elementi
		
		persone.add("Viggo");
		persone.add("Silas");
		persone.add("Ofelia");
		
		//Ciclo for
		for(int i = 0; i < persone.size(); i++) {
			System.out.println(persone.get(i));
		}

	}

}

