package array_trycatch;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String[] prodotti = {"Viggo", "Giuseppe", "Vincenzo", "Ettore", "Andrea"};
		
		System.out.println("Inserire il numero corrisspondente al nome: ");
		int n1 = scanner.nextInt();
		
		try {
			System.out.println("In numero " + "'" +  n1 + "'" + " è associato a " + "'" + prodotti[n1] + "'");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.print("In numero " + "'" +  n1 + "'" + " NON è associato a NESSUNO");
		}
		finally {
			scanner.close();
		}
		
		
	}
}
