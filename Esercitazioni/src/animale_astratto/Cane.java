package animale_astratto;

public class Cane extends Animale {
	
	String v = "Bau Bau!";

	@Override
	void emettiSuono() {
		System.out.println(v);
	}

}
