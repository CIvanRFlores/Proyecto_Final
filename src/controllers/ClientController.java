package controllers;

import views.ClientView;
 
public class ClientController {
	
	public ClientView view; //crear objeto de la vista
 	
 	public ClientController(String title, int frameWidth, int frameHeight) { //constructor
 		view = new ClientView(title, frameWidth, frameHeight);
 	}
 	
 	public void clients(){ //llamar al método clientes de la vista
 		view.clients(); 
 	}
 	
}