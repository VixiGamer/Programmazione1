package array_trycatch;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String[] prodotti = new String[5];
		
		prodotti[0] = "Viggo";
		prodotti[1] = "Giuseppe";
		prodotti[2] = "Vincenzo";
		prodotti[3] = "Ettore";
		prodotti[4] = "Andrea";
		
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
