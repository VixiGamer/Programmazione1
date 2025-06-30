package somma;

public class Somma {
	
	int numero;
	int tot;
	
	Somma (int numero){
		for(int i = 0; i <= numero; i++) {
			tot = tot + i;
		}
		System.out.println(tot);
	
	}
	
}
