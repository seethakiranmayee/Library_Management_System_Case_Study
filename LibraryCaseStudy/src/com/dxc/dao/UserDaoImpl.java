package com.dxc.dao;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.dxc.pojos.Book;

public class UserDaoImpl implements IUserDao {

	private static Connection con;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("Driver loaded....");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kiranmayee?autoReconnect=true&useSSL=false",
					"root", "Jashwin@11");
			// System.out.println("connected to database.......");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void login(String name, String pass) {
		int flag = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select name,pass from user");
			while (rs.next()) {
				if (rs.getString(1).equals(name) && rs.getString(2).equals(pass)) {
					flag = 1;
					System.out.println("User login successfully.........");
				}

			}
			if (flag == 0)
				System.out.println("invalid login!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Book> getAuthorBooks(String author) {

		List<Book> ls = new ArrayList<>();
		try {
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement("select * from book where author=?");
			pstmt.setString(1, author);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Book n = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				ls.add(n);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;

	}

	@Override
	public void closeConnection() {

	}

	@Override
	public boolean validateUserId(int uid) {
		boolean flag = false;
		try {
			PreparedStatement pstmt = con.prepareStatement("select userid from user where userid=?");
			pstmt.setInt(1, uid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}

	@Override
	public boolean validateBookId(int bid) {

		boolean flag = false;
		try {
			PreparedStatement pstmt = con.prepareStatement("select id from book where id=?");
			pstmt.setInt(1, bid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int issueBook(int bid, int uid, int no_of_days) {
		int book_count = 0;
		int i = 0;
		try {

			PreparedStatement pstmt1 = con.prepareStatement("select * from book where id=?");
			pstmt1.setInt(1, bid);
			ResultSet rs = pstmt1.executeQuery();
			while (rs.next()) {
				book_count = rs.getInt(4);

			}
			if (book_count != 0) {

				PreparedStatement pstmt = con.prepareStatement("insert into issuedbooks values(?,?,?,?,?,null)");
				pstmt.setInt(1, bid);
				pstmt.setInt(2, uid);
				pstmt.setInt(3, no_of_days);
				pstmt.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
				pstmt.setString(5, "Issued");
				i = pstmt.executeUpdate();
				System.out.println("book is issued");
				System.out.println(" before book is not issued" + book_count);
				book_count = book_count - 1;
				System.out.println("after issued the book" + book_count);
				System.out.println("After book insert" + i);
				if (i == 1) {
					PreparedStatement pstmt2 = con.prepareStatement("update book set quantity=? where  id=?");
					pstmt2.setInt(1, book_count);
					pstmt2.setInt(2, bid);
					pstmt2.executeUpdate();

				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i;
	}

	@Override
	public void getReturnBooks(int bid1, int uid1, String date) 
	{
		int n = 0,i=0;
		int amount=0;
		int quantity=0;

       try {
		PreparedStatement pstmt=con.prepareStatement("select * from issuedbooks where bid=? and uid=? and issue_date=?");
		pstmt.setInt(1, bid1);
		pstmt.setInt(2, uid1);
		pstmt.setString(3,date);
		ResultSet rs=pstmt.executeQuery();
	   while(rs.next())
	   {
		     n=rs.getInt(3);
		    n=n*5;
	   
	   System.out.println(" Amount is calculated for the no of days the book is borrowed "+n);
	   }
	   if(n>0)
	   {
		  
	   PreparedStatement pst=con.prepareStatement("select * from user where userid=?");
	   pst.setInt(1, uid1);
	   ResultSet rs1=pst.executeQuery();
	   while(rs1.next())
	   {
		   amount= rs1.getInt(5);
		  amount=amount-n;
		   System.out.println(" The remaining amount after returned the book "+amount);
	   }
		 
				PreparedStatement pstmt2 = con.prepareStatement("update issuedbooks set status=?,return_date=? where  bid=? and issue_date=? ");
				pstmt2.setString(1, "RETURNED");
				pstmt2.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
				pstmt2.setInt(3, bid1);
				try {
					pstmt2.setTimestamp(4,  new java.sql.Timestamp (new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pstmt2.execute();
	   
	   PreparedStatement pstmt3 = con.prepareStatement("update user set userbalance=? where  userid=?");
	   pstmt3.setInt(1, amount);
	   pstmt3.setInt(2, uid1);
	 pstmt3.executeUpdate();
	   
       }
       }
	   
	catch (SQLException e) 
       {
		
		e.printStackTrace();
	}
	}

	@Override
	public void getBalance(int userid1)
	{
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from user where userid=? ");
			pstmt.setInt(1, userid1);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{  
				
				int bal=rs.getInt(5);
				System.out.println("The avalilability balance is  :"+bal);
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

       