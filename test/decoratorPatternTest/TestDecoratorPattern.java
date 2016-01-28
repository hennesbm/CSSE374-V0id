package decoratorPatternTest;

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

public class TestDecoratorPattern {
	public static void main(String[] args) throws IOException {
		DesignParser parser = new DesignParser();

		DirectoryReader reader = new DirectoryReader(
				"D:\\Bo Peng\\Development\\CSSE374\\CSSE374V0id\\test", "decoratorPattern");

		ArrayList<String> files = reader.readDirectory();

		parser.main(files);

		OutputStream xmlOut = new FileOutputStream("docs/UMLTestAdapterPattern.txt");
		IVisitor xmlWriter = new UMLMakerOutputStream(xmlOut);

		ITraverser traverser = (ITraverser) parser.model;
		String title = "DecoratorPattern";
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
