package table.samples.model;

public class Person {
	private String name;
	private String address;
	private String other;
	private boolean active;
	
	public Person(String name) {
		this(name, "", "", true);
	}

	public Person(String name, String address, String other, boolean active) {
		super();
		this.name = name;
		this.address = address;
		this.other = other;
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
