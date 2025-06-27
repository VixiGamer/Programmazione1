package calcolatrice_completa;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Inserisci numero 1: ");
		int n1 = scanner.nextInt();
		System.out.println("Inserisci numero 2: ");
		int n2 = scanner.nextInt();
		
		System.out.println("Scegliere operazione: \n1-Somma \n2-Sottrazione \n3-Moltiplicazione \n4-Divisione \n5-Tutte le operazioni");
		
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
			
			try {
			  System.out.println(n1 / n2);
			} catch(ArithmeticException e) {
			  System.out.println("IMPOSSIBBILE DIVIDERE PER 0");
			}
					
			break;
			
		case 5:
			System.out.println("Somma: " + (n1 + n2));
			System.out.println("Sottrazione: " + (n1 - n2));
			System.out.println("Moltiplicazione: " + (n1 * n2));
			try {
				  System.out.println(n1 / n2);
				}
			catch(ArithmeticException e) {
				  System.out.println("IMPOSSIBBILE DIVIDERE PER 0");
				}
			finally {
				scanner.close();
			}
			
	}
		

		
	}
}
