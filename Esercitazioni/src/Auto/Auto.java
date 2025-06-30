package Auto;

public class Auto {
	
	String marca;
	String modello;

	Auto(String marca, String modello) {
		this.marca = marca;
		this.modello = modello;
	}

	@Override
	public String toString() {
		return "Marca: " + marca + "\nModello: " + modello + "\n";
	}
	
	

}
