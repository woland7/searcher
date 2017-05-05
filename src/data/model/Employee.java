package data.model;

public class Employee {
	public Employee(String firstname, String lastname, String job) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.job = job;
		this.id=0;
	}

	public Employee(int id, String firstname, String lastname, String job) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.job = job;
		this.id = id;
	}	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String toString(){
		String state = "ID: " + id +
				" Firstname: " +firstname +
				" Lastname: " + lastname +
				" Job: " + job;
		return state;
	}
	private String firstname, lastname, job;
	private int id;

}
