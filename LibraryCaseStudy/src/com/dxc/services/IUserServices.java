package com.dxc.services;

import java.util.List;

import com.dxc.pojos.Book;

public interface IUserServices 
{
	public void login(String name,String pass);
	public List<Book> getAuthorBooks(String author );
	public void closeConnection();
	public boolean validateBookIdAndUserId(int bid, int uid, int is);
	public int issueBook(int bid,int uid,int issue_date);
	public void getReturnBooks(int bid1, int uid1, String date);
	public void getBalance(int userid);
	
	
	
}
