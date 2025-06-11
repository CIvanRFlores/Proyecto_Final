package models;

public class Order 
{
	public int id;
	public int id_Dish;
	public int id_Client;
	public String table_Number;
	public String total;
	public String ammount;
	public Boolean order_Type;
	public String estimated_Time;
	public String instructions;
	
	public Order(int id, int id_Dish, int id_Client, String table_Number, String total, String ammount,  Boolean order_Type, String estimated_Time, String instructions)
	{
		this.id = id;
		this.id_Dish = id_Dish;
		this.id_Client = id_Client;
		this.table_Number = table_Number;
		this.total = total;
		this.ammount = ammount;
		this.order_Type = order_Type;
		this.estimated_Time = estimated_Time;
		this.instructions = instructions;
	}
}
