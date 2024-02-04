// Program to create a class implementing all methods in iCRUD interface.

import java.sql.*;
import java.util.*;

class cCRUD implements iCRUD {
	int ItemID;
	String Description;
	int UnitPrice;
	int StockQuantity;
	Connection connection;
	Statement statement;
	Scanner input = new Scanner(System.in);
	ResultSet resultSet;
	public void AddItem() {
		System.out.println("Enter Item ID: ");
		ItemID = input.nextInt();
		Description = ReadDescription();
		UnitPrice = ReadUnitPrice();
		StockQuantity = ReadStockQuantity();
		try {
			String InsertQuery = "INSERT INTO Items (ItemID, Description, UnitPrice, StockQuantity) VALUES (" + ItemID + ", '" + Description + "', " + UnitPrice + " , " + StockQuantity + ") ";
			statement.executeUpdate(InsertQuery);
		}
		catch(SQLException exce) {
			System.out.println(exce);
		}
	}

	public void ShowAll() {
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

	public void Update() {
		ItemID = ReadItemID();
		try {
			if(CheckItemID(ItemID)) {
				Description = ReadDescription();
				UnitPrice = ReadUnitPrice();
				StockQuantity = ReadStockQuantity();
				String UpdateQuery = "UPDATE Items SET Description = ?, UnitPrice = ?, StockQuantity = ? WHERE ItemID = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(UpdateQuery);
				preparedStatement.setString(1, Description);
				preparedStatement.setInt(2, UnitPrice);
				preparedStatement.setInt(3, StockQuantity);
				preparedStatement.setInt(4, ItemID);
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

	private int ReadUnitPrice() {
		System.out.print("Enter unit price: ");
		UnitPrice = input.nextInt();
		return UnitPrice;
	}

	private String ReadDescription() {
		System.out.print("Enter description: ");
		Description = input.next();
		return Description;
	}

	private int ReadStockQuantity() {
		System.out.print("Enter stock quantity: ");
		StockQuantity = input.nextInt();
		return StockQuantity;
	}

	public void Search() {
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

	public void Sort() {
		try {
			String SortItemsQuery = "SELECT ItemID, Description, UnitPrice, StockQuantity FROM Items ORDER BY Description; ";
			resultSet = statement.executeQuery(SortItemsQuery);
			PrintRecord(resultSet);
		}
		catch (SQLException exce) {
			System.out.println(exce);
		}
	}

	public void Remove() {
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

	public void CloseConnection() {
		try {
			connection.close();
		}
		catch(SQLException exception) {
			System.out.println(exception);
		}
	}
}