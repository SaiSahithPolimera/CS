// CRUD operation on Items using remote database. 

import java.sql.*;
import java.util.*;

class Supermarket {
	int ItemID;
	String Description;
	int UnitPrice;
	int StockQuantity;
	public Connection connection;
	Statement statement;
	Scanner input = new Scanner(System.in);
	ResultSet resultSet;
	Supermarket() {
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

	void AddItem() {
		System.out.print("Enter Item ID: ");
		ItemID = input.nextInt();
		System.out.print("Enter description of item: ");
		Description = input.next();
		System.out.print("Enter Unit price: ");
		UnitPrice = input.nextInt();
		System.out.print("Enter stock quantity: ");
		StockQuantity = input.nextInt();
		try {
			String InsertQuery = "INSERT INTO Items (ItemID, Description, UnitPrice, StockQuantity) VALUES (" + ItemID + ", '" + Description + "', " + UnitPrice + " , " + StockQuantity + ") ";
			statement.executeUpdate(InsertQuery);
		}
		catch(SQLException exce) {
			System.out.println(exce);
		}
	}

	void ShowAllItems() {
		try {
			String SelectAllItemsQuery = "SELECT *FROM Items";
			resultSet = statement.executeQuery(SelectAllItemsQuery);
			PrintRecord(resultSet);
		}
		catch(SQLException exce) {
			System.out.println(exce);
		}
	}

	private void PrintRecord(ResultSet resultSet) {
		try {
			System.out.printf("----------------------------------------------------------------------------------------------%n");
			System.out.printf("| %-5s | %-51s | %11s | %14s| %n", "ItemID", "Description", "UnitPrice", "StockQuantity");
			System.out.printf("----------------------------------------------------------------------------------------------%n");
			while (resultSet.next()) {
				System.out.printf("| %-6d | %-51s | %11d | %14d| %n", resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
			}
			System.out.printf("----------------------------------------------------------------------------------------------%n");
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
	}

	void UpdateDetails() {
		ItemID = ReadItemID();
		try {
			if(CheckItemID(ItemID)) {
				System.out.print("Enter description: ");
				Description = input.next();
				System.out.print("Enter unit price: ");
				UnitPrice = input.nextInt();
				System.out.print("Enter stock quantity: ");
				StockQuantity = input.nextInt();
				String UpdateQuery = "UPDATE Items SET Description = ?, UnitPrice = ?, StockQuantity = ? WHERE ItemID = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(UpdateQuery);
				preparedStatement.setString(1, Description);
				preparedStatement.setInt(2, UnitPrice);
				preparedStatement.setInt(3, StockQuantity);
				preparedStatement.setInt(4, ItemID);
				// connection.commit();
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected == 1) {
					System.out.println("Details updated successfully!");
				}
			}
		}
		catch (SQLException exce) {
			System.out.println(exce);
		}
	}

	void RemoveItem() {
		ItemID = ReadItemID();
		try {
			if(CheckItemID(ItemID)) {
				String UpdateQuery = "DELETE FROM Items WHERE ItemID = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(UpdateQuery);
				preparedStatement.setInt(1, ItemID);
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected == 1) {
					System.out.println("Item removed successfully!");
				}
			}
		}
		catch (SQLException exce) {
			System.out.println(exce);
		}
	}

	void SearchItem() {
		ItemID = ReadItemID();
		try {
			String SelectItemQuery = "SELECT *FROM Items WHERE ItemID = " + ItemID + "; ";
			resultSet = statement.executeQuery(SelectItemQuery);
			PrintRecord(resultSet);
		}
		catch (SQLException exce) {
			System.out.println(exce);
		}
	}

	void SortByItemNames() {
		try {
			String SortItemsQuery = "SELECT ItemID, Description, UnitPrice, StockQuantity FROM Items ORDER BY Description; ";
			resultSet = statement.executeQuery(SortItemsQuery);
			PrintRecord(resultSet);
		}
		catch (SQLException exce) {
			System.out.println(exce);
		}
	}

	private boolean CheckItemID(int ItemID) {
		boolean Status = false;
		try {
			String SelectItemQuery = "SELECT *FROM Items WHERE " + "Items.ItemID  = " + ItemID + "; ";
			resultSet = statement.executeQuery(SelectItemQuery);
			Status = resultSet.next();
		}
		catch (SQLException exce) {
			System.out.println(exce);
		}
		return Status;
	}

	private int ReadItemID() {
		int ItemID;
		System.out.print("Enter Item ID: ");
		ItemID = input.nextInt();
		return ItemID;
	}

	void CloseConnection() {
		try {
			connection.close();
		}
		catch(SQLException exception) {
			System.out.println(exception);
		}
	}
}

class Items {
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		Supermarket Item = new Supermarket();
		int Choice;
		do {
			System.out.println("1. Add Item");
			System.out.println("2. Show all items");
			System.out.println("3. Update item details");
			System.out.println("4. Search an item");
			System.out.println("5. Sort by item names");
			System.out.println("6. Remove item");
			System.out.println("7. Exit");
			System.out.println("Enter your choice: ");
			Choice = input.nextInt();
			switch (Choice) {
				case 1:
					Item.AddItem();
					break;
				case 2:
					Item.ShowAllItems();
					break;
				case 3:
					Item.UpdateDetails();
					break;
				case 4:
					Item.SearchItem();
					break;
				case 5:
					Item.SortByItemNames();
					break;
				case 6:
					Item.RemoveItem();
					break;
				case 7:
					break;
				default : System.out.println("Enter a valid input!");
			}
			System.out.println("Enter any number to continue or 7 to exit: ");
			Choice = input.nextInt();
		} while (Choice != 7);
		Item.CloseConnection();
	}
}