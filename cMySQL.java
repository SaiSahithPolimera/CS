// Program to define methods for MySQL database.

import java.sql.*;
import java.util.*;

class cMySQL extends cCRUD implements iCRUD {
	cMySQL() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://138.68.140.83:3306/dbSahith", "Sahith", "Sahith@369");
			statement = connection.createStatement();
			System.out.println("Connected successfully!");
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}