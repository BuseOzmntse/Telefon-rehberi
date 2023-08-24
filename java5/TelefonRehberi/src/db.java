import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class db {
	static Connection conn;
	static Statement stat;
	
	//nesneyi tanımlamadan direkt veritabanına bağlansın diye static kullandım.
	static ResultSet connect() {
		ResultSet rs = null;
		try {
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/telefonrehberi", "root", "20B.18z,17m");
			stat = (Statement) conn.createStatement();
			rs = stat.executeQuery("SELECT * FROM rehber");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	static void add(String sql_query) {
		try {
			stat.executeUpdate(sql_query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void update(String sql_query) {
		try {
			stat.executeUpdate(sql_query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void del(String sql_query) {
		try {
			stat.executeUpdate(sql_query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static ResultSet ask(String sql_query) {
		ResultSet rs = null;
		try {
			rs = stat.executeQuery(sql_query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
