package com.dxc.dao;

import java.util.*;

import com.dxc.pojos.Admin;
import com.dxc.pojos.Book;
import com.dxc.pojos.User;

public interface IAdminDao 
{
	public void login(String admin_name,String password);
    public void addbooks(Book b);
    public void RemoveBooks(int r);
    public List<Book> ViewBooks();
    public List<User> ViewIssuedBooks(int userid);
    public List<User> GetThebalancelist(int userid);
    public void DeleteAccount(int userid);
    public List<User> viewUsers();
	public void addUser(int id, String name, String pass, String ib,int bal);
    
}
