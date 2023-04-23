# Program to get temperature from OpenWeatherMapAPI.

import requests

CityName = input("Enter city name: ")
APILink = f'https://api.openweathermap.org/data/2.5/weather?q={CityName}&appid=abe3a0f4d0b6cebfbe7393b4b4e3aa28&units=metric'
Response = requests.get(APILink)
WeatherData = eval(Response.text)
Temperature = WeatherData.get('main').get('temp')
print(f'Temperature is {Temperature}')
