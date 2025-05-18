package controllers;

import views.DishView;
 
public class DishController {
	
	public DishView view; //crear objeto de la vista
 	
 	public DishController(String title, int frameWidth, int frameHeight) { //constructor
 		view = new DishView(title, frameWidth, frameHeight);
 	}
 	
 	public void dishes(){ //llamar al método platillo de la vista
 		view.dishes(); 
 	}
 	
}