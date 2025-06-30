package arraylist;

import java.util.ArrayList;

public class Main2 {

	public static void main(String[] args) {
		
		//ArrayList 'padre'
		ArrayList<ArrayList<String>> classi = new ArrayList<>();

		//ArrayList 'figlio 1'
		ArrayList<String> classe1 = new ArrayList<String>();
		classe1.add("Viggo");
		classe1.add("Silas");
		
		//ArrayList 'figlio 2'
		ArrayList<String> classe2 = new ArrayList<String>();
		classe2.add("Ofelia");
		classe2.add("Vanessa");
		
		//Ora aggingiamo gli ArrayList figli al ArrayList padre
		classi.add(classe1);
		classi.add(classe2);
		
		for(int i = 0; i < classi.size(); i++) {
			System.out.println("Classe " + (i + 1));
			for(int j = 0; j < classi.get(i).size(); j++) {
				System.out.println(classi.get(i).get(j) + " ");
			}
		}
	}
}


