package problem.asm;

import java.util.ArrayList;

import org.objectweb.asm.ClassVisitor;

public class ClassDeclarationVisitor extends ClassVisitor {
	public ClassDeclarationVisitor(int api) {
		super(api);
	}
	
	public ClassDeclarationVisitor(int api, ClassVisitor decorated) {
		super(api, decorated);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		
		 //System.out.println("Class: "+name+" extends "+superName+" implements "+Arrays.toString(interfaces));
		String[] namet = name.split("/");
		String[] superNamet = superName.split("/");
		ArrayList<String[]> interfacest = new ArrayList<String[]>();
		for (String i: interfaces){
			interfacest.add(i.split("/"));
		}
		 System.out.println(namet[namet.length - 1] + "[");
		 System.out.println("shape=\"record\",");
		 System.out.print("label = \"{" + namet[namet.length - 1] + "|");
		 super.visit(version, access, name, signature, superName,interfaces);
		 System.out.println("}\"");
		 System.out.println("];");
		 System.out.println(namet[namet.length - 1] + " -> " + superNamet[superNamet.length - 1] + " [arrowhead=\"onormal\"];");
		 for (String[] i: interfacest){
			 String inter = i[i.length - 1];
			 System.out.println(namet[namet.length - 1] + " -> " + inter + " [arrowhead=\"onormal\", style=\"dashed\"];");
		 }
	}
}