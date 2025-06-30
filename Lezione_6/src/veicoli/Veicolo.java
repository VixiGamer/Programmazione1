package veicoli;

abstract public class Veicolo {
	
	protected int batteria;

	public Veicolo(int batteria) {
		this.batteria = batteria;
	}
	
	public int getBatteria() {
		return batteria;
	}

	public void setBatteria(int batteria) {
		this.batteria = batteria;
	}


	@Override
	public String toString() {
		return "Veicolo [batteria=" + batteria + "]";
	}
	
	
	
	
	
}
