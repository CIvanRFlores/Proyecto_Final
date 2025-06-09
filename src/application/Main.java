package application;

import controllers.AuthController;

public class Main {

	public static void main(String[] args) {
		AuthController login = new AuthController("Restaurante El Manglar", 1000, 850);
		login.login();
	}

}
