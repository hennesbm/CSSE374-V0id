package umlMaker.app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import component.api.IPattern;
import component.impl.Model;
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
		
		DirectoryReader reader = new DirectoryReader("C:\\Users\\TF\\workspace\\CSSE374V0id\\src");
		ArrayList<String> files = reader.readDirectory();
		
//		ArrayList<String> files = new ArrayList<String>();
//		files.add("java.awt.Component");
//		files.add("javax.swing.JComponent");
//		files.add("java.awt.Container");
//		files.add("java.awt.Window");
//		files.add("java.awt.Panel");
//		files.add("java.awt.Frame");
//		files.add("java.awt.LayoutManager");
//		files.add("java.awt.GraphicsConfiguration");
		
		files.add("java.lang.String");
		parser.main(files);
		parser2.main(files, parser.model);
		
//		parser2.model.setActive("java.awt.LayoutManager", false);		
		
		HashMap<String, ArrayList<IPattern>> patternmap =  parser2.model.getAllPatterns();
//		ArrayList<String> adapterlist = Model.getPatternNameList(patternmap, "Adapter");
//		ArrayList<String> compositelist = Model.getPatternNameList(patternmap, "Composite");
		
		OutputStream xmlOut = new FileOutputStream("docs/input_output/UML.txt");
		IVisitor xmlWriter = new UMLMakerOutputStream(xmlOut);

		ITraverser traverser = (ITraverser) parser2.model;
		String title = "Lab7_2";
		xmlOut.write("digraph ".getBytes());
		xmlOut.write(title.getBytes());
		xmlOut.write(" { \n\trankdir=BT;\n\t".getBytes());
		xmlOut.write("splines=ortho;\n".getBytes());
		traverser.accept(xmlWriter);
		xmlOut.write("\n}".getBytes());
		
		xmlOut.close();
		UMLGenerator g = new UMLGenerator(title, "docs/input_output/UML.txt");
		g.execute();
	}
	

}
