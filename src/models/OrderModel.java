package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import customClasses.DBConnection;

public class OrderModel 
{
	public OrderModel()
	{
	}
	
	//Obtener lista de ordenes generadas
	public ArrayList<Order> get()
	{
		ArrayList<Order> orders = new ArrayList<Order>();
		String query = "SELECT * FROM `Order`";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try 
		{
			conn = DBConnection.connected();
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery(query);
			orders.clear();
			
			while(rs.next())
			{
				int id = rs.getInt(1);
				int id_Dish = rs.getInt(2);
				int id_Client = rs.getInt(3);
				String table_Number = rs.getString(4);
				String total = rs.getString(5);
				String ammount = rs.getString(6);
				boolean order_Type = rs.getBoolean(7);
				String estimated_Time = rs.getString(8);
				String instructions = rs.getString(9);
				
				orders.add(new Order(id, id_Dish, id_Client, table_Number, total, ammount, order_Type, estimated_Time, instructions));
			}
			rs.close();
			return orders;
			
		
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
		return orders;
	}
		
	//Generar orden nueva
	public boolean create(int id_Dish, int id_Client, String table_Number, String total, String ammount,  boolean order_Type, String estimated_Time, String instructions)
	{
		String query = "INSERT INTO `Order` (id_dish, id_client, table_number, price, ammount, order_type, estimated_time, Instructions) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try 
		{
			conn = DBConnection.connected();

			ps = conn.prepareStatement(query);
			ps.setInt(1, id_Dish);
			ps.setInt(2, id_Client);
			ps.setString(3, table_Number);
			ps.setString(4, total);
			ps.setString(5, ammount);
			ps.setBoolean(6, order_Type);
			ps.setString(7, estimated_Time);
			ps.setString(8, instructions);
			
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
		
	//Modifica orden ya existente
	public boolean update(int id, int id_Dish, int id_Client, String table_Number, String total, String ammount,  boolean order_Type, String estimated_Time, String instructions)
	{
		String query = "UPDATE `Order` SET "
				+ "id_dish = ?, id_client = ?, table_number = ?, price = ?, ammount = ?, order_type = ?, estimated_time = ?, Instructions = ? WHERE id_order = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try 
		{
			conn = DBConnection.connected();

			ps = conn.prepareStatement(query);
			ps.setInt(1, id_Dish);
			ps.setInt(2, id_Client);
			ps.setString(3, table_Number);
			ps.setString(4, total);
			ps.setString(5, ammount);
			ps.setBoolean(6, order_Type);
			ps.setString(7, estimated_Time);
			ps.setString(8, instructions);
			ps.setInt(9, id);
			
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
		
	//Elimina una orden ya existente
	public boolean delete(int id)
	{
		String query = "DELETE FROM `Order` WHERE `id_order` = ?" ;
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
		

	//Obtener lista de ordenes por medio de filtro
	public ArrayList<Order> search(String searchText)
	{
		ArrayList<Order> orders= new ArrayList<>();
		String query = "SELECT * FROM `Order` "
				+ "WHERE id_client LIKE ? OR table_number LIKE ?";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try 
		{
			conn = DBConnection.connected();
			ps = conn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				int id = rs.getInt(1);
				int id_Dish = rs.getInt(2);
				int id_Client = rs.getInt(3);
				String table_Number = rs.getString(4);
				String total = rs.getString(5);
				String ammount = rs.getString(6);
				boolean order_Type = rs.getBoolean(7);
				String estimated_Time = rs.getString(8);
				String instructions = rs.getString(9);
				
				orders.add(new Order(id, id_Dish, id_Client, table_Number, total, ammount, order_Type, estimated_Time, instructions));
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
		return orders;
	}
		
	//Obtener datos de una orden de la base de datos
	public Order getSingle(int row)
	{
		Order order= null;
		String query = "SELECT * FROM `Order` WHERE id_order = ?";
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
				int id_Dish = rs.getInt(2);
				int id_Client = rs.getInt(3);
				String table_Number = rs.getString(4);
				String total = rs.getString(5);
				String ammount = rs.getString(6);
				boolean order_Type = rs.getBoolean(7);
				String estimated_Time = rs.getString(8);
				String instructions = rs.getString(9);
				
				order = new Order(id, id_Dish, id_Client, table_Number, total, ammount, order_Type, estimated_Time, instructions);
			}
			rs.close();
			return order;
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
		return order;
	}
}
