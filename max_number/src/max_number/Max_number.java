package max_number;

public class Max_number {
	
	public static void numeroMax(int[] numeri, int maxN) {
		for (int i = 0; i < numeri.length; i++) {
			if (numeri[i] > maxN) {
				maxN = numeri[i];
			}
		}
		System.out.println(+maxN);
	}
	
	public static void main(String[] args) {
		int[] numeri = {2, 40, 35, 100, 85, 7};
		int maxN = 0;
		
		numeroMax(numeri, maxN);
		
	}
}
