package com.dxc.services;

import java.util.List;

import com.dxc.dao.IUserDao;
import com.dxc.dao.UserDaoImpl;
import com.dxc.pojos.Book;

public class UserServiceImpl  implements IUserServices
{
	IUserDao dao1=new UserDaoImpl();
	public void login(String name,String pass)
	{
		dao1.login(name, pass);
	}
	public List<Book> getAuthorBooks(String author )
	{
		return dao1.getAuthorBooks(author);
	}
	
	public void closeConnection()
	{
		
	}
	@Override
	public boolean validateBookIdAndUserId(int bid, int uid, int is) 
	{
		 boolean validateUserId=dao1.validateUserId(uid);
		 boolean validateBookId=dao1.validateBookId(bid);
		
		 if(validateUserId==true && validateBookId==true )
		 {
			 return true;
		 }
		 else 
		 {
			 return false;
		 }
		
	}
	public int issueBook(int bid, int uid, int issue_date) 
	{
	
		return dao1.issueBook(bid, uid, issue_date);
	}
	@Override
	public void getReturnBooks(int bid1, int uid1, String date) 
	{
		dao1.getReturnBooks(bid1, uid1, date);
		
	}
	@Override
	public void getBalance(int userid1) 
	{
		dao1.getBalance(userid1);
		
	}
}
