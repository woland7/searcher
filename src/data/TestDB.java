package data;
import data.model.City;;

public class TestDB {

	public static void main(String[] args) {
		DBInterfacer db = new DBInterfacer();

		City city = db.getByID(1234);
		db.getAllRecords();
		//System.out.println(city.toString());
		
	}

}
