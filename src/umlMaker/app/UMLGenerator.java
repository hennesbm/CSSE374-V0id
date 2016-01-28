package umlMaker.app;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UMLGenerator {
	String fileName;
	String path;

	public UMLGenerator(String fileName, String path) {
		this.fileName = fileName;
		this.path = path;
	}

	public void execute() {
		try {
			String f = new File("docs\\input_output\\UML.txt").getAbsoluteFile().getPath();
			String y = "images\\" + this.fileName + ".png";
			String z = new File(y).getAbsoluteFile().getPath();
			String path = "\"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot\" -Tpng " + "\"" + f + "\"";
			Process p = Runtime.getRuntime().exec(path);
			InputStream inputStream = p.getInputStream();
			File file = new File(z);
			OutputStream outputStream = new FileOutputStream(file);
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
			Desktop dsk = Desktop.getDesktop();
			dsk.open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		UMLGenerator unifier = new UMLGenerator("Test0","docs\\UML.txt");
		unifier.execute();
	}
}
