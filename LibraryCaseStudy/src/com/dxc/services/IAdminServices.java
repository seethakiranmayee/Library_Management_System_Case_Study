package com.dxc.services;

import java.util.List;

import com.dxc.pojos.Admin;
import com.dxc.pojos.Book;
import com.dxc.pojos.User;

public interface IAdminServices 
{
	public void login(String admin_name,String password);
	public void addbooks(Book b);
    public void RemoveBooks(int r);
    public List<Book> ViewBooks();
    public List<User> GetThebalancelist(int userid);
    public void DeleteAccount(int userid);
    public void closeConnection();
	public List<User> ViewIssuedBooks(int userid);
	public List<User> viewUsers();
	void addUser(int id, String name, String pass, String ib,int bal);
	
}
