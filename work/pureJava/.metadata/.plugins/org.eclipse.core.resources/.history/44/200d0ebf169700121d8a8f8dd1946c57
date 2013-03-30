package testing;

import java.sql.*;


public class QueryTest {

	public static String driverName = "oracle.jdbc.driver.OracleDriver";
	public static String url	=	"jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Class drvClass = Class.forName(driverName);
		Connection con = DriverManager.getConnection(url, "home", "passw0rd");
		Statement stmt = con.createStatement();
		stmt.executeUpdate("insert into toffees values ('joe', 101)");


	}

}
