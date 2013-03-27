package search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess {

	public static String driverName = "oracle.jdbc.driver.OracleDriver";
	public static String url = "jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";

	public static ResultSet getRecords(String searchTerm) throws SQLException,
			ClassNotFoundException {

		// connect to our db
		Class drvClass = Class.forName(driverName);
		Connection con = DriverManager.getConnection(url, "home", "passw0rd");
		Statement stmt = con.createStatement();
		ResultSet rset = stmt
				.executeQuery(" SELECT score(0), itemName, description " +
						"FROM testIndexTable WHERE contains(description, '" + searchTerm + "', 0) > 0" +
						" order by score(0) desc");

		return rset;

	}

}
