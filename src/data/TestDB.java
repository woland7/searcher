package data;
import data.model.Employee;

public class TestDB {

	public static void main(String[] args) {
		DBInterfacer db = new DBInterfacer();
		db.clearTable();
		Employee empl = new Employee("Emiliya", "Hrabova", "Paid for doing nothing");
		db.insert(empl);
		empl = new Employee("Antonio", "De Iasio", "SEO");
		db.insert(empl);
		Employee emplRetr = db.getByID(1);
		System.out.println(emplRetr.toString());
		db.addColumn("pay", "int");

	}

}
