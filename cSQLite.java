// Program to define methods for SQLite database.

import java.sql.*;
import java.util.*;

class cSQLite extends cCRUD implements iCRUD {
	cSQLite() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:Items.db");
			statement = connection.createStatement();
			System.out.println("Connection to SQLite has been established.");
		}
		catch (ClassNotFoundException exception) {
			System.out.println("SQLite JDBC driver not found.");
			exception.printStackTrace();
		} 
		catch (SQLException exception) {
			System.out.println("Error connecting to SQLite database.");
			exception.printStackTrace();
		}
	}
}
