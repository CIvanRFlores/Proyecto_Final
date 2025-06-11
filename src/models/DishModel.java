package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import customClasses.DBConnection;

public class DishModel 
{
	public DishModel()
	{
	}
	
	//Obtener los platillos almacenados
	public ArrayList<Dish> get()
	{
		ArrayList<Dish> dishes= new ArrayList<Dish>();
		String query = "SELECT * FROM `Dish`";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try 
		{
			conn = DBConnection.connected();
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery(query);
			dishes.clear();
			
			while(rs.next())
			{
				int id = rs.getInt(1);
				String name= rs.getString(2);
				double price = rs.getDouble(3);
				String description= rs.getString(4);
				byte[] image= rs.getBytes(5);
				boolean status= rs.getBoolean(6);
				boolean dish_Type= rs.getBoolean(7);
				
				dishes.add(new Dish(id, name, price, description, image, status, dish_Type));
			}
			rs.close();
			return dishes;
			
		
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
		return dishes;
	}
		
	//Ingresar platillos nuevos
	public boolean create(String name, double price, String description, byte[] image, boolean dish_Type)
	{
		String query = "INSERT INTO Dish (name, price, description, image, dish_type) "
				+ "VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try 
		{
			conn = DBConnection.connected();

			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setDouble(2, price);
			ps.setString(3, description);
			ps.setBytes(4, image);
			ps.setBoolean(5, dish_Type);
			
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
	
	//Modifica platillo ya existente
	public boolean update(int id, String name, double price, String description, byte[] image, boolean dish_Type)
	{
		String query = "UPDATE Client SET "
				+ "name = ?, price= ?, description = ?, image = ?, dish_type = ?, WHERE id_dish = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try 
		{
			conn = DBConnection.connected();

			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setDouble(2, price);
			ps.setString(3, description);
			ps.setBytes(3, image);
			ps.setBoolean(3, dish_Type);
			ps.setInt(4, id);
			
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
			
	//Elimina un platillo ya existente
	public boolean delete(int id)
	{
		String query = "DELETE FROM Client WHERE `id_dish` = ?" ;
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
	

	//Obtener los platillos por medio de filtro
	public ArrayList<Dish> search(String searchText)
	{
		ArrayList<Dish> dishes = new ArrayList<>();
		String query = "SELECT * FROM `Dish` "
				+ "WHERE LOWER(name) LIKE ?";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try 
		{
			conn = DBConnection.connected();
			ps = conn.prepareStatement(query);
			
			String searchTextLwr = "%" + searchText.toLowerCase() + "%";
			
			ps.setString(1, searchTextLwr);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				int id = rs.getInt(1);
				String name= rs.getString(2);
				double price = rs.getDouble(3);
				String description= rs.getString(4);
				byte[] image= rs.getBytes(5);
				boolean status= rs.getBoolean(6);
				boolean dish_Type= rs.getBoolean(7);
				
				dishes.add(new Dish(id, name, price, description, image, status, dish_Type));
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
		return dishes;
	}
}
