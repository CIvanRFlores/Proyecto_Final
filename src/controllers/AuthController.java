package controllers;

import views.AuthView;
 
public class AuthController {
	
	public AuthView view; //crear objeto de la vista
 	
 	public AuthController() { //constructor
 		view = new AuthView("Login", 1000, 850);
 	}
 	
 	public void login(){ //llamar al método login de la vista
 		view.login(); 
 	}
 	
}