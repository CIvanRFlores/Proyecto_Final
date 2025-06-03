package customClasses;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection 
{
	//Metodo de conexion a la base de datos
	public static Connection connected()
	{
		try
		{
			return DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_Restaurante_El_Manglar", "freedb_civanrflores", "Pm6kE#W!3sQyK5s");
		}catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
