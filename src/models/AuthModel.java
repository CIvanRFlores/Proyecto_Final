package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthModel {

	public  AuthModel() {
		//constructor vacio
	}
	
	public boolean login(String name, String password) {	//clase para iniciar secion con base de datos
	
	//query que guarda el comando para la base de datos
	 String query = "select * from Employee where name = ? and password = ?";
	 Connection conn = null;
	 PreparedStatement ps = null;
	 
	 try {
		 //Conexion a la base de datos
		 conn = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_Restaurante_El_Manglar", "freedb_civanrflores", "Pm6kE#W!3sQyK5s");
		 //PreparedStatement para evitar SQL Injection
		 ps = conn.prepareStatement(query);
		 ps.setString(1, name);
		 ps.setString(2, password);
		 
		 //imprime query en consola (inesesario)
		 System.out.println(ps);
		 
		 //almacena los resultados de busqueda
		 ResultSet rs = ps.executeQuery();
		 
		 //condicional que verifica si existe el usuario y contraseña en la BD
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
