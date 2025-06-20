package calcolatrice_funzioni;

import java.util.Scanner;

public class calcolatrice_funzioni {
	
	//Somma
	public static void somma(float n1, float n2) {
		float somma = n1 + n2;
		System.out.println(+somma);
	}
	
	//Sottrazione
	public static void sottrazione(float n1, float n2) {
		float sottrazione = n1 - n2;
		System.out.println(+sottrazione);
	}
	
	//Moltiplicazione
	public static void moltiplicazione(float n1, float n2) {
		float moltiplicazione = n1 * n2;
		System.out.println(+moltiplicazione);
	}
	
	//Divisione
	public static void divisione(float n1, float n2) {
		float divisione = n1 / n2;
		System.out.println(+divisione);
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//Chiedi il primo numero
		System.out.println("Inserisci numero 1: ");
		float n1 = scanner.nextFloat();
		
		//Chiedi il secondo numero
		System.out.println("Inserisci numero 2: ");
		float n2 = scanner.nextFloat();
		
		//chiedi l'operazione da fare
		System.out.println("Scegliere operazione: 1-Somma 2-Sottrazione 3-Moltiplicazione 4-Divisione");
		
		int operation = scanner.nextInt();
		
		scanner.close();
		
		switch (operation) {
			case 1:
				somma(n1, n2);
				break;
				
			case 2:
				sottrazione(n1, n2);
				break;
				
			case 3:
				moltiplicazione(n1, n2);
				break;
				
			case 4:
				divisione(n1, n2);
				break;
		}
	}
}
