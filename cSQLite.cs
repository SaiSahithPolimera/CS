// Program to connect with SQLite local database.

using System;
using System.Data.SQLite;  // import SQLite library

class Sample 
{
	SQLiteConnection connection;
	SQLiteCommand command;
	SQLiteDataReader reader;
	Sample() {
		string connectionString = "Data Source=Items.db;Version=3;";
		connection = new SQLiteConnection(connectionString);
		try
		{
			connection.Open();
			Console.WriteLine("Connected to database.");
			string query = "SELECT * FROM Items;";
			command = new SQLiteCommand(query, connection);
			reader = command.ExecuteReader();
			while (reader.Read())
			{
				Console.WriteLine(reader["column1"] + "\t" + reader["column2"]);
			}
		}
		catch (Exception e)
		{
			Console.WriteLine("Error connecting to database: " + e.Message);
		}
	}
	public void AddItem() {
		Console.WriteLine("Enter Item ID: ");
		ItemID = Convert.ToInt32(Console.ReadLine());
		Console.WriteLine("Enter description: ");
		Description = Console.ReadLine();
		Console.WriteLine("Enter unit price: ");
		UnitPrice = Convert.ToInt32(Console.ReadLine());
		Console.WriteLine("Enter stock quantity: ");
		StockQuantity = Convert.ToInt32(Console.ReadLine());
		try {
			string InsertQuery = "INSERT INTO Items (ItemID, Description, UnitPrice, StockQuantity) VALUES (" + ItemID + ", '" + Description + "', " + UnitPrice + " , " + StockQuantity + ") ";
			connection.Open();
			command = new SQLiteCommand(InsertQuery, connection);
			command.ExecuteNonQuery();
			connection.Close();
		}
		catch (SQLiteException ex) {
			Console.WriteLine(ex.ToString());
		}
	}

	public void ShowAll() {
		try {
			string SelectQuery = "SELECT * FROM Items";
			connection.Open();
			command = new SQLiteCommand(SelectQuery, connection);
			SQLiteDataAdapter dataAdapter = new SQLiteDataAdapter(command);
			dataAdapter.Fill(table);
			DisplayData(table);
		}
		catch (SQLiteException ex) {
			Console.WriteLine(ex.ToString());
		}
	}

	private void DisplayData(DataTable table)
	{
		foreach (DataRow row in table.Rows) {
			Console.WriteLine("Item ID: " + row["ItemID"]);
			Console.WriteLine("Description: " + row["Description"]);
			Console.WriteLine("Unit Price: " + row["UnitPrice"]);
			Console.WriteLine("Stock quantity: " + row["StockQuantity"]);
		}
		Console.WriteLine("\n");
	}	

	public void Update() {
		ItemID = ReadItemID();
		Description = Console.ReadLine();
		Console.WriteLine("Enter new unit price: ");
		UnitPrice = Convert.ToInt32(Console.ReadLine());
		Console.WriteLine("Enter new stock quantity: ");
		StockQuantity = Convert.ToInt32(Console.ReadLine());
		try {
			string UpdateQuery = "UPDATE Items SET Description='" + Description + "', UnitPrice=" + UnitPrice + ", StockQuantity=" + StockQuantity + " WHERE ItemID=" + ItemID;
			connection.Open();
			command = new SQLiteCommand(UpdateQuery, connection);
			command.ExecuteNonQuery();
			connection.Close();
		}
		catch (SQLiteException ex) {
			Console.WriteLine(ex.ToString());
		}
	}

	private int ReadItemID (){
		ItemID = Convert.ToInt32(Console.ReadLine());
		Console.WriteLine("Enter new description: ");
		return ItemID;
	}
	public void Remove() {
		Console.WriteLine("Enter Item ID: ");
		ItemID = Convert.ToInt32(Console.ReadLine());
		try {
			string UpdateQuery = "DELETE FROM Items WHERE ItemID=" + ItemID;
			connection.Open();
			command = new SQLiteCommand(UpdateQuery, connection);
			command.ExecuteNonQuery();
			connection.Close();
		}
		catch (SQLiteException ex) {
			Console.WriteLine(ex.ToString());
		}
	}
}


class cSQLite
{
	static void Main(string[] args)
	{

		string connectionString = "Data Source=Items.db;Version=3;";
		SQLiteConnection connection = new SQLiteConnection(connectionString);
		try
		{
			connection.Open();
			Console.WriteLine("Connected to database.");
			string query = "SELECT * FROM Items;";
			SQLiteCommand command = new SQLiteCommand(query, connection);
			SQLiteDataReader reader = command.ExecuteReader();
			while (reader.Read())
			{
				Console.WriteLine(reader["column1"] + "\t" + reader["column2"]);
			}
		}
		catch (Exception e)
		{
			Console.WriteLine("Error connecting to database: " + e.Message);
		}
		finally
		{
			connection.Close();
			Console.WriteLine("Connection closed.");
		}
	}
}
