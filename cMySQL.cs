// Program to connect with MySQL database.

using MySql.Data.MySqlClient;
using System;
using iCRUD;
using cCRUD;

class cMySQL : cCRUD.cCRUD, iCRUD.iCRUD
{
	public static void Main(string[] args) {
		MySqlConnection conn;
		string myConnectionString;
		myConnectionString = "server=138.68.140.83;uid=Sahith;" +"pwd=Sahith@369;database=dbSahith";
		try {
			conn = new MySqlConnection();
			conn.ConnectionString = myConnectionString;
			conn.Open();
			Console.WriteLine("Connected to MySQL!");
			conn.Close();
		}
		catch (MySqlException ex) {
			Console.WriteLine(ex.ToString());
		}
		Console.WriteLine("Done.");
	}
}
