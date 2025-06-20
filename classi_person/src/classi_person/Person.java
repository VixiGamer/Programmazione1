package classi_person;

public class Person {	//Calsse
	
	public String name;
	public String surname;
	public int age;
	
	public Person(String name, String surname, int age) {	//Costruttore
		this.name = name;			//Il 'this.name' si riferisce alla classe, invece ' = name;' si riferisce al costruttore
		this.surname = surname;
		this.age = age;
	}
}
