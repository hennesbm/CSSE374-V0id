package umlMaker.app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import problem.asm.DesignParser;
import umlMaker.api.IDeclaration;
import umlMaker.impl.Declaration;
import umlMaker.impl.UMLMakerOutputStream;
import umlMaker.visitor.api.ITraverser;
import umlMaker.visitor.api.IVisitor;

public class UMLMaker {

	public static void main(String[] args) throws IOException {
		DesignParser parser = new DesignParser();
		String[] classes = {
				"headfirst.observer.weather.WeatherData"
		};
		parser.main(classes);

		OutputStream xmlOut = new FileOutputStream("docs/UML.txt");
		IVisitor xmlWriter = new UMLMakerOutputStream(xmlOut);

		ITraverser traverser = (ITraverser) parser.model;
		String title = "weather_example";
		xmlOut.write("digraph ".getBytes());
		xmlOut.write(title.getBytes());
		xmlOut.write(" {".getBytes());
		traverser.accept(xmlWriter);
		xmlOut.write("}".getBytes());

		xmlOut.close();
	}
}
