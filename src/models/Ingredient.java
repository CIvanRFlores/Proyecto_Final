package models;

public class Ingredient		//Clase ingrediente
{
	public int id;
	public String code_Ingredient;
	public String name;
	public int ammount;
	
	public Ingredient(int id, String code_Ingredient, String name, int ammount)
	{
		this.id = id;
		this.code_Ingredient = code_Ingredient;
		this.name = name;
		this.ammount = ammount;
	}
}
