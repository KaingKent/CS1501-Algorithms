public class DLBnode{

	private char data;
	private DLBnode child;
	private DLBnode sibling;
	private int uses;

	public DLBnode(char data){
		this(data, null, null,0);
	}

	public DLBnode(char data, DLBnode child, DLBnode sibling, int uses){
		setData(data);
		setChild(child);
		setSibling(sibling);
		setUses(uses);
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

	public void setUses(int uses){
		this.uses = uses;
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

	public int getUses(){
		return uses;
	}

	public String toString(){
		return ""+getData();
	}
}