package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseHandler {

	Connection connect=null;

	public Connection getConnection(){

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect =DriverManager.getConnection("jdbc:mysql://localhost:3306/taskconnection?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false","root","12345678"); 
			}
		catch( SQLException | ClassNotFoundException e){
           	e.printStackTrace();
           	((SQLException) e).getErrorCode();
		}
		
		return connect;
	}
	
}