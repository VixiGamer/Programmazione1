package cerchio;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Inserire raggio: ");
		float r = scanner.nextFloat();
		
		float p1 = Cerchio.Perimetro(r);
		float a1 = Cerchio.Area(r);
		
		System.out.println("Perimetro con 'r = " + r + "': " + p1);
		System.out.println("Perimetro con 'r = " + r + "': " + a1);

	}

}
