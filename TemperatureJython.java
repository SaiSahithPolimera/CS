// Program to show temperature using OpenWeatherMap API.

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import java.util.Properties;

public class TemperatureJython {
    public static void main(String[] args) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("import urllib2");
        interpreter.exec("import urlparse");
        interpreter.exec("import json");
        interpreter.exec("cityName = raw_input(\"Enter City name: \")");
        interpreter.exec("url = \"https://api.openweathermap.org/data/2.5/weather?q=\" + urllib2.quote(cityName) + \"&appid=f9608e11b8d7a417d53920fa76f959a5&units=metric\"");
        interpreter.exec("response = urllib2.urlopen(url)");
        interpreter.exec("data = json.loads(response.read())");
        interpreter.exec("Temperature = data['main']['temp']");
        interpreter.exec("print \'Temperature in \', cityName, \'is\', Temperature, \'.\'");
    }
}

