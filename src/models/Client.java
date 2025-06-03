package models;

public class Client	//Clase cliente
{
	public int id;
	public String name;
	public String last_Name;
	public String phone_Number;
	public String address_1;
	public String address_2;
	public String city;
	public String state;
	public int postal_Code;
	public String email;
	
	public Client(int id, String name, String last_Name, String phone_Number, String address_1, String address_2, String city, String state, int postal_Code, String email)
	{
		this.id = id;
		this.name = name;
		this.last_Name = last_Name;
		this.phone_Number = phone_Number;
		this.address_1 = address_1;
		this.address_2 = address_2;
		this.city = city;
		this.state = state;
		this.postal_Code = postal_Code;
		this.email = email;
	}
}
