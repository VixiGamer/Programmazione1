package arraylist;
import java.util.ArrayList;

public class main {
	public static void main(String[] args) {
		
		//Per creare una lista
		ArrayList<String> Lista = new ArrayList<>();
		
		//Per aggiungere dei valori ad una lista
		Lista.add("Ciao");
		Lista.add("come");
		Lista.add("stai");
		Lista.add("bene");
		Lista.add("male");
		
		//Per rimuovere dei valori ad una lista
		Lista.remove(4);
		
		//Per aggiungere dei valori ad una lista in una posizione specifica
		Lista.add(2, "Appoi");
		
		//Foreach
		for (Object parole : Lista) {
		    System.out.println(parole);
		}
		
	}
	
}
