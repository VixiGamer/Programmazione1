package sistema_acesso;

public class Visitatore extends Utente {

	Visitatore(String username, String email) {
		super(username, email);
	}

	@Override
	String getMessaggioBenvenuto() {
		//System.out.println("Navigazione libera senza login");
		return "Navigazione libera senza login";
	}

}
