package controllers;

import views.ClientView;
 
public class ClientController {
	
	public ClientView view; 
 	
 	public ClientController(String title, int frameWidth, int frameHeight) { 
 		view = new ClientView(title, frameWidth, frameHeight);
 	}
 	
 	public void clients() { 
 		view.clients(); 
 	}
 	
}