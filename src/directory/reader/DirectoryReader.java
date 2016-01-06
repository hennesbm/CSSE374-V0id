package directory.reader;

import java.io.File;
import java.util.ArrayList;

public class DirectoryReader {

	private String projectDirectory = "";
	private String projectFolder;
	private ArrayList<String> files = new ArrayList<String>();
	private ArrayList<String> directories = new ArrayList<String>();

	public DirectoryReader(String folder) {
		this.projectFolder = folder;
	}

	public DirectoryReader(String folder, String directory) {
		this.projectFolder = folder;
		this.projectDirectory = directory;
	}

	public ArrayList<String> readDirectory() {
		String[] directoryAdjust = this.projectDirectory.split("\\.");
		String folderName = "";
		for(String directoryName : directoryAdjust) {
			folderName = folderName + "\\" + directoryName;
		}
		File folder = new File(this.projectFolder + folderName);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if(!this.projectDirectory.equals("")){
					this.files.add(this.projectDirectory + "." + listOfFiles[i].getName().split("\\.")[0]);
				}
				else {
					this.files.add(listOfFiles[i].getName().split("\\.")[0]);
				}
			} else if (listOfFiles[i].isDirectory()) {
				this.directories.add(listOfFiles[i].getName());
			}
		}

		if (containsDirectory()) {
			for (String directory : this.directories) {
				DirectoryReader reader = new DirectoryReader(this.projectFolder + "\\" + this.projectDirectory,
						directory);
				ArrayList<String> innerFiles = reader.readDirectory();
				ArrayList<String> adjustedNames = new ArrayList<String>();
				if (!this.projectDirectory.equals("")) {
					for (String name : innerFiles) {
						adjustedNames.add(this.projectDirectory + "." + name);
					}
					this.files.addAll(adjustedNames);
				}
				else {
					this.files.addAll(innerFiles);
				}
			}
		}
		return this.files;
	}

	private boolean containsDirectory() {
		return this.directories.size() > 0;
	}
}
