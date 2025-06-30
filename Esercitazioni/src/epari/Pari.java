package epari;

public class Pari {
	
	int numero;
	
	Pari(int numero){
		float resto = numero % 2;

		if (resto == 0) {
			System.out.println("Numero pari");
		} else {
			System.out.println("Numero dispari");
		}	
	}


	
}
