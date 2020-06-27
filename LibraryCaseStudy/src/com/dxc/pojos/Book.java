package com.dxc.pojos;

public class Book 
{
    private int id;
    private String name;
    private String author;
    private int quantity;
    public Book()
    {
    	
    }
    
	public Book(int id, String name, String author, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", quantity=" + quantity + "]";
	}

	public void display()
	{
		System.out.println(id+ " "+name+ " "+author+ " "+quantity);
	}
    
}
