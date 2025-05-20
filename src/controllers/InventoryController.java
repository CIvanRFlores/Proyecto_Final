package controllers;

import views.InventoryView;
 
public class InventoryController {
	
	public InventoryView view; //crear objeto de la vista
 	
 	public InventoryController(String title, int frameWidth, int frameHeight) { //constructor
 		view = new InventoryView(title, frameWidth, frameHeight);
 	}
 	
 	public void inventory(){ //llamar al método inventario de la vista
 		view.inventory(); 
 	}
 	
}