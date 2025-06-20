package calcolatrice;

import java.util.Scanner;

public class Calcolatrice {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Inserisci numero 1: ");
		float n1 = scanner.nextFloat();
		System.out.println("Inserisci numero 2: ");
		float n2 = scanner.nextFloat();
		
		System.out.println("Scegliere operazione: 1-Somma 2-Sottrazione 3-Moltiplicazione 4-Divisione");
		
		int operation = scanner.nextInt();
		
		switch (operation) {
			case 1:
				float somma = n1 + n2;
				System.out.println(+somma);
				break;
				
			case 2:
				float sottrazione = n1 - n2;
				System.out.println(+sottrazione);
				break;
				
			case 3:
				float moltiplicazione = n1 * n2;
				System.out.println(+moltiplicazione);
				break;
				
			case 4:
				float divisione = n1 / n2;
				System.out.println(+divisione);
				break;
		}
	}
}
