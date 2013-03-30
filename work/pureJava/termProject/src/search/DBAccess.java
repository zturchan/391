package search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class DBAccess {

	public static String driverName = "oracle.jdbc.driver.OracleDriver";
	public static String url = "jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";

	/**
	 * 
	 * @param searchTerm
	 * @param rankSort
	 * 			boolean to see if we need to rank or not, default is by date
	 * @return
	 * 			sorted arraylist full of records
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<Record> getRecords(String searchTerm, boolean rankSort) throws SQLException,
			ClassNotFoundException {

		// connect to our db
		//Class drvClass = Class.forName(driverName);
		Connection con = DriverManager.getConnection(url, "home", "passw0rd");
		Statement stmt = con.createStatement();
		//big query to get back
		ResultSet rset = stmt
				.executeQuery("SELECT score(1), score(2), score(3), record_id, patient_name, " +
						"doctor_name, radiologist_name, test_type, prescribing_date, test_date, diagnosis, description" +
						" FROM radiology_record WHERE contains(patient_name, '"+searchTerm+"', 1) > 0 or " +
						"contains(diagnosis, '"+searchTerm+"', 2) > 0 or " +
						"contains(description, '"+searchTerm+"', 3) > 0 order by test_date desc");
		//now convert to records
		ArrayList<Record> records = new ArrayList<Record>();
		while(rset.next()){
			Record r = new Record(rset);
			records.add(r);
		}
		//sort by rank if needed
		if(rankSort){
			Collections.sort(records);
		}
		return records;

	}

}
