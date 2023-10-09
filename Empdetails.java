package jdbcproject.jdbcproject12;


import java.sql.*;        //we can import some packages like sql & util(with scanner class used for user input) which is needed for that program
import java.util.Scanner;

public class Empdetails  //class name
{
	
	public static void main( String[] args ) //Main method
    {
      try //try-catch block is used
      {
    	  //load and register the driver
    	Class.forName("com.mysql.cj.jdbc.Driver"); 
    	
    	//establish the connection
    	//url, username, password
    	String url = "jdbc:mysql://localhost:3306/Company1";
    	String username="root";
    	String password="abcd";
    	
    	// in this we can create a database connection
    	Connection connection=DriverManager.getConnection(url, username, password);
    	
    	//we can create a Scanner for user input
    	Scanner scanner = new Scanner(System.in);
    	
    	
    	//we can display all the menu options by using a loop
        
        while(true) //we use while condition for this to make all that condition display
    	{
    	    System.out.println("Choose an operation we want to be performed:");//it prints that choose your choice
    	    System.out.println("1. Insert the data"); //it prints that Insert the data
    	    System.out.println("2. Update the data"); //it prints that update the data
    	    System.out.println("3. Delete the data"); //it prints that delete the data
    	    System.out.println("4. Select the data"); //it prints that select the data
    	    System.out.println("5. Exit from the process"); //it prints that exit from the process
    	    int choice = scanner.nextInt(); //it prints all choice one by one with next line
    	
    	    //in this we can use switch -case statement for particular choice
    	    switch (choice)
    	    {
            case 1: //in this we can give the choice of insert operation
                insertEmployees(connection, scanner);
                break;
            case 2: //in this we can give the choice of update operation
                updateEmployees(connection, scanner);
                break;
            case 3: //in this we can give the choice of delete operation
                deleteEmployees(connection, scanner);
                break;
            case 4:
                selectEmployees(connection); //in this we can give the choice of select all info from our table operation
                break;
            case 5:
            	scanner.close();  //close the program
                connection.close();  //it used to close the connection
                return;
            default:
                System.out.println("Invalid choice.Please do the proper choice.");
                break;
           }
       }
    	
      }
      catch (Exception e) //catch block is used to handle the exception by using parent exception
      {
          e.printStackTrace(); //it used to handle exception 
      }
  }
  
//we can use a private static method for insert employee details with connection and scanner for reading user input & this throws sqlexceptiontype
	
private static void insertEmployees(Connection connection, Scanner scanner) throws SQLException
{
     //it can accept the user input for employee details
    System.out.print("Enter Employee ID: "); //it take the empid
    int eid = scanner.nextInt(); //it take int type
    scanner.nextLine(); 
    
    System.out.print("Enter Employee Name: "); //it take the empname
    String ename = scanner.nextLine();   //it take string type
    scanner.nextLine();
    
    System.out.print("Enter Employee City: "); //it take the empcity
    String city = scanner.nextLine();    //it take string type
    scanner.nextLine();
    
    System.out.print("Enter Employee Salary: "); //it take the empsalary
    double salary = scanner.nextDouble();  //it take double type
    scanner.nextLine();
    
    //we create a sql query to insert a new employee
    String q = "INSERT INTO employee (eid, ename, city, salary) VALUES (?, ?, ?, ?)";
    
    //we can create a prepared statement for this
    PreparedStatement pstmt= connection.prepareStatement(q);
    pstmt.setInt(1, eid);  //in this we can set the values
    pstmt.setString(2, ename);
    pstmt.setString(3, city);
    pstmt.setDouble(4, salary);
    
    //it can execute the query
    int rowsInserted = pstmt.executeUpdate();
    
    if (rowsInserted > 0) //we can give the if-else condition to confirm that employee details is inserted successfully or not
    {
        System.out.println("Employee details is inserted successfully.");
    }
    else
    {
        System.out.println("Failed to insert the employee details.");
    }
		
	}

//we can use a private static method for update employee details with connection and scanner for reading user input & this throws sqlexceptiontype

private static void updateEmployees(Connection connection, Scanner scanner) throws SQLException
{
	//we create a sql query to update a  employee
	String updateq= "UPDATE employee SET ename = ?, city = ?, salary = ? WHERE eid = ?";
	
	//we can create a prepared statement for this
    PreparedStatement pstmt = connection.prepareStatement(updateq);
	pstmt.setString(1,"Kavya"); //in this we can set the values
	pstmt.setString(2, "Pune");
	pstmt.setDouble(3, 38900);
	pstmt.setInt(4, 106);
	
	//it can execute the query
	int rowsUpdated = pstmt.executeUpdate();
    if (rowsUpdated > 0) //we can give the if-else condition to confirm that employee details is updated successfully or not
    
    {
        System.out.println("Employee data is updated successfully.");
    }
    else
    {
        System.out.println("Employee data is not not found in the system.");
    }
		
	}

//we can use a private static method for delete employee details with connection and scanner for reading user input & this throws sqlexceptiontype

private static void deleteEmployees(Connection connection, Scanner scanner) throws SQLException
{
	//we create a sql query to delete a  employee
	String deleteq = "DELETE FROM employee WHERE eid = ?";
	
	//we can create a prepared statement for this
    PreparedStatement pstmt = connection.prepareStatement(deleteq);
	pstmt.setInt(1, 105); //we can set values eid=105
	

	//it can execute the query
    int rowsDeleted = pstmt.executeUpdate();
    if (rowsDeleted > 0) //we can give the if-else condition to confirm that employee details is deleted successfully or not
    {
        System.out.println("Employee data is deleted successfully.");
    }
    else
    {
        System.out.println("Employee data is not found in the system.");
    }
	}

//we can use a private static method for delete employee details with connection and scanner for reading user input & this throws sqlexceptiontype

private static void selectEmployees(Connection connection) throws SQLException
{
	//we can create the SQL query to select all the employees
	String selectq = "SELECT * FROM employee";
	
	//in this we can create a statement for executing the SQL query
    Statement statement = connection.createStatement();
    
    //it execute the given SQL query and result set is obtained
    ResultSet resultSet = statement.executeQuery(selectq);
    
    //we can use while condition & iterate through the result set and print all the employee details
    while (resultSet.next())
    {
        int eid = resultSet.getInt("eid"); //in this we can get the values or details of all employees.
        String ename = resultSet.getString("ename");
        String city = resultSet.getString("city");
        double salary = resultSet.getDouble("salary");
        
        //it prints all the employee details
        System.out.println("Employee ID: " + eid + ", Name: " + ename + ", City: " + city + ", Salary: " + salary);
    
    }
}
}

//output:-
//Choose an operation we want to be performed:
//1. Insert the data
//2. Update the data
//3. Delete the data
//4. Select the data
//5. Exit from the process
//
//1
//Enter Employee ID: 111
//Enter Employee Name: Tanushri
//Enter Employee City: Nashik
//Enter Employee Salary: 56000
//Employee details is inserted successfully.
//Choose an operation we want to be performed:
//1. Insert the data
//2. Update the data
//3. Delete the data
//4. Select the data
//5. Exit from the process
//2
//Employee data is updated successfully.
//Choose an operation we want to be performed:
//1. Insert the data
//2. Update the data
//3. Delete the data
//4. Select the data
//5. Exit from the process
//
//3
//Employee data is deleted successfully.
//Choose an operation we want to be performed:
//1. Insert the data
//2. Update the data
//3. Delete the data
//4. Select the data
//5. Exit from the process
//
//4
//Employee ID: 101, Name: Ram, City: Dehli, Salary: 25000.0
//Employee ID: 102, Name: shyam, City: Nagar, Salary: 35000.0
//Employee ID: 103, Name: Aliya, City: Rajastan, Salary: 21000.0
//Employee ID: 104, Name: Navya, City: Sanli, Salary: 45000.0
//Employee ID: 106, Name: Kavya, City: Pune, Salary: 38900.0
//Employee ID: 107, Name: Sara, City: Nagar, Salary: 16000.0
//Employee ID: 108, Name: Shekar, City: Dehli, Salary: 17000.0
//Employee ID: 109, Name: Ravi, City: Nagar, Salary: 15600.0
//Employee ID: 110, Name: Tanusha, City: Nagapur, Salary: 25000.0
//Employee ID: 111, Name: Tanushri, City: Nashik, Salary: 56000.0
//Choose an operation we want to be performed:
//1. Insert the data
//2. Update the data
//3. Delete the data
//4. Select the data
//5. Exit from the process
