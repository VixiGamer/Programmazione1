package permessi;

import java.util.ArrayList;

public class Cliente extends Utente {

	public Cliente(String username, String email) {
		super(username, email);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> GetPermessi() {
		ArrayList<String> permessi = new ArrayList<>();
		permessi.add("READ");
		return permessi;
	}

}
