package dispositivi_diversi;

public abstract class Dispositivi {
	
	String nome;
	
	Dispositivi (String nome){
		this.nome = nome;
	}
	
	abstract public void eseguiAzione();
	
	public void descrivi() {
		System.out.println("Dispositivo " + nome + " pronto all'azione");
	}

}
