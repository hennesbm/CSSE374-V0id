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
				"problem.asm.ClassDeclarationVisitor",
				"problem.asm.ClassFieldVisitor",
				"problem.asm.ClassMEthodVisitor",
				"problem.asm.DesignParser",
				"problem.asm.FirstASM",
				"umlMaker.api.IComponent",
				"umlMaker.api.IDeclaration",
				"umlMaker.api.IField",
				"umlMaker.api.IMethod",
				"umlMaker.api.IModel",
				"umlMaker.app.UMLGenerator",
				"umlMaker.app.UMLMaker",
				"umlMaker.impl.Declaration",
				"umlMaker.impl.Field",
				"umlMaker.impl.Method",
				"umlMaker.impl.Model",
				"umlMaker.impl.UMLMakerOutputStream",
				"umlMaker.visitor.api.ITraverser",
				"umlMaker.visitor.api.IVisitor",
				"umlMaker.visitor.api.VisitorAdapter"
		};
		parser.main(classes);

		OutputStream xmlOut = new FileOutputStream("docs/UML.txt");
		IVisitor xmlWriter = new UMLMakerOutputStream(xmlOut);

		ITraverser traverser = (ITraverser) parser.model;
		String title = "UMLMaker";
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
