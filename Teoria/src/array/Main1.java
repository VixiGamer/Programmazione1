package array;

public class Main1 {

	public static void main(String[] args) {
		
		int[] numeri = {10, 20, 30, 40, 50};
		
		
		System.out.println(numeri[2]);			//Qui stampa 30
		System.out.println(numeri.length);		//Qui stampa 5
		
		numeri[2] = 100;	//Sovrascrivo il 30 con il 100
		
		System.out.println(numeri[2]);

	}

}


