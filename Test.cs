// Program to use methods in Mylib.

using System;
using Mylib;

class Test
{
	public static void Main(String[] args) {
		Mylib.Hello oHello = new Mylib.Hello();
		oHello.SayHello("Sahith");
		Mylib.Hi oHi = new Mylib.Hi();
		oHi.SayHi("Light");
	}
}