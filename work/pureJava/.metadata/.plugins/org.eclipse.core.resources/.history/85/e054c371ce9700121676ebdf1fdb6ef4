package testing;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUtils {

	public static void printRSet(ResultSet rset) throws SQLException {
		while (rset.next()) {
			System.out.println("ID: " + rset.getString(1) + " Name: "
					+ rset.getString(2) + " Description: " + rset.getString(3));
		}
	}
}
