package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {
	//creating connection
	public Connection connect()
	{
		Connection con = null;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/admindb","root", "");
			//For testing
			System.out.print("Successfully connected");
			 }
			catch(Exception e)
			 {
			 	e.printStackTrace();
			  }

		return con;
		}
			
	public String validateLogin(String userName, String Password) 
	{
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for validation"; 
			}
						
			String query = "select username, password,userType from users";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
						
			while(rs.next())
			{
				String userN = rs.getString("username");
				String pass = rs.getString("password");
				String userT = rs.getString("userType");
							
				if(userName.equals(userN) && Password.equals(pass))
				{
					if(userT.equals("admin")) {
						return "Welcome Admin";
					}
					else{
						return "Welcome "+ userName;
					}
				}		
			}
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
			return "incorrect Username or password";
		}
			
			
}
