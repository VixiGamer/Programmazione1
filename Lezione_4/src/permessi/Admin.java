package permessi;

import java.util.ArrayList;

public class Admin extends Utente {

	public Admin(String username, String email) {
		super(username, email);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public ArrayList<String> GetPermessi() {
		ArrayList<String> permessi = new ArrayList<>();
		permessi.add("READ");
		permessi.add("WRITE");
		permessi.add("DELETE");
		return permessi;
	}

}
