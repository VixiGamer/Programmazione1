package Animali;

public class Main {

	public static void main(String[] args) {
		
		Animale a1 = new Animale();
		Cane c1 = new Cane();
		Gatto g1 = new Gatto();
		
		String sa1 = a1.EmettiSuono();
		String sc1 = c1.EmettiSuono();
		String sg1 = g1.EmettiSuono();
		
		System.out.println(sa1);
		System.out.println(sc1);
		System.out.println(sg1);

	}

}
