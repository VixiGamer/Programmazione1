package classi_person;

public class Main {

	public static void main(String[] args) {
		Person p1 = new Person("Viggo", "Ponturo Nygaard", 21);
		Person p2 = new Person("Giuseppe", "Gravagno", 23);
		
		System.out.println(p1.name.toString() + " " + p1.surname.toString() + " " + p1.age);
		System.out.println(p2.name.toString() + " " + p2.surname.toString() + " " + p2.age);
	}
	
}