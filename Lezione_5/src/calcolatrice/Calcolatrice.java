package calcolatrice;

public class Calcolatrice {
	
	int n1;
	int n2;
	int n3;
	double d1;
	double d2;
	String s1;
	String s2;
	
	
	
	public int Somma(int n1, int n2) {
		return n1 + n2;
	}
	
	public int Somma(int n1, int n2, int n3) {
		return n1 + n2 + n3;
	}
	
	public double Somma(double d1, double d2) {
		return d1 + d2;
	}
	
	public String Somma(String s1, String s2) {
		return s1 + " + " + s2; 
	}
}
