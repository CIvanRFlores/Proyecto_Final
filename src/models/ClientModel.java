package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import customClasses.DBConnection;

public class ClientModel 
{
	private ArrayList<Client> clients = new ArrayList<Client>();
	
	public  ClientModel(){
	}
	
	public ArrayList<Client> get()	//Obtener lista de clientes de la base de datos
	{
		String query = "SELECT * FROM `Client`";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try 
		{
			conn = DBConnection.connected();
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery(query);
			
			while(rs.next())
			{
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String last_name = rs.getString(3);
				String phone_Number= rs.getString(4);
				String address_1 = rs.getString(5);
				String address_2 = rs.getString(6);
				String city = rs.getString(7);
				String state = rs.getString(8);
				Integer postal_Code = rs.getInt(9);
				String email = rs.getString(10);
				
				clients.add(new Client(id, name, last_name, phone_Number, address_1, address_2, city, state, postal_Code, email));
			}
			rs.close();
			return clients;
			
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try
			{
				ps.close();
				conn.close();
			}catch(Exception e) {}
		}
		return clients;
	}
	
	//Modificar cliente existente
	public boolean create(String name, String last_Name, String countryCodeCmbBx, String phone_Number, String address_1, String address_2, String city, String state, String postal_Code, String email)
	{
		String query = "INSERT INTO Client (name, last_name, phone_number, address_1, address_2, city, state, postal_code, email) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try 
		{
			conn = DBConnection.connected();

			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, last_Name);
			ps.setString(3, countryCodeCmbBx + phone_Number);
			ps.setString(4, address_1);
			ps.setString(5, address_2);
			ps.setString(6, city);
			ps.setString(7, state);
			ps.setString(8, postal_Code);
			ps.setString(9, email);
			
			int rs = ps.executeUpdate();
			
			if(rs > 0)
			{
				
				return true;
			}
			
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try
			{
				ps.close();
				conn.close();
			}catch(Exception e) {}
		}
		return false;
	}
	
	//Modifica cliente ya existente
	public boolean update(int id, String name, String last_Name, String countryCodeCmbBx, String phone_Number, String address_1, String address_2, String city, String state, String postal_Code, String email)
	{
		String query = "UPDATE Client SET "
				+ "name = ?, last_name = ?, phone_number = ?, address_1 = ?, address_2 = ?, city = ?, state = ?, postal_code = ?, email = ? WHERE id_client = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try 
		{
			conn = DBConnection.connected();

			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, last_Name);
			ps.setString(3, countryCodeCmbBx + phone_Number);
			ps.setString(4, address_1);
			ps.setString(5, address_2);
			ps.setString(6, city);
			ps.setString(7, state);
			ps.setString(8, postal_Code);
			ps.setString(9, email);
			ps.setInt(10, id);
			
			int rs = ps.executeUpdate();
			
			if(rs > 0)
			{
				return true;
			}
			
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try
			{
				ps.close();
				conn.close();
			}catch(Exception e) {}
		}
		return false;
	}
	
	//Elimina un cliente ya existente
	public boolean delete(int id)
	{
		String query = "DELETE FROM Client WHERE `id_client` = ?" ;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try 
		{
			conn = DBConnection.connected();

			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			int rs = ps.executeUpdate();
			
			if(rs > 0)
			{
				return true;
			}
			
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try
			{
				ps.close();
				conn.close();
			}catch(Exception e) {}
		}
		return false;
	}
	
	public ArrayList<Client> search(String searchText)	//Obtener lista de clientes especificos de la base de datos
	{
		ArrayList<Client> clients = new ArrayList<>();
		String query = "SELECT * FROM `Client` "
				+ "WHERE LOWER(name) LIKE ? OR LOWER(last_name) LIKE ? OR LOWER(CONCAT(name, ' ', last_name)) LIKE ? OR phone_number LIKE ? OR LOWER(address_1) LIKE ? OR LOWER(address_2) LIKE ? OR LOWER(email) LIKE ?";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try 
		{
			conn = DBConnection.connected();
			ps = conn.prepareStatement(query);
			
			String searchTextLwr = "%" + searchText.toLowerCase() + "%";
			
			for(int i = 1; i <= 7; i++)
			{
				ps.setString(i, searchTextLwr);
			}
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String last_name = rs.getString(3);
				String phone_Number= rs.getString(4);
				String address_1 = rs.getString(5);
				String address_2 = rs.getString(6);
				String city = rs.getString(7);
				String state = rs.getString(8);
				Integer postal_Code = rs.getInt(9);
				String email = rs.getString(10);
				
				clients.add(new Client(id, name, last_name, phone_Number, address_1, address_2, city, state, postal_Code, email));
			}
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try
			{
				ps.close();
				conn.close();
			}catch(Exception e) {}
		}
		return clients;
	}
	
	public Client getSingle(int row)	//Obtener datos de un cliente de la base de datos
	{
		Client client = null;
		String query = "SELECT * FROM `Client` WHERE id_client = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try 
		{
			conn = DBConnection.connected();
			ps = conn.prepareStatement(query);
			ps.setInt(1, row);
			ResultSet rs = ps.executeQuery();
			
			
			if(rs.next())
			{
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String last_name = rs.getString(3);
				String phone_Number= rs.getString(4);
				String address_1 = rs.getString(5);
				String address_2 = rs.getString(6);
				String city = rs.getString(7);
				String state = rs.getString(8);
				Integer postal_Code = rs.getInt(9);
				String email = rs.getString(10);
				
				client = new Client(id, name, last_name, phone_Number, address_1, address_2, city, state, postal_Code, email);
			}
			rs.close();
			return client;
			
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try
			{
				ps.close();
				conn.close();
			}catch(Exception e) {}
		}
		return client;
	}
}
