package data.model;

public class Employee {
	public Employee(String firstName, String lastName, String job) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.job = job;
		this.ID=0;
	}

	public Employee(int ID, String firstName, String lastName, String job) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.job = job;
		this.ID = ID;
	}	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastname(String lastName) {
		this.lastName = lastName;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}

	public String toString(){
		String state = "ID: " + ID +
				" Firstname: " +firstName +
				" Lastname: " + lastName +
				" Job: " + job;
		return state;
	}
	private String firstName, lastName, job;
	private int ID;
}