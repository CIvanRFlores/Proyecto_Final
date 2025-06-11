package controllers;

import java.util.List;

import models.Ingredient;
import models.InventoryModel;
import views.InventoryView;
 
public class InventoryController {
	
	public InventoryView view; 
	public InventoryModel model;
 	
 	public InventoryController(String title, int frameWidth, int frameHeight) { //constructor
 		view = new InventoryView(title, frameWidth, frameHeight);
 		model = new InventoryModel();
 	}
 	
 	public void inventory() {
 		view.inventory(); 
 	}
 	
 	public void searchIngredient() {
 		view.searchIngredient(); 
 	}
 	
 	public void newInventory() {
 		view.newInventory(); 
 	}

 	public void editInventory(int selectedRow) {
 		view.editInventory(selectedRow); 
 	}
 	
 	//Funcion que genera lista de clientes en formato tabla
 	public Object[][] ingredientsTable()
 	{
 		List<Ingredient> ingredients= model.get();
 		
		Object[][] data = new Object[ingredients.size()][3];
		
		for(int i = 0; i < ingredients.size(); i++)
		{
			Ingredient ingredient = ingredients.get(i);
			data[i][0] = ingredient.name;
			data[i][1] = ingredient.ammount;
			data[i][2] = ingredient.code_Ingredient;
		}
		return data;
	}
 	
 	//Funcion que busca ingredientes especificos (filtro)
 	public Object[][] searchIngredientTable(String searchText)
 	{
 		List<Ingredient> ingredients= model.search(searchText);
 		
		Object[][] data = new Object[ingredients.size()][5];
		
		for(int i = 0; i < ingredients.size(); i++)
		{
			Ingredient ingredient = ingredients.get(i);
			data[i][0] = ingredient.name;
			data[i][1] = ingredient.ammount;
			data[i][2] = ingredient.code_Ingredient;
		}
		return data;
	}

 	//Funcion para añadir un ingrediente
 	public void ingredientCreate(String code_ingredient, String name, int ammount)
 	{
 		model.create(code_ingredient, name, ammount);
 	}
 	
 	//Funcion para actualizar un ingrediente ya existente
 	public void ingredientUpdate(int row, String code_ingredient, String name, int ammount)
 	{
 		model.update(model.get().get(row).id, code_ingredient, name, ammount);
 	}
 	 	
 	//Funcion para eliminar un ingrediente existente
 	public void ingredientDelete(int row)
 	{
 		
 		model.delete(model.get().get(row).id);
 	}

 	//Funcion para buscar un ingrediente especifico
 	public Ingredient ingredientRead(int row)
 	{
 		return model.getSingle(model.get().get(row).id);
 	}
}