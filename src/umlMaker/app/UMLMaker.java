package umlMaker.app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import problem.asm.DesignParser;
import umlMaker.impl.UMLMakerOutputStream;
import umlMaker.visitor.api.ITraverser;
import umlMaker.visitor.api.IVisitor;

public class UMLMaker {

	public static void main(String[] args) throws IOException {
		DesignParser parser = new DesignParser();
		String[] classes = {
				"headfirst.observer.weather.CurrentConditionsDisplay",
				"headfirst.observer.weather.DisplayElement",
				"headfirst.observer.weather.ForecastDisplay",
				"headfirst.observer.weather.HeatIndexDisplay",
				"headfirst.observer.weather.Observer",
				"headfirst.observer.weather.StatisticsDisplay",
				"headfirst.observer.weather.Subject",
				"headfirst.observer.weather.WeatherData",
				"headfirst.observer.weather.WeatherStation",
				"headfirst.observer.weather.WeatherStationHeatIndex"
		};
		parser.main(classes);

		OutputStream xmlOut = new FileOutputStream("docs/UML.txt");
		IVisitor xmlWriter = new UMLMakerOutputStream(xmlOut);

		ITraverser traverser = (ITraverser) parser.model;
		String title = "weather_example";
		xmlOut.write("digraph ".getBytes());
		xmlOut.write(title.getBytes());
		xmlOut.write(" { rankdir=BT;".getBytes());
		traverser.accept(xmlWriter);
		xmlOut.write("}".getBytes());

		xmlOut.close();
	}
}
