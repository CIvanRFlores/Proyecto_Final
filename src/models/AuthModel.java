package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import customClasses.DBConnection;

public class AuthModel {

	public  AuthModel() {
		//Constructor vacío
	}
	
	public boolean login(String name, String password) {	//clase para iniciar sesión con base de datos
	
	//Query que guarda el comando para la base de datos
	 String query = "select * from Employee where name = ? and password = ?";
	 Connection conn = null;
	 PreparedStatement ps = null;
	 
	 try {
		 //Conexión a la base de datos
		 conn = DBConnection.connected();
		 
		 //PreparedStatement para evitar SQL Injection
		 ps = conn.prepareStatement(query);
		 ps.setString(1, name);
		 ps.setString(2, password);
		 
		 //Imprime query en consola (innecesario)
		 System.out.println(ps);
		 
		 //Almacena los resultados de busqueda
		 ResultSet rs = ps.executeQuery();
		 
		 //Condicional que verifica si existe el usuario y contraseña en la BD
		 if(rs.next()) {
			 return true;
		 }
		 
		 rs.close();
	 }catch (Exception e) {
		 
	 }finally {
		 try {
			 if(ps != null) 
				 ps.close();
			 if(conn != null)
				conn.close(); 
		 }catch(Exception e) {}
	 }
	 
	 return false;
	}
}
