package array;

public class Main {

	public static void main(String[] args) {
		
		int[] numeri = new int[3];
		
		numeri[0] = 10;
		numeri[1] = 20;
		numeri[2] = 30;
		
		System.out.println(numeri[2]);			//Qui stampa 30
		System.out.println(numeri.length);		//Qui stampa 3
		
		numeri[2] = 100;	//Sovrascrivo il 30 con il 100
		
		System.out.println(numeri[2]);

	}

}




