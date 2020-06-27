package com.dxc.dao;
import com.dxc.pojos.Book;
import java.util.*;
public interface IUserDao
{
	public void login(String name,String pass);
	public List<Book> getAuthorBooks(String author );
	public void closeConnection();
	public boolean validateUserId(int uid);
	public boolean validateBookId(int bid);
	public int issueBook(int bid,int uid, int issue_date);
	public void getReturnBooks(int bid1, int uid1, String date);
	public void getBalance(int userid1);
}
