package Bank_Pjt;

import java.sql.Connection;
import java.sql.SQLException;

public class Driver {

	public static void main(String[] args) {
		
			Connection conn = DB.getConnection();
				try {
			System.out.println("Is connection Closed: " + conn.isClosed());
			System.out.println("DB Connection Test result: Connected!");
					} 
				catch (SQLException e) {
							e.printStackTrace();
					}

		
			System.out.println ("\n");
			System.out.println ("    |--------------------------------------------|");
			System.out.println ("    |                                            |");
			System.out.println ("    |   Welcome to Hitriton ATM Banking System   | ");
			System.out.println ("    |                                            |");
			System.out.println ("    |--------------------------------------------|\r");
	
	
			Admin drivers = new Admin ();
			drivers.start();
				}	 
	   

	}
	
