package ufficio_postale;

public class Main {
	
	//GestionePosta

	public static void main(String[] args) {
		
		Posta posta = new Posta();
		
		
		posta.entraInCoda(new Persona("Viggo"));
		posta.entraInCoda(new Persona("Peppe"));
		posta.entraInCoda(new Persona("Vincenzo"));
		
		posta.mostraCoda();
		
		posta.chiEIlProssimo();
		
		posta.servireProssimo();
		posta.servireProssimo();
		
		posta.mostraCoda();
		

	}

}
