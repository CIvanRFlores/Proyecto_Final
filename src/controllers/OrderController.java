package controllers;

import views.OrderView;
 
public class OrderController {
	
	public OrderView view; 
 	
 	public OrderController(String title, int frameWidth, int frameHeight) { //constructor
 		view = new OrderView(title, frameWidth, frameHeight);
 	}
 	
 	public void orders(){ 
 		view.orders(); 
 	}
 	
 	public void searchOrder(){ 
 		view.searchOrder(); 
 	}
 	
 	public void newOrder(){ 
 		view.newOrder(); 
 	}
 	
}