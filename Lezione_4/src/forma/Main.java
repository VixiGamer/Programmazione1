package forma;

public class Main {
	
	public static void main(String[] args) {
		Cerchio c = new Cerchio(5);
		double areaCerchio = c.area();
		System.out.println("L'area del cerchio è: " + areaCerchio);
		
		Rettangolo r = new Rettangolo(5, 10);
		double perimetroRettangolo = r.perimetro();
		System.out.println("Il perimetro del rettangolo è: " + perimetroRettangolo);
	}

}
