// Program to connect to MySQL and SQLite databases.

import java.util.*;
import java.io.*;

class CRUDMain
{
	public static void main(String[] args) throws IOException {
		File file = new File("ClassName.cfg");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String ClassName = br.readLine();
		try {
			iCRUD oCRUD = (iCRUD)Class.forName(ClassName).newInstance();
			Scanner input = new Scanner(System.in);
			int Choice;
			do {
				System.out.println("1. Add Item");
				System.out.println("2. Show all items");
				System.out.println("3. Update item details");
				System.out.println("4. Search an item");
				System.out.println("5. Sort by item names");
				System.out.println("6. Remove item");
				System.out.println("7. Exit");
				System.out.print("Enter your choice: ");
				Choice = input.nextInt();
				switch (Choice) {
					case 1:
						oCRUD.AddItem();
					break;
					case 2:
						oCRUD.ShowAll();
					break;
					case 3:
						oCRUD.Update();
					break;
					case 4:
						oCRUD.Search();
					break;
					case 5:
						oCRUD.Sort();
					break;
					case 6:
						oCRUD.Remove();
					break;
					case 7:
					break;
					default : System.out.println("Enter a valid input!");
				}
				System.out.print("Enter any number to continue or 7 to exit: ");
				Choice = input.nextInt();
			} while (Choice != 7);
			oCRUD.CloseConnection();
		}
		catch (Exception exception) {
			System.out.println(exception);
			exception.printStackTrace();
		}
	}
}


