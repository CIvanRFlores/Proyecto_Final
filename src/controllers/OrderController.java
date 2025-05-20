package controllers;

import views.OrderView;
 
public class OrderController {
	
	public OrderView view; //crear objeto de la vista
 	
 	public OrderController(String title, int frameWidth, int frameHeight) { //constructor
 		view = new OrderView(title, frameWidth, frameHeight);
 	}
 	
 	public void orders(){ //llamar al método ordenes de la vista
 		view.orders(); 
 	}
 	
}