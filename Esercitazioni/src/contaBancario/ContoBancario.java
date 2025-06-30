package contaBancario;

public class ContoBancario {
	private static int saldo = 125;
	private int importo;
	
	static int Deposita(int importo) {
		return saldo + importo;
	}
	
	
}
