package problem.asm;

import java.io.IOException;
import java.util.ArrayList;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import component.api.IModel;

public class PatternParser {
	public static ArrayList<String> CLASSES;
	public IModel model;
	
	
	public void main(ArrayList<String> files, IModel model) throws IOException {
		PatternParser.CLASSES = files;
		
		this.model = model;

		for (String className : CLASSES) {
			ClassReader reader = new ClassReader(className);
			
			ClassVisitor singletonVisitor = new SingletonVisitor(Opcodes.ASM5, this.model);
						
			ClassVisitor adaptVisitor = new AdapterVisitor(Opcodes.ASM5, singletonVisitor, this.model);
			
			reader.accept(adaptVisitor, ClassReader.EXPAND_FRAMES);
		}
	}
}