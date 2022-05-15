package com;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;


public class UserService {
	
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		// Connect to the DB
		public Connection connect()
		{
			Connection con = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogriddb", "root", "");
				
				//for testing
				System.out.print("Succesfully connected to the DB");
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return con;
		}
		
		//insert a Complain
		public String insertUser(String Username, String Userpwd, String Userphn, String Useremail, String Useradrs, String Useracc, String Date)
		{
			System.out.println(" "+Username+" "+Userpwd+" ");
			String output = "";
			
			//String comEmpty = "^[A-Za-z0-9+_.-]{0}";
			
			try 
			{
				Connection con = connect();
				
				if(con == null)
				{return "Error while connecting to the database for inserting.";}
				
				//create a prepared statement 
				String query = " insert into user (`UID`,`name`,`password`,`phoneNO`,`email`,`address`,`accountNO`,`createdDate`)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, Username);
				preparedStmt.setString(3, Userpwd);
				preparedStmt.setInt(4, Integer.parseInt(Userphn));
				preparedStmt.setString(5, Useremail);
				preparedStmt.setString(6, Useradrs);
				preparedStmt.setInt(7, Integer.parseInt(Useracc));
				preparedStmt.setString(8, Date);
				
				/*if((Username.matches(comEmpty))){
					output = "User Name should not be Empty!";
				}else {*/
					
							//execute the statement
					preparedStmt.execute();
					con.close();
					
					String newComplain = readUser();
					output = "{\"status\":\"success\", \"data\": \"" + 
							newComplain + "\"}"; 

				//}
			}catch(Exception e)
			{
				output = "{\"status\":\"error\", \"data\": "
						+ "\"Error while inserting the Complaints.\"}"; 
				 System.err.println(e.getMessage()); 
			}
			return output;
		}
		
		
		
		//Read a Complain
				public String readUser() 
				{
					String output = "";
					
					try
					{
						Connection con = connect();
						
						if(con == null)
						{
							return "Error while connecting to the database for Reading.";}
						
						//Prepare the html table to be displayed
						output = "<table border='1'>"
								+"<tr><th>User ID </th>" 
						        +  "<th>User Name</th>"
								+  "<th>phoneNO</th>"
								+  "<th>Email</th>"
								+  "<th>Address</th>"
								+  "<th>AccountNO</th>"
								+  "<th>CreatedDate</th>"
								+  "</tr>";
						
						String query = "select * from user";
						 java.sql.Statement stmt = con.createStatement(); 
						 ResultSet rs = stmt.executeQuery(query); 
						
						// iterate through the rows in the result set
						while(rs.next())
						{
							 String UID = Integer.toString(rs.getInt("UID")); 
							 String name = rs.getString("name"); 
							 String phoneNO = Integer.toString(rs.getInt("phoneNO"));
							 String email = rs.getString("email"); 
							 String address = rs.getString("address");
							 String accountNO = Integer.toString(rs.getInt("accountNO"));
							 String createdDate = rs.getString("createdDate");
	
						
						 // Add into the html table
							 //output += "<tr><td><input id='hidcomIDUpdate' name='hidcomIDUpdate' type='hidden' value='" + comId + "'>"+"</td>"; 
							 output += "<tr><td>" + UID + "</td>"; 
							 output += "<td>" + name + "</td>"; 
							 output += "<td>" + phoneNO + "</td>";
							 output += "<td>" + email + "</td>";
							 output += "<td>" + address + "</td>"; 
							 output += "<td>" + accountNO + "</td>"; 
							 output += "<td>" + createdDate + "</td>";
						
						 //buttons
						 output += "<td><input id='btnUpdate' name='btnUpdate' type='button' value='Update' "
						 + "class='btnUpdate btn btn-secondary' data-itemid='" + UID + "'></td>"
						 + "<td><input id='btnRemove' name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-itemid='" + UID + "'></td></tr>"; 
						 		
						
						}
						 con.close();
						 // Complete the html table
						 output += "</table>"; 
					}
					catch(Exception e)
					{
						output = "Error while Reading the Complains."; 
						System.err.println(e.getMessage());
					}
					return output;
					
				}
				
				
				//Update Complain		
				public String updateUser(String Username, String Userpwd, String Userphn, String Useremail, String Useradrs, String Useracc, String Date, String UID)
				{
					String output = "";
					try
					{
					
						Connection con = connect();
						
						if(con == null)
						{return "Error while connecting to the database for Updating.";}
						
						//create a prepared statement
						String query = "UPDATE user SET name=?,password=?,phoneNO=?,email=?,address=?,accountNO=?, createdDate=? WHERE UID=?";
						
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						//Binding Values
						preparedStmt.setString(1, Username);
						preparedStmt.setString(2, Userpwd);
						preparedStmt.setInt(3, Integer.parseInt(Userphn));
						preparedStmt.setString(4, Useremail);
						preparedStmt.setString(5, Useradrs);
						preparedStmt.setInt(6, Integer.parseInt(Useracc));
						preparedStmt.setString(7, Date);
						preparedStmt.setInt(8, Integer.parseInt(UID)); 
						
						// execute the statement
						 preparedStmt.execute(); 
						 con.close(); 

						 String newComplain = readUser();
							output = "{\"status\":\"success\", \"data\": \"" + 
									newComplain + "\"}"; 
						
						 
						
					}catch(Exception e)
					{
						output = "{\"status\":\"error\", \"data\": "
								+ "\"Error while Updating the Complaints.\"}"; 
						 System.err.println(e.getMessage()); 
					}
					return output;
					
					
				}
				
				//Delete a Complain
				public String deleteUser(String ID) 
				{
					String output = "";
					
					try 
					{
						Connection con = connect();
						
						if(con == null) 
						{return "Error while connecting to the database for deleting.";}
						
						//create a prepared statement
						String query = "delete from user where UID=?";
					
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						//binding values
						preparedStmt.setInt(1, Integer.parseInt(ID));
						
						
						//execute the statement
						preparedStmt.execute();
						con.close();
						
						 String newComplain = readUser();
							output = "{\"status\":\"success\", \"data\": \"" + 
									newComplain + "\"}"; 
						
					}
					catch(Exception e) {
						output = "{\"status\":\"error\", \"data\": "
								+ "\"Error while Deleting the Complaints.\"}"; 
						 System.err.println(e.getMessage()); 
					}
					
					return output;
				}
				
		
}
