package data;
//STEP 1. Import required packages
import java.sql.*;
import java.util.ArrayList;

import data.model.Employee;

public class DBInterfacer {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   //cambiare il nome del db e della tabella
   private String dbname = "test_db";
   private String tableName = "Employee_ext";
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
   public ArrayList<Employee> getAllRecords(){
	   ArrayList<Employee> allEmpl = new ArrayList<>();
	   sql = "SELECT * FROM " + tableName;
	   ResultSet rs = null;
	   try {
		 rs = stmt.executeQuery(sql);
		 while(rs.next()){
			   //Retrieve by column name
			   int id  = rs.getInt("id");
			   String first = rs.getString("firstname");
			   String last = rs.getString("lastname");
			   String job = rs.getString("job");
			   allEmpl.add(new Employee(id, first, last, job));
		 }
	   } catch (SQLException e) { e.printStackTrace();}
	   //printResults(allEmpl);
	   return allEmpl;
   }
   
   public Employee getByID(int e_id){
	   //System.out.println("Processing query...");
	   Employee employee = null;
	   try {
		   stmt = conn.createStatement();
		   sql = "SELECT * FROM "+ tableName + " WHERE id = " + e_id;
		   ResultSet rs = stmt.executeQuery(sql);
		   //true if exists
		   if (rs.first()){  
		       String first = rs.getString("firstname");
		       String last = rs.getString("lastname");
		       String job = rs.getString("job");
		       //create employee
		       employee = new Employee(e_id, first, last, job);
		   }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   return employee;
   }
   public void insert(Employee e){
	   System.out.println(e.getFirstname() + e.getLastname() + e.getJob());
	   sql = "INSERT INTO "+tableName 
	   		+ " (firstname, lastname, job) VALUES ('"+e.getFirstname()+"', '"+e.getLastname()+"', '"+ e.getJob()+"')";
	   System.out.println(sql);
	   try {
		stmt.executeUpdate(sql);
	   } catch (SQLException e1) { e1.printStackTrace(); }
   }
   public void delete(int id){
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
   
   public void printResults(ArrayList<Employee> list){ 
	  String record = "";
	  if(list==null){
		  System.out.println("empty return list");
	  }else{
		  for(Employee e: list){
		      record = "ID: " + e.getId() + "First: " + e.getFirstname() + ", Last: " + e.getLastname()+ ", Job: " + e.getJob();
		      System.out.println(record);
		  }
	  }
   }
   
}//end DBInterface
