// Client program to connect with server.

using System;
using System.Net;
using System.Net.Sockets;
using System.Threading;
using System.Text;

public class cClient
{
	Socket socket;
	string clientName;
	public cClient() {
		string host = "127.0.0.1";
		int port = 59000;
		socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
		Console.WriteLine("Establishing Connection with " + host);
		socket.Connect(host, port);
		Console.WriteLine("Connection established");
		Console.WriteLine("Enter your name:");
		clientName = Console.ReadLine();
		byte[] nameData = Encoding.ASCII.GetBytes(clientName);
		socket.Send(nameData);
	}

	public void SendMessage() {
		while (true) {
			try {
				string message;
				Console.WriteLine("Enter the message: ");
				message = Console.ReadLine();
				byte[] data = System.Text.Encoding.ASCII.GetBytes(message);
				socket.Send(data);
			}
			catch (SocketException se) {
				Console.WriteLine(se);
				break;
			}
		}
	}

	public void ReceiveMessage() {
		while (true) {
			try {
				byte[] data = new byte[1024];
				int receivedDataLength = socket.Receive(data);
				string stringData = Encoding.ASCII.GetString(data, 0, receivedDataLength);
				Console.WriteLine(stringData.TrimEnd('\0'));
			}
			catch (SocketException se) {
				Console.WriteLine(se);
				break;
			}
		}
	}

}

class Client
{
	public static void Main(string[] args) {
		cClient oClient = new cClient();
		Thread receiveThread = new Thread(new ThreadStart(oClient.ReceiveMessage));
		Thread sendThread = new Thread(new ThreadStart(oClient.SendMessage));
		receiveThread.Start();
		sendThread.Start();
	}
}
