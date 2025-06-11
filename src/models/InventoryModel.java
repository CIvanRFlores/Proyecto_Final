package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import customClasses.DBConnection;

public class InventoryModel
{
	public InventoryModel(){
	}
	
	//Obtener lista de ingredientes almacenados en el inventario
	public ArrayList<Ingredient> get()
	{
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		String query = "SELECT * FROM `Inventory`";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try 
		{
			conn = DBConnection.connected();
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery(query);
			ingredients.clear();
			
			while(rs.next())
			{
				int id = rs.getInt(1);
				String code_Ingredient= rs.getString(2);
				String name = rs.getString(3);
				int ammount= rs.getInt(4);
				
				ingredients.add(new Ingredient(id, code_Ingredient, name, ammount));
			}
			rs.close();
			return ingredients;
			
		
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
		return ingredients;
	}
	
	//Ingresar ingrediente neevo
		public boolean create(String code_Ingredient, String name, int ammount)
		{
			String query = "INSERT INTO Inventory (code_ingredient, name, ammount) "
					+ "VALUES (?, ?, ?)";
			Connection conn = null;
			PreparedStatement ps = null;
			
			try 
			{
				conn = DBConnection.connected();

				ps = conn.prepareStatement(query);
				ps.setString(1, code_Ingredient);
				ps.setString(2, name);
				ps.setInt(3, ammount);
				
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
		
		//Modifica ingrediente ya existente
		public boolean update(int id, String code_Ingredient, String name, int ammount)
		{
			String query = "UPDATE Inventory SET "
					+ "code_ingredient = ?, name= ?, ammount= ?, WHERE id_ingredient = ?";
			Connection conn = null;
			PreparedStatement ps = null;
			
			try 
			{
				conn = DBConnection.connected();

				ps = conn.prepareStatement(query);
				ps.setString(1, code_Ingredient);
				ps.setString(2, name);
				ps.setInt(3, ammount);
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
		
		//Elimina un ingrediente ya existente
		public boolean delete(int id)
		{
			String query = "DELETE FROM Inventory WHERE `id_ingredient` = ?" ;
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
		

		//Obtener lista de ingredientes por medio de filtro
		public ArrayList<Ingredient> search(String searchText)
		{
			ArrayList<Ingredient> ingredients = new ArrayList<>();
			String query = "SELECT * FROM `Inventory` "
					+ "WHERE code_ingredient LIKE ? OR LOWER(name) LIKE ?";
			Connection conn = null;
			PreparedStatement ps = null;
			
			try 
			{
				conn = DBConnection.connected();
				ps = conn.prepareStatement(query);
				
				String searchTextLwr = "%" + searchText.toLowerCase() + "%";
				
				ps.setString(2, searchTextLwr);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
					int id = rs.getInt(1);
					String code_Ingredient = rs.getString(2);
					String name = rs.getString(3);
					int ammount= rs.getInt(4);
					
					ingredients.add(new Ingredient(id, code_Ingredient, name, ammount));
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
			return ingredients;
		}
		
		public Ingredient getSingle(int row)	//Obtener datos de un cliente de la base de datos
		{
			Ingredient ingredient= null;
			String query = "SELECT * FROM `Inventory` WHERE id_ingredient = ?";
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
					String code_Ingredient = rs.getString(2);
					String name = rs.getString(3);
					int ammount = rs.getInt(4);
					
					ingredient= new Ingredient(id, code_Ingredient, name, ammount);
				}
				rs.close();
				return ingredient;
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
			return ingredient;
		}
}
