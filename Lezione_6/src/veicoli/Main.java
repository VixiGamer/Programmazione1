package veicoli;

public class Main {

	public static void Main (String[] args) {
		
		Autoibrida a1 = new Autoibrida(70);	
		
		System.out.println(a1.toString());
		a1.ricarica();
		System.out.println(a1.toString());
		a1.connect();
	}
}
