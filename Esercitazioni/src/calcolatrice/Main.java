package calcolatrice;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Inserire n1: ");
		int n1 = scanner.nextInt();
		
		System.out.println("Inserire n2: ");
		int n2 = scanner.nextInt();
		
		
		int somma = Calcolatrice.Somma(n1, n2);
		int sottrazione = Calcolatrice.Sottrazione(n1, n2);
		int moltiplicazione = Calcolatrice.Moltiplicazione(n1, n2);
		int divisione = Calcolatrice.Divisione(n1, n2);
		
		System.out.println(n1 + " + " + n2 + " = " + somma);
		System.out.println(n1 + " - " + n2 + " = " + sottrazione);
		System.out.println(n1 + " x " + n2 + " = " + moltiplicazione);
		System.out.println(n1 + " / " + n2 + " = " + divisione);

	}

}
