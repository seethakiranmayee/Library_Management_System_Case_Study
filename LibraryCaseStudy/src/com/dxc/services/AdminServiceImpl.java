package com.dxc.services;

import java.util.List;

import com.dxc.dao.AdminDaoImpl;
import com.dxc.dao.IAdminDao;
import com.dxc.dao.IUserDao;
import com.dxc.dao.UserDaoImpl;
import com.dxc.pojos.Book;
import com.dxc.pojos.User;

 public class AdminServiceImpl implements IAdminServices
{
     IAdminDao dao=new AdminDaoImpl();
     public void login(String admin_name,String password)
     {
    	 dao.login(admin_name, password);
     }
     
     public void addbooks(Book b)
     {
 		 dao.addbooks(b);
 		
 	}
	public void RemoveBooks(int r) 
	{
		dao.RemoveBooks(r);
	}

	@Override
	public List<Book> ViewBooks() {
		
		return dao.ViewBooks();
	}

	public List<User> ViewIssuedBooks(int userid) 
	{
		return dao.ViewIssuedBooks(userid);
		
		
	}

	@Override
	public void DeleteAccount(int userid)
	{
		
		dao.DeleteAccount(userid);
	}
	public void closeConnection()
	{
		
	}

	@Override
	public List<User> GetThebalancelist(int userid) 
	{
		return dao.GetThebalancelist(userid);
	}

	@Override
	public void addUser(int id,String name,String pass,String ib,int bal)
	{
		dao.addUser(id,name,pass,ib,bal);
		
	}

	@Override
	public List<User> viewUsers() 
	{
		
		return dao.viewUsers();
	}

	
}
