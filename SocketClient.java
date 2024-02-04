// Program to connect with server to send and receive messages.

import java.io.*;
import java.net.*;
import java.util.*;

class Client implements Runnable {
	Socket socket;
	Thread receiveThread;
	Thread sendThread;
	BufferedReader br;
	DataOutputStream dout;
	String message = new String();
	Scanner input = new Scanner(System.in);
	Client() throws IOException {
		try {
			socket = new Socket("localhost", 59000);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			receiveThread = new Thread(this);
			sendThread = new Thread(this);
			System.out.println("You are now connected!");
			receiveThread.start();
			sendThread.start();
		}
		catch (UnknownHostException exception) {
			System.out.println(exception);
		}
	}

	void receiveMessage() {
		try {
			while (true) {
				message = br.readLine();
				System.out.println("Response from server: " + message);
			}
		}
		catch(Exception exception) {
			System.out.println(exception);
		}
	}

	void sendMessage() {
		try {
			System.out.print("You: ");
			message = input.nextLine();
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			dout.writeUTF(message);  
			dout.flush();
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
	}

	public void run() {
		try {
			if (Thread.currentThread() == receiveThread) {
				receiveMessage();
			}
			else if (Thread.currentThread() == sendThread) {
				sendMessage();
			}
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
	}
}

class SocketClient {
	public static void main(String[] args) throws IOException {
		Client oClient = new Client();
	}
}
