package prenotazione_autobus;

public class PostiEsauritiPasseggeroException extends Exception {
	public PostiEsauritiPasseggeroException() {
		System.out.println("Posti esauriti per passeggeri | MAX 20");
	}
}
