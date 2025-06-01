package controllers;

import views.DishView;
 
public class DishController {
	
	public DishView view; 
 	
 	public DishController(String title, int frameWidth, int frameHeight) { //constructor
 		view = new DishView(title, frameWidth, frameHeight);
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
}