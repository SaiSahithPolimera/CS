// Sample library.

using System;

namespace Mylib 
{
	public class Hello {
		public void SayHello(string Name){
			Console.WriteLine("Hello " + Name + "!");
		}
	}

	public class Hi {
		public void SayHi(string Name){
			Console.WriteLine("Hi " + Name + "!");
		}
	}
}

public () {
		string myConnectionString;
		myConnectionString = "server=138.68.140.83;uid=Sahith;" + "pwd=Sahith@369;database=dbSahith";
		connection = new MySqlConnection(myConnectionString);
		try {
			connection.Open();
			Console.WriteLine("Connected to MySQL!");
			connection.Close();
		}
		catch (MySqlException ex) {
			Console.WriteLine(ex.ToString());
		}
	}
