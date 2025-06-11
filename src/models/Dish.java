package models;

public class Dish 
{
	public int id;
	public String name;
	public double price;
	public String description;
	public byte[] image;
	public boolean status = false;
	public boolean dish_Type = false;
	
	public Dish(int id, String name, double price, String description, byte[] image,  boolean status, boolean dish_type)
	{
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.image = image;
		this.status = status;
		this.dish_Type = dish_type;
	}
}
