package Bank_Pjt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Admin implements IBank{
	private String customerName;  
	public String Username; 
	public String Password; 
	private float accountBalance;  
	private String accountNumber; 
	private int customer_id;  
	private String AdminName;  
	public int totalAttempts = 3;
	
	private Scanner scanner;  
	public String clear = ""; 
	public Customer c = new Customer();//creating Clear field/ variable
	
	public Admin() { // creating a constructor with default balance parameters
		this((float) 0.00, null, null);
	}
	
	public Admin( float accountBalance, String accountNumber, String AdminName) // initializing the field values in a constructor with different parameters (overloading -polymorphism)
			 {
		this.accountBalance = accountBalance; // key word this. specifies that the parameters above refers to the fields encapsulated above
		this.setAccountNumber(accountNumber);
		this.AdminName = AdminName;
		
	}
	
	public void Deposit(double D_amount) { //deposit amount formula using a void method (no return value)
		this.accountBalance += D_amount;
		System.out.println ("New Deposit: $" + D_amount + ".\rAvailable balance: $" + this.accountBalance + "\r");
	}
	
	public void Withdrawal(double W_amount) { //  withdrawal amount formula using void method (no return value)
		
		if (this.accountBalance - W_amount < 0) {
			System.out.println ("Insuffucient Funds, \rPlease check your account balance \r");
		}
		else {
			this.accountBalance -= W_amount;
			System.out.println ("New withdrawal: $" + W_amount +"\rAvailable Balance: $" + this.accountBalance + "\r" );
		}
	}
	
	public void Menu() { // Interface abstract method
		 char options = '0';
		 scanner = new Scanner(System.in);
		 
		 System.out.println ("******************************************");
		 System.out.println ("**\tPlease select your transaction  **"); // transaction options
		 System.out.println ("**                                      **");
		 System.out.println ("**\tA. ADD New Customer             **");
		 System.out.println ("**\tB. Customer's Account Info      **");
		 System.out.println ("**\tC. DELETE Customer              **");
		 System.out.println ("**\tD. Log out                      **");
		 System.out.println ("******************************************");
		 
		 do 
		  {
			 System.out.println ("----------------------------------");
			 System.out.println ("| Which option did you choose ?  |"); // option statement
			 System.out.println ("----------------------------------");
			 options = scanner.next().charAt(0);
			 options = Character.toUpperCase(options);
			 
			 
			 	switch (options) // switch statement
			 	{
			 	case 'A':
			 		System.out.println ("New Customer option Selected");
			 		System.out.println ("\n");
					 methodA();
					 break;
					
			 	case 'B':
			 		System.out.println (" Customer Account's Informations option Selected");
			 		System.out.println ("\n");
			 		 methodB();
					 break;
					
			 	case 'C':
			 		System.out.println (" DELETE Customer option Selected");
			 		System.out.println ("\n");
			 		 methodC();
			 		 break;
			 		 
			 	case 'D':
			 		System.out.println ("=======================================");
		 			System.out.println ("| You've Successfully Logged out !!   |");
		 			System.out.println ("=======================================\n");
		 			
			 		 break;
			 	default:
			 		 methodD();
			 		 break;
			 	}
		    } 
		 while (options != 'D');
	        }
	
	public double getAccountBalance() {// accessing Account Balance using getter
				return accountBalance;
			}
	
			public String getCustomerName() {
				return AdminName;
			}
	
	public void setCustomerName(String customerName) {
					this.AdminName = customerName;
			}		
	public void methodA() {		// inserting new customer in database
						

		scanner = new Scanner(System.in);
		System.out.println ("\n");
		
		System.out.println("\tEnter your Name: ");
		this.customerName = scanner.nextLine();
		
		System.out.println("\tEnter your user name:");
		this.Username= scanner.nextLine();
		
		System.out.println("\tEnter your password:");
		this.Password= scanner.nextLine();
		
		System.out.println("\tEnter Deposit Amount:");
		this.accountBalance= scanner.nextFloat();
		
         Connection conn = DB.getConnection();     
        
         String query = "INSERT INTO Users(customername, username, password,accountBalance) VALUES (?,?,?,?)";
        
         try {
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setString(1, customerName);
            stmt.setString(2, Username);
            stmt.setString(3, Password);
            stmt.setFloat(4, accountBalance);
            
            stmt.executeUpdate();
            
         } catch (SQLException e) {
	            
	            e.printStackTrace();
	        }
         
        
     	try {
     		 PreparedStatement stmt2 = conn.prepareStatement( "SELECT customer_id FROM Users WHERE password = Password order by customer_id desc limit 1"); // querying the user database
     		 ResultSet rs = stmt2.executeQuery();
     							
     			while( rs.next()) { // getting the database user name and password to various columns

     				    Password = rs.getString(1); 
     			
     		}
     	} catch (SQLException e) {
     	
     		e.printStackTrace();
     	}
     			
         
		System.err.println("\rCONGRATULATIONS " + this.customerName + "\rYour Customer ID: " + Password);
		
			}
	public void methodB() {		
		
		
	    try {
				Connection conn = DB.getConnection();
				
				Statement	s = conn.createStatement();
				
				ResultSet rs = s.executeQuery("select * from Users;");
				
			
				
				while(rs.next()) {
			
							rs.getInt(1); //get the first column (id)
							
							rs.getString(2); //get the second column (name)
							rs.getString(3);
							rs.getString(4);//etc
							rs.getFloat(5);
							
		
			    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");	
			    System.out.println("Customer ID: "+rs.getInt(1)+" | User Name: "+rs.getString(2)+"| Password: "+rs.getString(3)+"| Customer Name: "+rs.getString(4)+"|Account Balance: "+rs.getFloat(5)+"\r");
					
				}
	
	}catch (SQLException e) {
							e.printStackTrace();
								}
					}
				

			
	public void methodC() {		
		System.out.println("\tEnter Customer ID:");
		this.customer_id= scanner.nextInt();
		
	
         Connection conn = DB.getConnection();     
        
         String query = ("delete from Users where customer_id = ?");
        
         try {
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setInt(1, this.customer_id);
           
            stmt.executeUpdate();
            
         } catch (SQLException e) {
	            
	            e.printStackTrace();
	        }
         
        
     	try {
     		 PreparedStatement stmt2 = conn.prepareStatement( "SELECT customer_id FROM Users WHERE password = Password order by customer_id desc limit 1"); // querying the user database
     		 ResultSet rs = stmt2.executeQuery();
     							
     			while( rs.next()) { // getting the database user name and password to various columns

     				    Password = rs.getString(1); 
     			
     		}
     	} catch (SQLException e) {
     	
     		e.printStackTrace();
     	}
     			
         
		System.err.println("\rCONGRATULATIONS Customer ID: " + Password + " was successfully DELETED");
		
			}
	
	public void methodD() {		
				System.err.println("Not a valid input. Error :\rPlease choose another option\r");
			}
			
		
	public void Credentials(){
		
		while (totalAttempts != 0) {
			
				scanner = new Scanner(System.in);
			System.out.println(" Administrator Username:"); // enter user name
			String userName = scanner.nextLine();
			
			System.out.print(" Administrator Password:");//enter password
			String passWord = scanner.nextLine(); 
			
			 Connection conn = DB.getConnection();     
			 PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement( "SELECT * FROM Users WHERE username = 'Administrator' AND password = 'administrator'"); // validating the input information
				 ResultSet rs = stmt.executeQuery();
									
					while( rs.next()) { // getting and storing the database user name and password to various column checking
	
							Username = rs.getString(2); 
							
						    Password = rs.getString(3); 
					
				}
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
							
				      if (Username.equals(userName) && Password.equals(passWord)) {  // validating input info with database information for log in access
					 
				    	  System.out.println ("\n");
				    	  Menu();  // administrator Menu options 
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

 	public void administrator() {
	 
 				System.out.println ("-------- WELCOME TO ADMIN PORTAL ---------");
 				System.out.println ("\n");
 				Credentials();
 			}
	
	@SuppressWarnings("resource")
	public void start() { // program starting point
		
			Scanner scanner; 
			@SuppressWarnings("unused")
			String clear = " ";  
			
			char check = '0';
			scanner = new Scanner(System.in);
		
		 do 
		  {
			 System.out.println ("\t _____________________________________");
			 System.out.println ("\t|                                     |");
			 System.out.println ("\t|  Please select an option            |");
			 System.out.println ("\t|                                     |");
			 System.out.println ("\t|    A. Administrator Account         |");
			 System.out.println ("\t|    B. Customer Account              |");
			 System.out.println ("\t|    C. Exit                          |");
			 System.out.println ("\t|                                     |");
			 System.out.println ("\t|_____________________________________|");
			 check = scanner.next().charAt(0);
			 check = Character.toUpperCase(check);
			 
			 
			 	switch (check)
			 	{
			 	case 'A':
			 		administrator();
					 break;
					
			 	case 'B':
			 		c.customer();
			 	
					 break;
					
			 	case 'C':
			 		exit();
		 			
			 		 break;
			 	default:
			 		errorInput();
			 		 break;
			 	}
		  	} 
		 while (check != 'C');
			}
	  
		public void exit(){
		    	  	System.out.println ("_____________________________________________________");
		 			System.out.println ("|                                                   |");
		 			System.out.println ("|               GoodBye !!!                         |");
		 			System.out.println ("|                                                   |");
		 			System.out.println ("|  \u00402019 Hitriton Bank. All Rights Reserved.        |");
		 			System.out.println ("|           Federally Insured by NCUA.              |");
		 			System.out.println ("|                                                   |");
		 			System.out.println ("|___________________________________________________|");
		      }
		   public void errorInput() {
			   	
					System.err.println("Not a valid input. Error :\rPlease choose another option\n");
				}

		public String getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}


}
