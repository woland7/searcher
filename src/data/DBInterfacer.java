package data;
//STEP 1. Import required packages
import java.sql.*;
import java.util.ArrayList;

import data.model.City;

public class DBInterfacer {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   //cambiare il nome del db e della tabella
   private String dbname = "world_new";
   private String tableName = "city";
   private String DB_URL = "jdbc:mysql://localhost/" + dbname;

   //  Database credentials
   private String USER = "root";
   private String PASS = "root";
   
   private Connection conn = null;
   private Statement stmt = null;
   private String sql;
   public DBInterfacer(){
	   openConnection();
   }
   public DBInterfacer(String dbname){
	  this.dbname = dbname;
	  
	  openConnection();
   }
   
   public DBInterfacer(String dbname, String user, String pass){
	   this.dbname = dbname;
	   this.USER = user;
	   this.PASS = pass;
	   
	   openConnection();
   }
   
   public void openConnection(){
      try {
    	//Register JDBC driver
		Class.forName("com.mysql.jdbc.Driver");
	    //Open a connection
	    System.out.println("Connecting to database...");
	    conn = DriverManager.getConnection(DB_URL,USER,PASS);
	    stmt = conn.createStatement();
      } catch (ClassNotFoundException e) {
		e.printStackTrace();
      } catch (SQLException e) {
		e.printStackTrace();
      }
   }
   
   public ArrayList<City> getAllRecords(){
	   ArrayList<City> cities = new ArrayList<>();
	   sql = "SELECT * FROM " + tableName;
	   ResultSet rs = null;
	   try {
		 rs = stmt.executeQuery(sql);
		 while(rs.next()){
			   //Retrieve by column name
			   int id  = rs.getInt("ID");
			   String name = rs.getString("Name");
			   String ccode = rs.getString("CountryCode");
			   String dist = rs.getString("District");
			   int population = rs.getInt("Population");
			   cities.add(new City(id, name, ccode, dist, population));
		 }
	   } catch (SQLException e) { e.printStackTrace();}
	   //printResults(allEmpl);
	   return cities;
   }
   
   public boolean exists(int ID){
	   if(getByID(ID)==null) return false;
	   else return true;
   }
   
   public City getByID(int e_id){
	   System.out.println("Processing query...");
	   City city = null;
	   try {
		   sql = "SELECT * FROM "+ tableName + " WHERE ID = " + e_id;
		   ResultSet rs = stmt.executeQuery(sql);
		   //true if exists
		   if (rs.first()){ 
			   System.out.println("Result set not null");
			   String name = rs.getString("Name");
			   String ccode = rs.getString("CountryCode");
			   String dist = rs.getString("District");
			   int population = rs.getInt("Population");
		       //create city
		       city = new City(e_id, name, ccode, dist, population);
		   }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   System.out.println(city.toString());
	   return city;
   }
   
   public void insert(City c){
	   System.out.println("insert :" + c.toString());
	   sql = "INSERT INTO "+tableName 
	   		+ " (Name, CountryCode , District, Population) VALUES "
	   		+ "('"+c.getName()+"', '"+c.getCountryCode()+"', '" + c.getDistrict() + "', '"+ c.getPopulation()+"')";
	   System.out.println(sql);
	   try {
		stmt.executeUpdate(sql);
	   } catch (SQLException e1) { e1.printStackTrace(); }
   }
   
   public void deleteById(int id){
	   sql = "DELETE FROM "+tableName+" WHERE id =" + id;
	   try {
		stmt.execute(sql);
		} catch (SQLException e) {e.printStackTrace(); }
   }
   /**
    * 
    * @param colName the collumn name to add to the table
    * @param type example VARCHAR(120)
    */
   public void addColumn(String colName, String type){
	   sql = "ALTER TABLE "+tableName+" ADD COLUMN "+colName+ " " + type;
	   try {
		stmt.execute(sql);
		} catch (SQLException e) {e.printStackTrace(); }
   }
   
   public void clearTable(){
	   sql = "Truncate table " + tableName;
	   try {
		stmt.execute(sql);
		} catch (SQLException e) {e.printStackTrace(); }
   }
   
   public void closeDB(){
	    try {
			stmt.close();
			conn.close();
			System.out.println("Goodbye!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
   }
   
   public void printResults(ArrayList<City> list){ 
	  String record = "";
	  if(list==null){
		  System.out.println("empty return list");
	  }else{
		  for(City c: list){
		      record = c.toString() + "\n";
		      System.out.println(record);
		  }
	  }
   }  
}