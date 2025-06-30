package animale_astratto;

public class Gatto extends Animale {
	
	String v = "Miao Miao!";

	@Override
	void emettiSuono() {
		System.out.println(v);
	}

}
