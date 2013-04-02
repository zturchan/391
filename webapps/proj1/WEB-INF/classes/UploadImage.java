/***
 *  A sample program to demonstrate how to use servlet to 
 *  load an image file from the client disk via a web browser
 *  and insert the image into a table in Oracle DB.
 *  
 *  Copyright 2005 COMPUT 391 Team, CS, UofA                             
 *  Author:  Fan Deng
 *                                                                  
 *  Licensed under the Apache License, Version 2.0 (the "License");         
 *  you may not use this file except in compliance with the License.        
 *  You may obtain a copy of the License at                                 
 *      http://www.apache.org/licenses/LICENSE-2.0                          
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 *  the table shall be created using the following
      CREATE TABLE pictures (
            pic_id int,
	    pic_desc  varchar(100),
	    pic  BLOB,
	    primary key(pic_id)
      );
 *
 *  One may also need to create a sequence using the following 
 *  SQL statement to automatically generate a unique pic_id:
 *
 *   CREATE SEQUENCE pic_id_sequence;
 *
 ***/

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;
import oracle.sql.*;
import oracle.jdbc.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


/**
 *  The package commons-fileupload-1.0.jar is downloaded from 
 *         http://jakarta.apache.org/commons/fileupload/ 
 *  and it has to be put under WEB-INF/lib/ directory in your servlet context.
 *  One shall also modify the CLASSPATH to include this jar file.
 */
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

public class UploadImage extends HttpServlet {
	public String response_message;
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {



		//  change the following parameters to connect to the oracle database
		String username = "zturchan";
		String password = "Pikachu1";
		String drivername = "oracle.jdbc.driver.OracleDriver";
		String dbstring ="jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";


		try {
			//Parse the HTTP request to get the image stream
			DiskFileUpload fu = new DiskFileUpload();
			List FileItems = fu.parseRequest(request);
			int recordID = 0;
			// Process the uploaded items
			Iterator i = FileItems.iterator();
			FileItem item = (FileItem) i.next();
			while (i.hasNext() && item.isFormField()) {
				if(item.getFieldName().equals("dropdownID"))
					recordID = Integer.parseInt(item.getString());
				item = (FileItem) i.next();
			}
			//Get the image stream
			InputStream instream = item.getInputStream();

			BufferedImage img = ImageIO.read(instream);
			BufferedImage thumbnailimg = shrink(img, 10);	
			BufferedImage fullimg = enlarge(img, 3);



			//Connect to the database and create a statement
			Connection conn = getConnected(drivername,dbstring, username,password);
			Statement stmt = conn.createStatement();


			//First, to generate a unique pic_id using an SQL sequence
			ResultSet rset1 = stmt.executeQuery("SELECT pic_id_sequence.nextval from dual");
			rset1.next();
			int pic_id = rset1.getInt(1);

			//Insert an empty blob into the table first. Note that you have to
			//use the Oracle specific function empty_blob() to create an empty blob
			String cmd = "INSERT INTO pacs_images VALUES(?, ?, empty_blob(), empty_blob(), empty_blob())";
			PreparedStatement command = conn.prepareStatement(cmd);
			command.setInt(1, recordID);
			command.setInt(2, pic_id);
			command.executeQuery();


			//to retrieve the lob_locator 
			//Note that you must use "FOR UPDATE" in the select statement
			cmd = "SELECT * FROM pacs_images WHERE record_id = ? and image_id = ? FOR UPDATE";
			command = conn.prepareStatement(cmd);
			command.setInt(1, recordID);
			command.setInt(2, pic_id);	
			ResultSet rset = command.executeQuery();

			rset.next();
			BLOB thumbnailBlob = ((OracleResultSet)rset).getBLOB(3);
			BLOB regularBlob = ((OracleResultSet)rset).getBLOB(4);
			BLOB fullBlob = ((OracleResultSet)rset).getBLOB(5);


			// Write the image to the blob object
			OutputStream thumbnail = thumbnailBlob.getBinaryOutputStream();
			ImageIO.write(thumbnailimg, "jpg", thumbnail);

			OutputStream regular = regularBlob.getBinaryOutputStream();
			ImageIO.write(img, "jpg", regular);

			OutputStream full = fullBlob.getBinaryOutputStream();
			ImageIO.write(fullimg, "jpg", full);
			instream.close();
			thumbnail.close();
			regular.close();
			full.close();


			stmt.executeUpdate("commit");
			conn.close();
			javax.swing.JOptionPane.showMessageDialog(null, "Upload Successful");
			response.sendRedirect("upload.jsp");

		} catch( Exception ex ) {
			//System.out.println( ex.getMessage());
			response_message = ex.getMessage();
		}

	}




	//   To connect to the specified database

	private static Connection getConnected( String drivername,
			String dbstring,
			String username, 
			String password  ) 
					throws Exception {
		Class drvClass = Class.forName(drivername); 
		DriverManager.registerDriver((Driver) drvClass.newInstance());
		return( DriverManager.getConnection(dbstring,username,password));
	} 

	public static BufferedImage shrink(BufferedImage image, int n) {

		int w = image.getWidth() / n;
		int h = image.getHeight() / n;

		BufferedImage shrunkImage =
				new BufferedImage(w, h, image.getType());

		for (int y=0; y < h; ++y)
			for (int x=0; x < w; ++x)
				shrunkImage.setRGB(x, y, image.getRGB(x*n, y*n));

		return shrunkImage;
	}	

	public static BufferedImage enlarge(BufferedImage image, int n) {

		int w = n * image.getWidth();
		int h = n * image.getHeight();

		BufferedImage enlargedImage =
				new BufferedImage(w, h, image.getType());

		for (int y=0; y < h; ++y)
			for (int x=0; x < w; ++x)
				enlargedImage.setRGB(x, y, image.getRGB(x/n, y/n));

		return enlargedImage;
	}



}
