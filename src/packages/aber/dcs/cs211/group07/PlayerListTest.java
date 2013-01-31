	import java.io.*;
	import java.sql.*;
	import java.util.*;


	public class PlayerListTest{

	public static void main(String[] args) {
	/*
	 *
	 * Refuses to connect to the database server
	 * doesn't like it when I extend servlet, even after importing the relavent
	 * imports.
	 *
	 * Requires an external jar file to connect to the database.
	 * going to work on this tonight
	 *
	 *
	 *
	 *
	 */
		PrintWriter out;
		String host = "jdbc:mysql://74.53.183.226/handyman_monster";
		String uName = "handyman_group07";
		String uPass = "3213560921*+*";
		Statement statement = null;
		Connection connection=null;
		ResultSet rs = null;
		String userName = new String("");


		try{

		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = DriverManager.getConnection(host, uName, uPass);
		statement = connection.createStatement();

	if(statement.execute("select * from player")){

			rs = statement.getResultSet();
		} else {

			System.err.println("select failed");
		}

			while (rs.next()) {

			String entry = rs.getString(1);
			System.out.println(entry);
		}


		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException error){

			error.printStackTrace();


		}







	}

	}






