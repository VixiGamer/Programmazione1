package veicoli;

public class Autoibrida extends Veicolo implements Connettivita, Ricaricabile {
	private boolean connesso;
	
	
	public Autoibrida(int batteria) {
		super(batteria);
		this.connesso = false;
	}

	@Override
	public void ricarica() {
		batteria = 100;
	}

	@Override
	public void connect() {
		this.connesso = true;
		System.out.println("BlueTooh CONNESSO");
		
	}

	@Override
	public void disconnected() {
		this.connesso = false;
		System.out.println("BlueTooh DISCONNESSO");
		
	}

	@Override
	public boolean isConnected() {
		
		return this.connesso;
	}
	
}
