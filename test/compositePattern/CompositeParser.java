package compositePattern;

import java.io.IOException;
import java.util.ArrayList;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import component.api.IModel;
import problem.asm.CompositeVisitor;
import problem.asm.HierarchyVisitor;
import problem.asm.LeafVisitor;

public class CompositeParser {
	public static ArrayList<String> CLASSES;
	public IModel model;
	
	
	public void main(ArrayList<String> files, IModel model) throws IOException {
		CompositeParser.CLASSES = files;
		
		this.model = model;

		for (String className : CLASSES) {
			ClassReader reader = new ClassReader(className);
			
			//Test all those three visitors together, order matters.
			ClassVisitor hierarchyVisitor = new HierarchyVisitor(Opcodes.ASM5, this.model);
			ClassVisitor compositeVisitor = new CompositeVisitor(Opcodes.ASM5, hierarchyVisitor, this.model);
			ClassVisitor leafVisitor = new LeafVisitor(Opcodes.ASM5, compositeVisitor, this.model);
			
			reader.accept(leafVisitor, ClassReader.EXPAND_FRAMES);
		}
	}
}