package sistema_acesso;

public class Admin extends Utente {

	Admin(String username, String email) {
		super(username, email);
	}

	@Override
	String getMessaggioBenvenuto() {
		//System.out.println("Acesso amministratore completo");
		return "Acesso amministratore completo";
	}

}
