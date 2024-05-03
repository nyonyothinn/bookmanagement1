package bookmanagementsystem.persistant.DAO;

import java.sql.*;

public class MyConnection {
	
	static Connection con=null;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String db="jdbc:mysql://localhost:3306/bookdb_1_m";
			String username="root";
			String password="root";
			con=DriverManager.getConnection(db,username,password);						
		}catch(ClassNotFoundException e) {
			System.out.println("Driver class not found "+e);
		}catch(SQLException e) {
			System.out.println("Sql Exception "+e);
		}
		return con;
	}
}
