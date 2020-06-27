package com.dxc.pojos;

public class User 
{
   private int userid;
   private String name;
   private String pass;
   private String issuedbook;
   private int userbalance;


public User(int userid, String name, String pass, String issuedbook, int userbalance) {
	super();
	this.userid = userid;
	this.name = name;
	this.pass = pass;
	this.issuedbook = issuedbook;
	this.userbalance = userbalance;
}

public int getUserid() {
	return userid;
}

public void setUserid(int userid) {
	this.userid = userid;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getPass() {
	return pass;
}

public void setPassword(String pass) {
	this.pass = pass;
}

public String getIssuedbook() {
	return issuedbook;
}

public void setIssuedbook(String issuedbook) {
	this.issuedbook = issuedbook;
}

public int getUserbalance() {
	return userbalance;
}

public void setUserbalance(int userbalance) {
	this.userbalance = userbalance;
}
   public void display()
   {
	   System.out.println(userid+" "+name+" "+pass+" "+issuedbook+" "+userbalance);
   }
}
