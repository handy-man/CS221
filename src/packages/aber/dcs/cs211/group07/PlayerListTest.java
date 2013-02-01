package aber.dcs.cs211.group07;

import java.sql.*;

import aber.dcs.cs211.group07.db.TableConnector;

public class PlayerListTest {
	public static void main(String[] args) {
		TableConnector db = new TableConnector();

		try {
			db.statement.execute("select * from player");
			ResultSet rs = db.statement.getResultSet();
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			TableConnector.complain(e, "Could not fetch players");
		}		
	}
}






