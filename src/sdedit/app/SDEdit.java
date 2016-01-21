package sdedit.app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import directory.reader.DirectoryReader;
import problem.asm.DesignParser;
import sdedit.impl.SDEditOutputStream;
import visitor.api.ITraverser;
import visitor.api.IVisitor;

public class SDEdit {

	public static void main(String[] args) throws IOException {
		DesignParser parser = new DesignParser();
		
		DirectoryReader reader = new DirectoryReader("D:\\Bo Peng\\Development\\CSSE374-project\\CSSE374V0id\\src", "sdedit.app","SDEdit.java","<init>");
		ArrayList<String> files = reader.readDirectory();
		
		parser.main(files);

		OutputStream xmlOut = new FileOutputStream("docs/SDEdit.txt");
		IVisitor xmlWriter = new SDEditOutputStream(xmlOut);

		ITraverser traverser = (ITraverser) parser.model;
		String title = "Milestone3Sequence";
		traverser.accept(xmlWriter);
		checkAndChange();
		for(String d: SDEditOutputStream.declare){
			write(xmlOut,d);
		}
		write(xmlOut,"\n");
		for(String d: SDEditOutputStream.methods){
			write(xmlOut,d);
		}
		
		xmlOut.close();
		SDEditGenerator g = new SDEditGenerator(title);
		g.execute();
	}
	
	private static void write(OutputStream o,String m) {
		try {
			o.write(m.getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void checkAndChange(){
		
		ArrayList<String> mark = new ArrayList<String>(); 
		ArrayList<Integer> mark2 = new ArrayList<Integer>(); 
		ArrayList<Integer> mark3 = new ArrayList<Integer>(); 
		ArrayList<String> declare = SDEditOutputStream.declare;
		ArrayList<String> methods = SDEditOutputStream.methods;
		
		
		for(int i=0; i<methods.size();i++){
			
			if(methods.get(i).contains("new")){
				String arg1 = methods.get(i).split(":")[0];
				if(!mark2.contains(Integer.parseInt(arg1.substring(3, arg1.length())))){
					mark2.add(Integer.parseInt(arg1.substring(3, arg1.length())));
					mark3.add(i);
				}
				
			}
		}
		int first = 0;
		int last = 0;
		if(mark2.size()!=0)
			last = mark2.get(0);
		for(int i=0; i<mark2.size();i++){
			if(last!=0){
				methods.add(mark3.get(i-1)+1, "arg"+first+":"+"arg"+last+".new"+"\n");
			}
			
			first = last;
			last = mark2.get(i);
		}
		
		for(String method: methods){
			if(method.contains("new")){
				//String arg1 = method.split(":")[0];
				String arg2 = method.split(":")[1].split("\\.")[0];
				int argIndex = Integer.parseInt(arg2.substring(3, arg2.length()));
				if(!mark.contains(argIndex)){
					if(argIndex!=0){
						if(!declare.get(argIndex).startsWith("/")){
							declare.set(argIndex, "/"+declare.get(argIndex));
						}
						else{
							mark.add(method);
						}
					}
				}
			}
		}
		
		Outer:
		for(String m: mark){
			for(int i=methods.size()-1; i>=0 ; i--){
				if(methods.get(i).equals(m)){
					methods.set(i, "");
					continue Outer;
				}
			}
		}
		
		ArrayList<Integer> remove = new ArrayList<Integer>();
		for(int i=methods.size()-1; i>=0; i--){
			//String arg1 = methods.get(i).split(":")[0];
			
			if(methods.get(i).contains("new")){
				for(int j = i - 1; j >=0 ; j--){
					if(methods.get(j).length() > 0){
						String arg2 = methods.get(j).split(":")[1].split("\\.")[0];
						//System.out.println(arg2 + " " + methods.get(i).split(":")[1].split("\\.")[0]);
						if(arg2.equals(methods.get(i).split(":")[1].split("\\.")[0])){
							remove.add(j);
						}
					}
				}
			}
			
		}
		Collections.sort(remove);
		for(int i = remove.size()-1; i>=0; i--){
			SDEditOutputStream.methods.set(remove.get(i), "");
		}
		SDEditOutputStream.methods.removeAll(Arrays.asList("",null));
		
		Map<String, Integer> fix = new HashMap<String, Integer>();
		for(int i =methods.size()-1; i>=1; i--){
			if(methods.get(i).length()!=0 && methods.get(i-1).length()!=0){
				String pa1 = methods.get(i).split(":")[0];
				String pa2 = methods.get(i).split(":")[1].split("\\.")[0];
				String pa3 = methods.get(i-1).split(":")[0];
				String pa4 = methods.get(i-1).split(":")[1].split("\\.")[0];
				if(!pa1.equals(pa3) && !pa2.equals(pa3) && !pa1.equals(pa4) && !pa2.equals(pa4)){
					fix.put(pa3+":"+pa1+".destroy\n", i);
				}
			}

		}
		
		for(String key: fix.keySet()){
			SDEditOutputStream.methods.add(fix.get(key),key);
		}
		
		ArrayList<Integer> re = new ArrayList<Integer>();
		
		for(int i =methods.size()-1; i>=0; i--){
				String pa1 = methods.get(i).split(":")[0];
				String pa2 = methods.get(i).split(":")[1].split("\\.")[0];
				if(pa1.equals(pa2)){
					re.add(i);
				}

		}
		for(int a: re){
			methods.remove(a);
		}
		
	}
}
