package controllers;

import java.util.List;

import models.Client;
import models.ClientModel;
import views.ClientView;
 
public class ClientController {
	
	public ClientView view;
	public ClientModel model;
 	
 	public ClientController(String title, int frameWidth, int frameHeight) { 
 		view = new ClientView(title, frameWidth, frameHeight);
 		model = new ClientModel();
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
 	
 	//Funcion que genera lista de clientes en formato tabla
 	public Object[][] clientsTable()
 	{
 		List<Client> clients = model.get();
 		
		Object[][] info = new Object[clients.size()][5];
		
		for(int i = 0; i < clients.size(); i++)
		{
			Client client = clients.get(i);
			info[i][0] = client.name;
			info[i][1] = client.address_1;
			info[i][2] = client.phone_Number;
			info[i][3] = client.email;
			info[i][4] = "Accion";
		}
		return info;
	}
 	
 	//Funcion para crear un cliente
 	public void clientCreate(String name, String last_name, String CountryCodeCmbBx, String phone_Number, String address_1, String address_2, String city, String state, String postal_Code, String email)
 	{
 		model.create(name, last_name, CountryCodeCmbBx, phone_Number, address_1, address_2, city, state, postal_Code, email);
 	}
}