package application;

import controllers.AuthController;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AuthController login = new AuthController("Login", 1000, 850);
		login.login();
		
	}

}
