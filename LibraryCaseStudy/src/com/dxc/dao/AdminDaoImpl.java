package com.dxc.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.dxc.pojos.Book;
import com.dxc.pojos.User;
import com.dxc.pojos.Admin;

public class AdminDaoImpl  implements IAdminDao
{
	private static Connection con;
	static
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("Driver loaded....");
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kiranmayee?autoReconnect=true&useSSL=false", "root", "Jashwin@11");
	        // System.out.println("connected to database.......");
	      	}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void login(String admin_name,String password)
	{
		try
		{
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select admin_name,password from admin");
			while(rs.next())
			{
				if(rs.getString(1).equals(admin_name) && rs.getString(2).equals(password))
					System.out.println("Admin login successfully.........");
				else
					System.out.println("Invalid login............");
				
			}
			
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
	}
	@Override
	public void addbooks(Book b) 
	{
		try	
		{
		PreparedStatement pstmt=con.prepareStatement("insert into book values(?,?,?,?)");
		pstmt.setInt(1, b.getId());
        pstmt.setString(2,b.getName());
        pstmt.setString(3, b.getAuthor());
        pstmt.setInt(4, b.getQuantity());
        pstmt.execute();
        System.out.println("The books are added..........");  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void RemoveBooks(int r) 
	{
		try
		{
			PreparedStatement pstmt=con.prepareStatement("delete from book where id=?");
	         System.out.println("Enter the id");
	         pstmt.setInt(1,r);
	         pstmt.execute();
	         System.out.println("The book is deleted from the table.......");
		}
	         catch(Exception e)
	         {
	        	
	         }
	}

	@Override
	public List<Book> ViewBooks() 
	{
		List<Book> list=new ArrayList<>();
		try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from book");
			while(rs.next())
			{
				Book n=new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
				list.add(n);
			}  
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<User> ViewIssuedBooks(int userid) 
	{
		List<User> list=new ArrayList<>();
	
			
		try
		{
			PreparedStatement pstmt=con.prepareStatement("select bookissued from user where userid=?");
			pstmt.setInt(1, userid);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString(1));
			}        
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<User> GetThebalancelist(int userid) 
	{
		try
		{
			PreparedStatement pstmt=con.prepareStatement("select userbalance from user where userid=?");
			pstmt.setInt(1, userid);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getDouble(1));
			}        
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void DeleteAccount(int userid) 
	{
		try
		{
			PreparedStatement pstmt=con.prepareStatement("delete from user where userid=?");
	         System.out.println("Enter the userid");
	         pstmt.setInt(1,userid);
	         pstmt.execute();
	         System.out.println("Useraccount is deleted from the library...............");
		}
	         catch(Exception e)
	         {
	        	 e.printStackTrace();
	         }
	}
	public void closeConnection()
	{
		
	}
	@Override
	public List<User> viewUsers() 
	{
		List<User> list=new ArrayList<>();
		try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from user");
			while(rs.next())
			{
				User n=new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
				list.add(n);
			}
		 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	@Override
	public void addUser(int id, String name, String pass, String ib,int bal)
	{
		try	
		{
		PreparedStatement pstmt=con.prepareStatement("insert into user values(?,?,?,?,?)");
		pstmt.setInt(1, id);
        pstmt.setString(2,name);
        pstmt.setString(3, pass);
        pstmt.setString(4, ib);
        pstmt.setInt(5, bal);
        pstmt.execute();
        System.out.println("The users are added..........");  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
		
	}