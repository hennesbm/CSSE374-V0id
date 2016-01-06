package umlMaker.app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import directory.reader.DirectoryReader;
import problem.asm.DesignParser;
import umlMaker.impl.UMLMakerOutputStream;
import umlMaker.visitor.api.ITraverser;
import umlMaker.visitor.api.IVisitor;

public class UMLMaker {

	public static void main(String[] args) throws IOException {
		DesignParser parser = new DesignParser();
		
		DirectoryReader reader = new DirectoryReader("C:\\Users\\hennesbm\\Desktop\\CSSE374\\Labs\\Lab 1\\Lab1-3-Solution\\src" , "problem");
		ArrayList<String> files = reader.readDirectory();
		
		parser.main(files);

		OutputStream xmlOut = new FileOutputStream("docs/UML.txt");
		IVisitor xmlWriter = new UMLMakerOutputStream(xmlOut);

		ITraverser traverser = (ITraverser) parser.model;
		String title = "Lab1_3";
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
