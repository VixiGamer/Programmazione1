package stud_matricola;

public class Studente {
	private int matri;
	private String nome;
	
	Studente(int matri, String nome){
		this.matri = matri;
		this.nome = nome;
	}

	public int getMatri() {
		return matri;
	}

	public void setMatri(int matri) {
		this.matri = matri;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
