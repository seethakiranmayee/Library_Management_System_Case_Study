package com.dxc.presentation;

import java.sql.Date;
import java.util.List;

import java.util.Scanner;

import com.dxc.pojos.Admin;
import com.dxc.pojos.Book;
import com.dxc.pojos.User;
import com.dxc.services.AdminServiceImpl;
import com.dxc.services.IAdminServices;
import com.dxc.services.IUserServices;
import com.dxc.services.UserServiceImpl;

public class Test {

		private static String author;

		public static void main(String[] args)
	{
		IAdminServices adminservice=new AdminServiceImpl();
		IUserServices  userservice=new UserServiceImpl() ;
		Scanner sc=new Scanner(System.in);
		int userid;
		int choice1;
		int ch1;
		int ch2;
		int r;
		System.out.println("1.Enter the Admin login details:");
		System.out.println("2.Enter the User login details:");
		System.out.println("Enter your choice");
		choice1=sc.nextInt();
		switch(choice1)
		{
		     case 1:
			        System.out.println("Enter the admin login details:");
			        System.out.println("Enter the username:");
			        String admin_name = sc.next();
			        System.out.println("Enter password:");
			        String password=sc.next();
		           adminservice.login(admin_name, password);
				while(true)
		          {
					System.out.println("1.ADD the book");
					System.out.println("2.Remove the book");
					System.out.println("3.view the books");
					System.out.println("4.view the issued books");
					System.out.println("5.Get the balance list");
					System.out.println("6.Delete account of the user");
					System.out.println("7.ADD the user");
					System.out.println("8.view the users");
					System.out.println("9.Exit");
					System.out.println("Enter the choice:");
					ch1=sc.nextInt();
					switch(ch1)
					{
					case 1:
						     System.out.println("Enter book details:");
						     Book b=new Book(sc.nextInt(),sc.next(),sc.next(),sc.nextInt());
						     adminservice.addbooks(b);
						     break;
					case 2: 
						    System.out.println("Remove the book");
						    r=sc.nextInt();
						    adminservice.RemoveBooks(r);
						   break;
					case 3:
						     System.out.println("View all the book details:");
						     List<Book> list=adminservice.ViewBooks();
						    		 for(Book n:list)
								       {
								    	 n.display();
								    	 System.out.println();
								      }
						              break;
					case 4:
						       System.out.println("Enter the particular userid");
						       userid=sc.nextInt();
						      adminservice.ViewIssuedBooks(userid);
						      System.out.println();
						    break;
					case 5:  
						      System.out.println("Enter the userid for checking the balance");
						      userid=sc.nextInt();
						      adminservice.GetThebalancelist(userid);
						      System.out.println();
						     
						   break;
					case 6:
						 System.out.println("Enter the userid");
					       userid=sc.nextInt();
					       adminservice.DeleteAccount(userid);
					       System.out.println();
						   break;
					case 7:
						    System.out.println("Enter user id");
						    int id=sc.nextInt();
						    System.out.println("enter username");
						    String name=sc.next();
						    System.out.println("Enter password");
						    String pass=sc.next();
						    System.out.println("enter the book u want to issue");
						    String ib=sc.next();
						    System.out.println("Enetr the balance for ur account");
						    int bal=sc.nextInt();
						    adminservice.addUser(id, name, pass, ib,bal);
						    System.out.println();
					case 8:
						    System.out.println(" view the users");
						    List<User> list1=adminservice.viewUsers();
				    		 for(User n:list1)
						       {
						    	 n.display();
						       }
				    		 System.out.println();
						break;
					case 9:
						    adminservice.closeConnection();
					       System.exit(0);
					}
		          }
					case 2:  System.out.println("Enter the user login details:");
			                 System.out.println("Enter the username:");
			                 String name = sc.next();
			                 System.out.println("Enter password:");
			                 String pass=sc.next();
		                    userservice.login(name, pass);	
						
						while(true)
						{
							System.out.println("1.Issue book.");
							System.out.println("2.Return book");
							System.out.println("3.Get list of books by particular author.");
							System.out.println("4.check available balance in the account.");
							System.out.println("5.Exit");
							System.out.println("Enter the choice:");
							ch2=sc.nextInt();
							switch(ch2)
							{
							case 1:
								      System.out.println("Enter Book id:");
								      int bid=sc.nextInt();
								      System.out.println("Enter User id:");
								      int uid=sc.nextInt();
								      System.out.println("Enter no of days books issued");
								      int is=sc.nextInt();
								      if(bid!=0 && uid!=0 && is!=0)
								      {
								    	 boolean validate=userservice.validateBookIdAndUserId(bid,uid,is );
						
								      if(validate==true)
								      {
								    	  
										int issued=userservice.issueBook(bid,uid,is);
										if(issued==1)
										{
											System.out.println("Issued successfully........");
										}
										else
										{
											System.out.println("Book not issued successfully.......");
										}
								      }
								      
								      }
								      break;
							case 2:
								     System.out.println("Enter bookid");
								     int bid1=sc.nextInt();
								     System.out.println("Enter User id");
								     int uid1=sc.nextInt();
								     System.out.println("Enter issue date");
								     String date=sc.next();
								     userservice.getReturnBooks(bid1,uid1,date);
								     System.out.println("The book is returned....");
								     
								       break;
							case  3:    
								    
								     System.out.println(" get the booklist by author name");
								author=sc.next();
								List<Book> ls=userservice.getAuthorBooks(author);
						              for(Book n:ls)
						              {
						            	  n.display();
						              }
						              System.out.println();
						              
								       
								      break;
							case 4:
								       System.out.println(" Enter userid for checking the balance");
								       int userid1=sc.nextInt();
								       userservice.getBalance(userid1);
								       System.out.println();
								       
								       
								      break;
							case 5:
								   userservice.closeConnection();
							       System.exit(0);
							}
							
						}
		          }
		}
}