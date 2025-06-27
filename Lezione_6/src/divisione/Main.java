package divisione;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Inserisci numero 1: ");
		int n1 = scanner.nextInt();
		System.out.println("Inserisci numero 2: ");
		int n2 = scanner.nextInt();
		
		/* Il 'try, catch', serve per prevenire che una parte di codice ci dia errore, e quindi il 'catch' lo gestisce.
		 * In questo caso se si divide un numero per 0, ovvero impossibile e il codice da errore, invece di crashare stampa "IMPOSSIBBILE DIVIDERE PER 0" */
		
		try {		/* Viene esegito se NON c'è un errore */
			  System.out.println(n1 / n2);
			}
		catch(ArithmeticException e) {		/* Viene esegito se c'è un errore */
			  System.out.println("IMPOSSIBBILE DIVIDERE PER 0");
			}
		finally {			 /* Viene esegito SEMPRE */
			scanner.close();		/* Qui chiudiamo lo scanner */
		}
		
	}
}
