package aber.dcs.cs211.group07.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Creates a connection to a database to access its tables and edit them
 * 
 * @author  Dan Cornwell
 *
 */

public class DatabaseConnector {

	//A statement from the connection, used to get result sets
	private Statement statement = null;
	
	//A result set for a specific table. Initialized before each method to get updated set
	private ResultSet resultSetTable1=null;
	
	//SQL string to tell the statement which table to get results from
	//MUST ENTER NAME EXACT NAME OF TABLE IN PLACE OF Table1
	private String SQLTable1= "SELECT * FROM Table1";
	
	//For multiple tables need extra result set and sql string
	//Change table2 appropriately
	private ResultSet resultSetTable2=null;
	private String SQLTable2= "SELECT * FROM Table2";
	
	public DatabaseConnector() {

		//Enter address of database being used
		String host = "database/address";
		//Enter name of the actual datbase
		String userName = "DBNAME";
		//Enter password for the database (unsure if necessary)
		String password = "admin";
		
		
		try {
			//Creates a connection to the database and a statement
			Connection connection = DriverManager.getConnection(host,userName,password);
			statement = connection.createStatement();
			
		}

		catch(SQLException error) {
			System.out.println( error.getMessage( ) );
		}
	}
	
	//WORKING
	//Returns a value in a column that corresponds to another column
	//In this case gets a password from a username
	public String getPassword(String userName) {
		
		try {
			resultSetTable1 = statement.executeQuery(SQLTable1);
			while(resultSetTable1.next()) {
				String email = resultSetTable1.getString(1); // enter column number or name in ()
				String userPassword = resultSetTable1.getString(2); // enter column number or name in ()
				
				if(email.equals(userName)) {
					

					return userPassword;
				}
			}
		} catch (SQLException error) {
			System.out.println( error.getMessage( ) );
		}
		
		return null;
		
	}
	
	//WORKING
	//Creates another row in the table
	public void createUser(String userName,String password) {
		try {
			resultSetTable1 = statement.executeQuery(SQLTable1);
			statement.executeUpdate("INSERT INTO MYTABELLE" + 
					" VALUES ('"+userName+"','"+password+"')");
		} catch (SQLException error) {
			System.out.println( error.getMessage( ) );
		}
	}
	
	//WORKING
	//Deletes a row in the table
	public void deleteUser(String userName) {
		
		try {
			resultSetTable1 = statement.executeQuery(SQLTable1);
			while(resultSetTable1.next()) {
				String email = resultSetTable1.getString(1);
				// enter column number or name in ()
				if(email.equals(userName)) {
					statement.executeUpdate("DELETE FROM MYTABELLE" +
							" WHERE USERNAME='"+email+"'"	);
					break;
				}
			}
		} catch (SQLException error) {
			System.out.println( error.getMessage( ) );
		}
	}
	
	//WORKING
	//Edits a value of a row in the table
	public void editPassword(String userName, String newPassword) {
		
		try {
			resultSetTable1 = statement.executeQuery(SQLTable1);
			while(resultSetTable1.next()) {
				String email = resultSetTable1.getString(1);
				// enter column number or name in ()
				if(email.equals(userName)) {
				
					String sql = "UPDATE MYTABELLE SET PASSWORD='"+newPassword+
							"' WHERE USERNAME='"+email+"'";
					statement.executeUpdate(sql);
					// enter column number or name in ()
					break;
				}
			}
		} catch (SQLException error) {
			System.out.println( error.getMessage( ) );
		}
	}

}




