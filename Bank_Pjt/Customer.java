package Bank_Pjt; // first line of Java Code. Named Bank Project Folder

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer implements IBank {/* Applying Encapsulation using a private access modifier (so only available in this class)
	 
		 *  using getter & setter to access or modify fields in private access modifier
		 */ 
		public String Username; //declaring fields
		public String Password;  
		public int password;  
		private float accountBalance; 
		private String customerName;  
		private int customer_id;  
		private Scanner scanner;  
		public String clear = "";  
		public int totalAttempts = 3;
		private float balance;
		
		public Customer() { // creating a constructor with no parameters
			this((float) 0.00, 0000);
			}
		
		public Customer( float accountBalance, int customer_id) {// initializing the field values in a constructor with different parameters (overloading -Polymorphism)
				
			this.accountBalance = accountBalance; // key word this. specifies that the parameters above refers to the fields encapsulated above
			
			this.setCustomerNumber(customer_id);
			}
		
		
		public void Menu() { // Interface abstract method
			 char options = '0';
			 scanner = new Scanner(System.in);
			
			 System.out.println ("\t ____________________________________");
			 System.out.println ("\t|                                    |");
			 System.out.println ("\t|  Please select your transaction    |");
			 System.out.println ("\t|                                    |");
			 System.out.println ("\t|   A. Deposit                       |");
			 System.out.println ("\t|   B. Withdrawal                    |");
			 System.out.println ("\t|   C. Balance Enquiry               |");
			 System.out.println ("\t|   D. Log out                       |");
			 System.out.println ("\t|____________________________________|");
			 
			 do 
			  {
				 System.out.println ("\n");
				 System.out.println ("-----------------------------------");
				 System.out.println (" | What option do you choose ?   |");
				 System.out.println ("-----------------------------------");
				 options = scanner.next().charAt(0);
				 options = Character.toUpperCase(options);
				 
				 	switch (options)
				 	{
				 	case 'A':
				 		System.out.println (" Deposit option Selected");
				 		System.out.println ("\n");
						 methodA();
						 break;
						
				 	case 'B':
				 		System.out.println (" Withdrawal option Selected");
				 		System.out.println ("\n");
				 		 methodB();
						 break;
						
				 	case 'C':
				 		System.out.println (" Balance Enquiry option Selected");
				 		System.out.println ("\n");
				 		 methodC();
				 		 break;
				 		  
				 	case 'D':
				 		System.out.println ("=======================================");
			 			System.out.println ("|  You Successfully Logged out !!!    |");
			 			System.out.println ("=======================================\n");
			 			
				 		 break;
				 	default:
				 		 methodD();
				 		 break;
				 	}
			  } 
			 while (options != 'D');	 
			  }
			
			public void methodA() {		// constructor details to Case 'A' option
				
				
				 Connection conn = DB.getConnection();     
				 PreparedStatement stmt;
				 		
				    System.out.println(" Enter Customer ID:"); // enter user name
					int customer_id = scanner.nextInt();

					double balance = 0;
				 try {
						stmt = conn.prepareStatement( " SELECT accountbalance FROM Users WHERE customer_id = ?;");
						stmt.setInt(1, customer_id);
						 
						ResultSet rs = stmt.executeQuery();
											
						 
							while( rs.next()) { // getting the database previous balance from database
								balance= rs.getInt(1);
						}
							
						
					} catch (SQLException e) {
					
						e.printStackTrace();
					}
				
				
					 System.out.println (" Enter Deposit Amount  ");	//try catch statement to prevent runtime exceptions problem 
						double amount = scanner.nextDouble();
								
				while (true) {		
					
								
						try {
				
							if (Math.abs(amount) >= 0){  // condition to detect positive input amount
								accountBalance = (float) (balance + Math.abs(amount));
								
							try {
								stmt = conn.prepareStatement( " UPDATE users SET accountbalance = ? where customer_id = ?;");
							
								
								stmt.setFloat(1, accountBalance);
					            stmt.setInt(2, customer_id);
								 
					            stmt.executeUpdate();
								
								System.out.println ("New Deposit: $" + Math.abs(amount) + ".\rAvailable balance: $" + this.accountBalance + "\r");
								
							} catch (SQLException e) {
					            
					            e.printStackTrace();
							}
						}
							
								@SuppressWarnings("unused")
								String clear = scanner.nextLine();
							
							break;
			}
						catch (InputMismatchException e) { // catches the non numerical inputs and prints a message
					
							System.err.println("Not a valid input. Error :" + e.getMessage());
							@SuppressWarnings("unused")
							String clear = scanner.nextLine(); // clears the scanner buffer so the loop can restart preventing stack overflow of memory
							continue;	 // continue stops the loop and re-start from the beginning "Enter Deposit Amount'
							}
					}
							
				}	
			
				
			public void methodB() {		
				
				
				 Connection conn = DB.getConnection();     
				 PreparedStatement stmt;
				 		
				    System.out.println(" Enter Customer ID:"); // enter user name
					int customer_id = scanner.nextInt();

					double balance = 0;
				 try {
						stmt = conn.prepareStatement( " SELECT accountbalance FROM Users WHERE customer_id = ?;");
						stmt.setInt(1, customer_id);
						 
						ResultSet rs = stmt.executeQuery();
											
						 
							while( rs.next()) { // getting the database previous balance from database
								balance= rs.getInt(1);
						}
							
						
					} catch (SQLException e) {
					
						e.printStackTrace();
					}
				
				
					 System.out.println (" Enter Withdrawal Amount  ");	//try catch statement to prevent runtime exceptions problem 
						double wamount = scanner.nextDouble();
								
				while (true) {		
					
								
						try {
				
							
							accountBalance = (float) (balance - Math.abs(wamount)); 
							try {
								stmt = conn.prepareStatement( " UPDATE users SET accountbalance = ? where customer_id = ?;");
							
								
								stmt.setFloat(1, accountBalance);
					            stmt.setInt(2, customer_id);
								 
					            stmt.executeUpdate();
			
							} catch (SQLException e) {
					            
					            e.printStackTrace();
							}
												
								System.out.println ("New Deposit: $" + Math.abs(wamount) + ".\rAvailable balance: $" + this.accountBalance + "\r");
								
								@SuppressWarnings("unused")
								String clear = scanner.nextLine();
								
			    break;
				}catch (InputMismatchException e) { // catches the non numerical inputs and prints a message
					
							System.err.println("Not a valid input. Error :" + e.getMessage());
							@SuppressWarnings("unused")
							String clear = scanner.nextLine();	 // clears the scanner buffer so the loop can restart preventing stack overflow of memory
							continue;	 // continue stops the loop and re-start from the beginning "Enter Deposit Amount'
						   }
						}	
				}	
					
			public void methodC() {		
				Connection conn = DB.getConnection();     
				 PreparedStatement stmt;
				 		
				    System.out.println(" Enter Customer ID:"); // enter user name
					int customer_id = scanner.nextInt();

					double balance = 0;
				 try {
						stmt = conn.prepareStatement( " SELECT accountbalance FROM Users WHERE customer_id = ?;");
						stmt.setInt(1, customer_id);
						 
						ResultSet rs = stmt.executeQuery();
											
						 
							while( rs.next()) { // getting the database previous balance from database
								balance= rs.getInt(1);
						}
							
						
					} catch (SQLException e) {
					
						e.printStackTrace();
					}
				
				
								System.out.println ("Current Account Balance: $" + balance+ "\r");
						}
				
			public void methodD() {		
					System.err.println("Not a valid input. Error :\rPlease choose another option\r");
			}
	
				
		public void Credentials(){ // Interface abstract method
		
							System.out.println ("--------- WELCOME TO CUSTOMER PORTAL --------");
							System.out.println ("\n");
							validation();
								
			
				}
		
		@SuppressWarnings("resource")
		public void login() { // login request
			
			Scanner scanner; 
			@SuppressWarnings("unused")
			String clear = " ";  
			
			char check = '0';
			scanner = new Scanner(System.in);
		
			 do 
			  { 
				 System.out.println ("\t ________________________________");
				 System.out.println ("\t|                                |");
				 System.out.println ("\t|  Please select an option       |");
				 System.out.println ("\t|                                |");
				 System.out.println ("\t|   A. Log In                    |");
				 System.out.println ("\t|                                |");
				 System.out.println ("\t|   B. Log out                   |");
				 System.out.println ("\t|                                |");
				 System.out.println ("\t|________________________________|");
			 check = scanner.next().charAt(0);
			 check = Character.toUpperCase(check);
			 
			
			 	switch (check)
			 	{
			 	case 'A':
			 		Credentials();
					 break;
						
			 	case 'B':
			 		exit();
			 		 break;
			 	default:
			 		errorInput();
			 		 break;
			 	}
		  	} 
		 while (check != 'B'); 
		}
	  
		public void exit(){
					System.out.println ("=======================================");
					System.out.println ("|  You Successfully Logged out !!!    |");
					System.out.println ("=======================================\n");
		      }
		   public void errorInput() {
			   	
					System.err.println("Not a valid input. Error :\rPlease choose another option\r");
				}
	   		
		
public void validation() {
	
	while (totalAttempts != 0) {
		
							scanner = new Scanner(System.in);
						System.out.println(" Customer Username:"); // enter user name
						String userName = scanner.nextLine();
	
						System.out.print(" Customer Password:");//enter password
						String passWord = scanner.nextLine(); 
	
						Connection conn = DB.getConnection();     
						PreparedStatement stmt;
	 
					try {
						stmt = conn.prepareStatement("SELECT * FROM Users WHERE username =? AND password =?;"); // querying the user database
						stmt.setString(1, userName);
						stmt.setString(2, passWord);
		
						ResultSet rs = stmt.executeQuery();
							
						while( rs.next()) { // getting the database user name and password to various columns

							Username = rs.getString(2); 
					
							Password = rs.getString(3); 
						}  
				    
				} catch (SQLException e) {
									e.printStackTrace();
					}
					
						if (Username.equals(userName) && Password.equals(passWord)) {  // validating input info with database information for log in access
			 
								System.out.println ("\n");
								Menu();
								return;
						} else {
								System.err.println("Invalid input: Check Username or Password!");
								totalAttempts--;
								System.out.println ("\n");
							}
									}
 							if (totalAttempts == 0) {
 								System.err.println("Maximum number of attempts exceeded: Account Blocked!");
 							}
						}
	
		public int getCustomerNumber() {
			return customer_id;
		}

		public void setCustomerNumber(int customerNumber) {
			this.customer_id = customerNumber;
		}
		
		public void customer() {
		    login();
		}

		public float getBalance() {
			return balance;
		}

		public void setBalance(float balance) {
			this.balance = balance;
		}
		public double getAccountBalance() { // accessing Account Balance using getter 
			return accountBalance;
		}
		public String getCustomerName() { // accessing Customer Name using getter 
			return customerName;
		}

		public void setCustomerName(String customerName) {
				this.customerName = customerName;
		}		
		
}