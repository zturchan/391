package testing;

import java.sql.*;

import search.DBAccess;


public class QueryTest {


	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		ResultSet rset = DBAccess.getRecords("cat");
		TestUtils.printRSet(rset);

	}

}
