package testing;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUtils {

	public static void printRSet(ResultSet rset, int colnum) throws SQLException {		
		while (rset.next()) {
			System.out.println("");
			for (int i = 1; i < colnum; i++){
				System.out.print("\t" + rset.getString(i));
			}
			
		}
	}
}
