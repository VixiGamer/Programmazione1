package calcolatrice;

public class Main {

	public static void main(String[] args) {
		
		Calcolatrice calcolatrice = new Calcolatrice();
		
		System.out.println(calcolatrice.Somma(1, 2));
		
		System.out.println(calcolatrice.Somma(13, 21, 5));
		
		System.out.println(calcolatrice.Somma(1.3, 2.5));
		
		System.out.println(calcolatrice.Somma("Tre", "Due"));
		

	}

}
