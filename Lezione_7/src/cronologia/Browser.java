package cronologia;

import java.util.Stack;

public class Browser {
	
	private static Stack<String> pila = new Stack<>();
	
	void visitPage(String url) {
		pila.push(url);
	}
	
	void goBack() {
		pila.pop();
		if (pila.size() > 0) {
			System.out.println("Ultima pagina visitata: " + pila.peek());
		} else {
			System.out.println("Nessuna pagina precedente.");
		}
		
		//Gestire il caso in cui la coda e vuota
	}
	
	void printHistory() {
		System.out.println("Cronologia: ");
		
		for(int i=pila.size()-1; i >=0 ;i-- ) 
			System.out.println(pila.get(i));
	}
}
