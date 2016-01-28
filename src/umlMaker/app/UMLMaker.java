package umlMaker.app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import directory.reader.DirectoryReader;
import problem.asm.DesignParser;
import problem.asm.PatternParser;
import umlMaker.impl.UMLMakerOutputStream;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

public class UMLMaker {

	public static void main(String[] args) throws IOException {
		DesignParser parser = new DesignParser();
		PatternParser parser2 = new PatternParser();
		
		DirectoryReader reader = new DirectoryReader("C:\\Users\\hennesbm\\Desktop\\CSSE374\\AdapterExample\\src");

		ArrayList<String> files = reader.readDirectory();
		
		parser.main(files);
		parser2.main(files, parser.model);

		OutputStream xmlOut = new FileOutputStream("docs/input_output/UML.txt");
		IVisitor xmlWriter = new UMLMakerOutputStream(xmlOut);

		ITraverser traverser = (ITraverser) parser2.model;
		String title = "TurkeyAdapter";
		xmlOut.write("digraph ".getBytes());
		xmlOut.write(title.getBytes());
		xmlOut.write(" { \n\trankdir=BT;\n\t".getBytes());
		xmlOut.write("splines=ortho;\n".getBytes());
		traverser.accept(xmlWriter);
		xmlOut.write("\n}".getBytes());
		
		xmlOut.close();
		UMLGenerator g = new UMLGenerator(title, "docs/UML.txt");
		g.execute();
	}
}
