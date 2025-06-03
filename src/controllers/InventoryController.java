package controllers;

import views.InventoryView;
 
public class InventoryController {
	
	public InventoryView view; 
 	
 	public InventoryController(String title, int frameWidth, int frameHeight) { //constructor
 		view = new InventoryView(title, frameWidth, frameHeight);
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

 	public void editInventory() {
 		view.editInventory(); 
 	}
}