package sdfsdf;

public class DLBnode{

	private char data;
	private DLBnode child;
	private DLBnode sibling;

	//MORE CONSTRUCTORS IF NEEDED

	public DLBnode(char data){
		this(data, null, null);
	}

	public DLBnode(char data, DLBnode child, DLBnode sibling){
		setData(data);
		setChild(child);
		setSibling(sibling);
	}

	public void setData(char data){
		this.data = data;
	}

	public void setChild(DLBnode child){
		this.child = child;
	}

	public void setSibling(DLBnode sibling){
		this.sibling = sibling;
	}

	public char getData(){
		return data;
	}

	public DLBnode getChild(){
		return child;
	}

	public DLBnode getSibling(){
		return sibling;
	}

	public String toString(){
		return ""+getData();
	}
}
