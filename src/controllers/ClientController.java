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
 	
 	public void searchClient() { 
 		view.searchClient();; 
 	}
 	
 	public void newClient() { 
 		view.newClient(); 
 	}
 	
 	public void editClient() { 
 		view.editClient();
 	}
 	
 	public void clientHistory() { 
 		view.clientHistory(); 
 	}
}