package sdedit.app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import directory.reader.DirectoryReader;
import problem.asm.DesignParser;
import sdedit.impl.SDEditOutputStream;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

public class SDEdit {

	public static void main(String[] args) throws IOException {
		DesignParser parser = new DesignParser();
		
		DirectoryReader reader = new DirectoryReader("C:\\Users\\hennesbm\\Desktop\\CSSE374\\Labs\\Lab 2\\Lab2-3\\src", "headfirst.factory.pizzaaf");
		ArrayList<String> files = reader.readDirectory();
		
		parser.main(files);

		OutputStream xmlOut = new FileOutputStream("docs/SDEdit.txt");
		IVisitor xmlWriter = new SDEditOutputStream(xmlOut);

		ITraverser traverser = (ITraverser) parser.model;
		String title = "AbstractPizzaFactory";
		traverser.accept(xmlWriter);

		xmlOut.close();
		SDEditGenerator g = new SDEditGenerator(title);
		g.execute();
	}
}
