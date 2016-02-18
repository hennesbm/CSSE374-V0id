package visitorTests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.junit.Test;

import directory.reader.DirectoryReader;
import problem.asm.DesignParser;
import umlMaker.impl.UMLMakerOutputStream;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

public class ClassDecoratorVisitorTest {
	InputStream ip;
	InputStream cip;
	boolean passTest = false;

	public ClassDecoratorVisitorTest() throws IOException {

		DesignParser parser = new DesignParser();
		ClassDecoratorVisitorParser parser2 = new ClassDecoratorVisitorParser();

		DirectoryReader reader = new DirectoryReader(new File("test/").getAbsoluteFile().getPath(), "decoratorPattern");

		ArrayList<String> files = reader.readDirectory();

		parser.main(files);
		parser2.main(files, parser.model);

		OutputStream xmlOut = new FileOutputStream("docs/ClassDecoratorVisitorTest.txt");
		IVisitor xmlWriter = new UMLMakerOutputStream(xmlOut);

		ITraverser traverser = (ITraverser) parser2.model;
		String title = "ClassDecoratorVisitorTest";
		xmlOut.write("digraph ".getBytes());
		xmlOut.write(title.getBytes());
		xmlOut.write(" { rankdir=BT;".getBytes());
		traverser.accept(xmlWriter);
		xmlOut.write("}".getBytes());

		xmlOut.close();
		File f = new File("docs/ClassDecoratorVisitorTest.txt").getAbsoluteFile();
		ip = new FileInputStream(f);
		File cf = new File("test/visitorTests/ClassDecoratorVisitorTest.txt").getAbsoluteFile();
		cip = new FileInputStream(cf);
		this.passTest = isEqual(ip, cip);

	}

	@Test
	public void compareStream() {
		assertTrue(this.passTest);
	}

	private boolean isEqual(InputStream i1, InputStream i2) throws IOException {

		try {
			// do the compare
			while (true) {
				int fr = i1.read();
				int tr = i2.read();

				if (fr != tr)
					return false;

				if (fr == -1)
					return true;
			}

		} finally {
			if (i1 != null)
				i1.close();
			if (i2 != null)
				i2.close();
		}
	}
}
