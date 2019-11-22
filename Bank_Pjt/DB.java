package Bank_Pjt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class DB {

	
	private static Connection conn = null; //Declare connection object
	
	private DB() {
		Properties properties = new Properties(); //Create constructor that connects to database if called
        try {
			properties.load(new FileReader("application.properties")); // reading from an external specific file properties
		} catch (FileNotFoundException e1) {
		
			e1.printStackTrace();
		} catch (IOException e1) {
		
			e1.printStackTrace();
		}
        String url = properties.getProperty("url");
        String un = properties.getProperty("un");
        String pwd = properties.getProperty("pwd");
        
        try {
        	
            conn = DriverManager.getConnection(url, un, pwd); // reading info from file

		} catch (SQLException e) {
			System.out.println(e.getMessage()); //System exits after printing stack trace
			
			System.exit(0);
		}
	}

	public static Connection getConnection(){
		
		if (conn == null) { //Instantiates DB connection if it does not exist, otherwise returns existing connection
			new DB();
			return conn;
		}
		return conn;
	}
}
