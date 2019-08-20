import java.sql.*;
import java.util.*;
public class bookstoreProgram {

	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore" , "root" , "titP4Root10meg" ); //Allocate a database 'Connection' object
				Statement stmt = conn.createStatement();) { //Allocate a 'Statement' object in the Connection
				
				Scanner input = new Scanner(System.in);
				System.out.println("1. Enter book"); //user menu
				System.out.println("2. Update book");
				System.out.println("3. Delete book");
				System.out.println("4. Search books");
				System.out.println("0. Exit");
				System.out.println("Please enter a number:");
        
				int choice = input.nextInt(); //Get user's choice
				
				switch (choice) {
		        case 1: //if user enters 1
		            
		        	String newID = "select max(id) from books"; //get the maximum ID from books table 
		        	
		        	Scanner inputTitle = new Scanner(System.in);
		        	System.out.println("Please enter the title of the book: "); //user enters title
		        	String title = inputTitle.nextLine();
		        	
		        	Scanner inputAuthor = new Scanner(System.in);
		        	System.out.println("Please enter the author of the book: "); //user enters author
		        	String author = inputAuthor.nextLine();
		        	
		        	Scanner inputQty = new Scanner(System.in);
		        	System.out.println("Please enter the quantity of the book: "); //user enters quantity
		        	int qty = inputQty.nextInt();
		        	
		        	//enter into books table 
		        	String sqlInsert = "insert into books " + "values (" + (newID+1) + title + author + qty + ")";
					int countInserted = stmt.executeUpdate(sqlInsert); //execute the update
					System.out.println(countInserted + " records inserted.\n");
		        	
		            break;
		        case 2: //if user enters 2
		        	Scanner inputUpdate = new Scanner(System.in);
		        	System.out.println("Please enter what you would like to update: "); //user enters what he/she would like to update
		        	System.out.println("1. Title"); //menu
					System.out.println("2. Author");
					System.out.println("3. Quantity");
					System.out.println("Enter a number:");
					int choiceUpdate = inputUpdate.nextInt(); //Get user's choice
					
					if(choiceUpdate == 1) { //first option
						Scanner inputOldTitle = new Scanner(System.in);
			        	System.out.println("Please enter the old title: "); //user enters old title
			        	String oldTitle = inputOldTitle.nextLine();
						
						Scanner inputNewTitle = new Scanner(System.in);
			        	System.out.println("Please enter the new title: "); //user enters new title
			        	String newTitle = inputNewTitle.nextLine();
					
			        	//enter into sql books table
						String strUpdate = "update books set title =" + newTitle + "where title =" + oldTitle; 
						int countUpdated = stmt.executeUpdate( strUpdate ); //execute the update
						System.out.println(countUpdated + " records affected.");
						
						inputOldTitle.close(); 
						inputNewTitle.close(); 
					}
					if(choiceUpdate == 2) { //second option
						Scanner inputOldAuthor = new Scanner(System.in);
			        	System.out.println("Please enter the old author: "); //user enters old author
			        	String oldAuthor = inputOldAuthor.nextLine();
						
						Scanner inputNewAuthor = new Scanner(System.in);
			        	System.out.println("Please enter the new author: "); //user enters new author
			        	String newAuthor = inputNewAuthor.nextLine();
					
						String strUpdate = "update books set author =" + newAuthor + "where author =" + oldAuthor;
						int countUpdated = stmt.executeUpdate(strUpdate); //execute the update
						System.out.println(countUpdated + " records affected.");
						
						inputOldAuthor.close(); 
						inputNewAuthor.close(); 
					}
					if(choiceUpdate == 3) { //option 3
						Scanner inputTitle1 = new Scanner(System.in);
			        	System.out.println("Please enter the title of the book: "); //user enters title
			        	String titleQty = inputTitle1.nextLine();
						
						Scanner inputNewQty = new Scanner(System.in);
			        	System.out.println("Please enter the new quantity: "); //user enters new quantity
			        	String newQty = inputNewQty.nextLine();
					
						String strUpdate = "update books set qty =" + newQty + "where title =" + titleQty;
						int countUpdated = stmt.executeUpdate( strUpdate ); //execute update
						System.out.println(countUpdated + " records affected.");
						
						inputTitle1.close(); 
						inputNewQty.close();
					}
		            break;
		        case 3: //if user enters 3
		        	Scanner inputBook1 = new Scanner(System.in);
		        	System.out.println("Please enter the title of the book you wish to delete: "); //user enters title
		        	String book1 = inputBook1.nextLine();
		        	
		        	//delete from sql books table
		        	String sqlDelete = "delete from books where title =" + book1;
					int countDeleted = stmt.executeUpdate(sqlDelete); //execute update
					System.out.println (countDeleted + " records deleted.\n");
		            
		            break;
		        case 4: //if user enters 4
		        	Scanner inputBook2 = new Scanner(System.in);
		        	System.out.println("Please enter the title of the book you wish to search: "); //user enters title
		        	String book2 = inputBook2.nextLine();
		        	
		        	//search in sgl books table for title
		        	String strSelect = "select * from books where title =" + book2;
					ResultSet rset = stmt.executeQuery(strSelect);
					System.out.println (rset + " record search.\n");
		        	
		            break;
		            
		        case 0: //if user enters 0, program will stop
			        System.exit(0);
			        break;
		            
				}input.close(); 
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
}

		

	


	