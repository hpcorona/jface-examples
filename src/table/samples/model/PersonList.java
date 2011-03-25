package table.samples.model;

import java.util.ArrayList;

import table.tools.ModelEventProvider;

public class PersonList extends ModelEventProvider {
	
	private ArrayList<Person> persons;
	
	public PersonList() {
		persons = new ArrayList<Person>();
	}
	
	public void generateSampleData() {
		persons.add(new Person("Hilario Ramirez Ort’z", "Av. San Juli‡n #1421", "Computer Programmer", true));
		persons.add(new Person("Adalberto Levi Manriquez", "Av. Palo Santo #163", "Technician", false));
	}
	
	public Object[] toArray() {
		return persons.toArray();
	}
	
	public void newPerson(Person p) {
		persons.add(p);
		notifyNew(p);
	}
	
	public void changePerson(Person p) {
		notifyChange(p);
	}
	
	public void removePerson(Person p) {
		notifyDelete(p);
		persons.remove(p);
	}
}
