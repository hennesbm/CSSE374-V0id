package sdedit.app;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class SDEditGenerator {
	String fileName;

	public SDEditGenerator(String fileName) {
		this.fileName = fileName;
	}

	public void execute() {
		try {
			String f = new File("docs\\input_output\\SDEdit.txt").getAbsoluteFile().getPath();
			String y = "images\\" + this.fileName + ".png";
			String z = new File(y).getAbsoluteFile().getPath();
			String app = "sdedit\\sdedit-4.2-beta1.exe";
			String path = "\"" + new File(app).getAbsoluteFile().getPath() + "\"" + " -o " + "\"" + z +"\"" +  " -t png \"" + f + "\"";
			@SuppressWarnings("unused")
			Process p = Runtime.getRuntime().exec(path);
			File file = new File(z);
			Desktop dsk = Desktop.getDesktop();
			dsk.open(file);
//			InputStream inputStream = p.getInputStream();
//			OutputStream outputStream = new FileOutputStream(new File(z));
//			int read = 0;
//			byte[] bytes = new byte[1024];
//			while ((read = inputStream.read(bytes)) != -1) {
//				outputStream.write(bytes, 0, read);
//			}
//			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SDEditGenerator unifier = new SDEditGenerator("Test0");
		unifier.execute();
	}
}
