package sdedit.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SDEditGenerator {
	String fileName;

	public SDEditGenerator(String fileName) {
		this.fileName = fileName;
	}

	public void execute() {
		try {
			String f = new File("docs\\SDEdit.txt").getAbsoluteFile().getPath();
			String y = "docs\\" + this.fileName + ".png";
			String z = new File(y).getAbsoluteFile().getPath();
			String path = "\"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot\" -Tpng " + "\"" + f + "\"";
			Process p = Runtime.getRuntime().exec(path);
			InputStream inputStream = p.getInputStream();
			OutputStream outputStream = new FileOutputStream(new File(z));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SDEditGenerator unifier = new SDEditGenerator("Test0");
		unifier.execute();
	}
}
