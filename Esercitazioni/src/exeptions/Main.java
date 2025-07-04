package exeptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		//Nel 'try' mettiamo il codice che potrebbe creare un errore
		try {
			System.out.println("Inserisci numero 1: ");
			int x = scanner.nextInt();
		
			System.out.println("Inserisci numero 2: ");
			int y = scanner.nextInt();
			
			System.out.println(x / y);
		
		// I 'catch' gestiscono se ce un errore, in questo caso matematico
		} catch (ArithmeticException e){
			System.out.println("Numero non divisibile per 0");
			
		// E qui se dvidiamo un numero per una stringa
		} catch(InputMismatchException e) {
			System.out.println("Numero non divisibile per una stringa");
		
		/*E il 'finally' esegue il codice indipendentemente dal fatto che
		  si sia verificata o meno un’eccezione*/
		} finally {
			scanner.close();
		}
	}
}

