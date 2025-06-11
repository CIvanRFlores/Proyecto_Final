package controllers;

import java.util.ArrayList;
import java.util.List;

import customClasses.RoundPanel;
import models.Order;
import models.OrderModel;
import views.OrderView;
 
public class OrderController {
	
	public OrderView view; 
	public OrderModel model;
 	
 	public OrderController(String title, int frameWidth, int frameHeight) { //constructor
 		view = new OrderView(title, frameWidth, frameHeight);
 		model = new OrderModel();
 	}
 	
 	public void orders(){ 
 		ArrayList<Order> orders = model.get();
        view.orders(orders);
 	}
 	
 	public void searchOrder(){ 
 		view.searchOrder(); 
 	}
 	
 	public void newOrder(){ 
 		view.newOrder(); 
 	}
 	
 	public void editOrder(int editWindow){ 
 		view.editOrder(editWindow); 
 	}
 	
}