package controllers;

import java.util.List;

import models.Dish;
import models.DishModel;
import views.DishView;
 
public class DishController {
	
	public DishView view;
	public DishModel model;
 	
 	public DishController(String title, int frameWidth, int frameHeight) { //constructor
 		view = new DishView(title, frameWidth, frameHeight);
 		model = new DishModel();
 	}
 	
 	public void dishes() { 
 		view.dishes(); 
 	}
 	
 	public void newDish(String type) { 
 		view.newDish(type);; 
 	}
 	
 	public void searchDish() { 
 		view.searchDish();
 	}
 	
 	public void dishPage(String type) { 
 		view.dishPage(type); 
 	}
 	
 	public void editDish(String type) { 
 		view.editDish(type);; 
 	}
 	
 	//Funcion que genera platillos
 	public Object[][] dishRecovery()
 	{
 		List<Dish> dishes= model.get();
 		
		Object[][] data = new Object[dishes.size()][6];
		
		for(int i = 0; i < dishes.size(); i++)
		{
			Dish dish = dishes.get(i);
			data[i][0] = dish.name;
			data[i][1] = dish.price;
			data[i][2] = dish.description;
			data[i][3] = dish.image;
			data[i][4] = dish.status;
			data[i][5] = dish.dish_Type;
		}
		return data;
	}
 	
 	//Funcion para añadir un platillo
 	public void dishCreate(String name, double price, String description, byte[] image, boolean dish_Type)
 	{
 		model.create(name, price, description, image, dish_Type);
 	}
}