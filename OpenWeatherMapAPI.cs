// Program to get temperature of given city from OpenWeatherMap API.

using System.Net;  
using System.IO;
using System;

class Weather {
	public void ShowTemperature() {  
		string CityName;
		Console.WriteLine("Enter city name: ");
		CityName = Console.ReadLine();
		string APILink = "https://api.openweathermap.org/data/2.5/weather?q=" + CityName + "&appid=abe3a0f4d0b6cebfbe7393b4b4e3aa28&units=metric";
		WebRequest request = HttpWebRequest.Create(APILink);  
		WebResponse response = request.GetResponse();  
		StreamReader reader = new StreamReader(response.GetResponseStream());  
		string Data = reader.ReadToEnd();  
		string[] WeatherData = Data.Split('\"', ':', '{', '[', ',');
		int index = 0;
		foreach(string Key in WeatherData) {
			index++;
			if(Key.Equals("temp")) {
				Console.WriteLine("Temperature is " + WeatherData[index + 1] + ".");
			}
		}
	}  
}

class OpenWeatherMapAPI
{
	public static void Main(string[] args) {
		Weather oWeather = new Weather();
		oWeather.ShowTemperature();
	}
}