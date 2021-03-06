package adapterPatternTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import directory.reader.DirectoryReader;
import problem.asm.DesignParser;
import umlMaker.app.UMLGenerator;
import umlMaker.impl.UMLMakerOutputStream;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

public class TestAdapterPattern {
	public static void main(String[] args) throws IOException {
		DesignParser parser = new DesignParser();

		DirectoryReader reader = new DirectoryReader(
				"C:\\Users\\hennesbm\\Desktop\\CSSE374\\Labs\\Lab 4\\Lab4-2-Singleton\\src", "test.adapterPattern");

		ArrayList<String> files = reader.readDirectory();

		parser.main(files);

		OutputStream xmlOut = new FileOutputStream("docs/UMLTestAdapterPattern.txt");
		IVisitor xmlWriter = new UMLMakerOutputStream(xmlOut);

		ITraverser traverser = (ITraverser) parser.model;
		String title = "AdapterPattern";
		xmlOut.write("digraph ".getBytes());
		xmlOut.write(title.getBytes());
		xmlOut.write(" { rankdir=BT;".getBytes());
		traverser.accept(xmlWriter);
		xmlOut.write("}".getBytes());

		xmlOut.close();
		UMLGenerator g = new UMLGenerator(title, "docs/UMLTestAdapterPattern.txt");
		g.execute();
	}
}
