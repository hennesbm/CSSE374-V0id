package adapterPattern;

public class Adapter implements ITarget {

	public Adaptee a;

	@Override
	public void method1() {
		System.out.println("This is Method 1.");
	}

	@Override
	public void method2() {
		System.out.println("This is Method 2.");
	}

}
