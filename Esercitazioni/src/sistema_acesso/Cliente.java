package sistema_acesso;

public class Cliente extends Utente {

	Cliente(String username, String email) {
		super(username, email);
	}

	@Override
	String getMessaggioBenvenuto() {
		//System.out.println("Benvenuto nel nostro store!");
		return "Benvenuto nel nostro store!";
	}

}
