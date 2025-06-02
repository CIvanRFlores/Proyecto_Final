package controllers;

import views.AuthView;
 
public class AuthController {
	
	public AuthView view;
 	
 	public AuthController(String title, int frameWidth, int frameHeight) { //constructor
 		view = new AuthView(title, frameWidth, frameHeight);
 	}
 	
 	public void login() {
 		view.login(); 
 	}
 	
}