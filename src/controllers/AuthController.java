package controllers;

import views.AuthView;
 
public class AuthController {
	
	public AuthView view; //crear objeto de la vista
 	
 	public AuthController(String title, int frameWidth, int frameHeight) { //constructor
 		view = new AuthView(title, frameWidth, frameHeight);
 	}
 	
 	public void login(){ //llamar al método login de la vista
 		view.login(); 
 	}
 	
}