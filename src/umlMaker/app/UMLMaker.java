package umlMaker.app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import directory.reader.DirectoryReader;
import problem.asm.DesignParser;
import umlMaker.impl.UMLMakerOutputStream;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

public class UMLMaker {

	public static void main(String[] args) throws IOException {
		DesignParser parser = new DesignParser();
		
		DirectoryReader reader = new DirectoryReader("D:\\Bo Peng\\Development\\CSSE374\\Lab2-1\\src");

		ArrayList<String> files = reader.readDirectory();
		
		parser.main(files);

		OutputStream xmlOut = new FileOutputStream("docs/UML.txt");
		IVisitor xmlWriter = new UMLMakerOutputStream(xmlOut);

		ITraverser traverser = (ITraverser) parser.model;
		String title = "Chocolate";
		xmlOut.write("digraph ".getBytes());
		xmlOut.write(title.getBytes());
		xmlOut.write(" { rankdir=BT;".getBytes());
		traverser.accept(xmlWriter);
		xmlOut.write("}".getBytes());

		xmlOut.close();
		UMLGenerator g = new UMLGenerator(title);
		g.execute();
	}
}
