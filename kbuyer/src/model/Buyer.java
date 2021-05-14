
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Buyer {
	//*************Create Connection**************
		//A common method to connect to the DB
		public Connection connect()
		{
		 Connection con = null;

		 try
		 {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gadgetbadget?useTimezone=true&serverTimezone=UTC", "root", "");
		 //For testing
		 System.out.print("Successfully connected");
		 }
		 catch(Exception e)
		 {
		 e.printStackTrace();
		 }

		 return con;
		}

	//*************Insert Data*****************	
		public String insertBuyer(String fname,String lname ,String gender,String address, String phone,String nic ,String birthday,String email,String password)
		 {
		 
			
			String output = "";
		
		try
		 {
		 Connection con = connect();
		 
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 
		 // create a prepared statement
		 String query = " insert into buyer(`bID`,`bFname`,`bLname`,`bGender`,`bAddress`,`bPhone`,`bNic`,`bBirthday`,`bEmail`,`bPassword`)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, fname);
		 preparedStmt.setString(3, lname);
		 preparedStmt.setString(4, gender);
		 preparedStmt.setString(5, address);
		 preparedStmt.setString(6, phone);
		 preparedStmt.setString(7, nic);
		 preparedStmt.setString(8, birthday);
		 preparedStmt.setString(9, email);
		 preparedStmt.setString(10, password);

		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 
		 }
		 
		catch (Exception e)
		{
		 output = "Error while inserting the item.";
		 System.err.println(e.getMessage());
		}
		 return output;
		}

		
		
		//************ Read Data****************************
		public String readBuyer()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		
		 // Prepare the html table to be displayed
		 output = "<table border='1'>"
		 		+ "<tr>"
				+"<th>Buyer_ID</th>"
		 		+ "<th>FirstName</th>"
		 		+ "<th>LastName</th>"
		 		+ "<th>Gender</th>"
		 		+ "<th>Address</th>"
		 		+"<th>Phone</th>"
		 		+"<th>Nic</th>"
		 		+"<th>Birth Day</th>"
		 		+"<th>Email</th>"
		 		+"<th>Password</th>"
		 	    +"<th>Update</th>"
		 	    + "<th>Remove</th>"
		 	    + "</tr>";

		 String query = "select * from buyer";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String bID  = Integer.toString(rs.getInt("bID"));
		 String bFname = rs.getString("bFname");
		 String bLname = rs.getString("bLname");
		 String bGender = rs.getString("bGender");
		 String bAddress = rs.getString("bAddress");
		 String bPhone=rs.getString("bPhone");
		 String bNic = rs.getString("bNic");
		 String bBirthday = rs.getString("bBirthday");
		 String bEmail = rs.getString("bEmail");
		 String bPassword = rs.getString("bPassword");

		 
		 // Add into the html table
		 output += "<tr><td>" + bID + "</td>";
		 output += "<td>" + bFname + "</td>";
		 output += "<td>" + bLname + "</td>";
		 output += "<td>" + bGender + "</td>";
		 output += "<td>" + bAddress + "</td>";
		 output += "<td>"+bPhone+"</td>";
		 output += "<td>" +bNic  + "</td>";
		 output += "<td>" +bBirthday + "</td>";
		 output += "<td>" +bEmail  + "</td>";
		 output += "<td>" +bPassword  + "</td>";

		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>" + "<td><form method='post' action='Buyers.jsp'>" + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
		 + "<input name='itemID' type='hidden' value='" + bID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		//*****************Update Data**********************
		public String updateBuyer(String ID,String fname,String lname ,String gender,String address, String phone,String birthday,String password)
		{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE buyer SET bFname=?,bLname=?,bGender=?,bAddress=?,bPhone=?,bBirthday=?,bPassword=?  WHERE bID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, fname);
			 preparedStmt.setString(2, lname);
			 preparedStmt.setString(3, gender);
			 preparedStmt.setString(4, address);
			 preparedStmt.setString(5, phone);
			 preparedStmt.setString(6, birthday);
			 preparedStmt.setString(7,password);
			 preparedStmt.setInt(8, Integer.parseInt(ID));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while updating the item.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
		
		
		
		//******************Delete data*****************
		public String deleteBuyer(String buyerID )
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from buyer where bID =?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(buyerID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		//*******************View Profile*****************
		public String viewProfileBuyers(int buyerId)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		
		 // Prepare the html table to be displayed
		 output = "<table border='1'>"
		 		+ "<tr>"
				+"<th>Buyer_ID</th>"
		 		+ "<th>FirstName</th>"
		 		+ "<th>LastName</th>"
		 		+ "<th>Gender</th>"
		 		+ "<th>Address</th>"
		 		+"<th>Phone</th>"
		 		+"<th>Nic</th>"
		 		+"<th>Birth Day</th>"
		 		+"<th>Email</th>"
		 		+"<th>Password</th>"
		 	    +"<th>Update</th>"
		 	    + "<th>Remove</th>"
		 	    + "</tr>";

		 String query = "select * from buyer where 	bID=? ";
		 PreparedStatement stmt = con.prepareStatement(query);

		 stmt.setInt(1,buyerId);
		 ResultSet rs = stmt.executeQuery();
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String bID  = Integer.toString(rs.getInt("bID"));
		 String bFname = rs.getString("bFname");
		 String bLname = rs.getString("bLname");
		 String bGender = rs.getString("bGender");
		 String bAddress = rs.getString("bAddress");
		 String bPhone=rs.getString("bPhone");
		 String bNic = rs.getString("bNic");
		 String bBirthday = rs.getString("bBirthday");
		 String bEmail = rs.getString("bEmail");
		 String bPassword = rs.getString("bPassword");

		 
		 // Add into the html table
		 output += "<tr><td>" + bID + "</td>";
		 output += "<td>" + bFname + "</td>";
		 output += "<td>" + bLname + "</td>";
		 output += "<td>" + bGender + "</td>";
		 output += "<td>" + bAddress + "</td>";
		 output += "<td>"+bPhone+"</td>";
		 output += "<td>" +bNic  + "</td>";
		 output += "<td>" +bBirthday  + "</td>";
		 output += "<td>" +bEmail  + "</td>";
		 output += "<td>" +bPassword  + "</td>";

		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>" + "<td><form method='post' action='Buyers.jsp'>" + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
		 + "<input name='buyerID' type='hidden' value='" + bID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }

	
		
}