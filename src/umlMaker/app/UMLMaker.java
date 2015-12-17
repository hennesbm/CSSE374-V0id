package umlMaker.app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import umlMaker.api.IDeclaration;
import umlMaker.impl.Declaration;
import umlMaker.impl.UMLMakerOutputStream;
import umlMaker.visitor.api.ITraverser;
import umlMaker.visitor.api.IVisitor;

public class UMLMaker {

	public static void main(String[] args) throws IOException {
		IDeclaration class1 = new Declaration(0, 0, null, null, null, args, null);

		OutputStream xmlOut = new FileOutputStream("input_output/car.xml");
		IVisitor xmlWriter = new UMLMakerOutputStream(xmlOut);

		ITraverser traverser = (ITraverser) class1;
		traverser.accept(xmlWriter);

		xmlOut.close();
	}
}
