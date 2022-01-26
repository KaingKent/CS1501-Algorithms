import java.io.*;
import java.util.*;

public class Test{
	public static void main(String args[]) {
		//DLB test = new DLB(args[0]);
		//System.out.println(test.toString());

		TST test = new TST(args[0]);
		//TSTnode curr = null;



		System.out.println(test.search("t"));
		//System.out.println(test.search("barb"));
		//System.out.println(test.search("there"));
		//search("yeet");


		/*
		test.addWord("ban");
		//System.out.println(test.head.getChild().getSibling().getChild().getChild().getData());
		test.addWord("cake");
		test.addWord("land");
		test.addWord("bad");
		test.addWord("asian");
		test.addWord("asia");
		test.addWord("ben");
		test.addWord("lakes");s
		test.addWord("leak");
		test.addWord("laky");
		test.addWord("beds");
		test.addWord("beans");

		
		test.addWord("professional");
		test.addWord("potent");
		test.addWord("peen");
		test.addWord("predict");
		test.addWord("push");
		test.addWord("preteen");
		test.addWord("preface");
		test.addWord("pry");



		/*
		DLBnode curr = test.head.getChild();
		while(curr.getSibling() != null){
			System.out.println(curr.getData());
			curr = curr.getSibling();
		}

		System.out.println("~~~~~~~~~~~~~");
		System.out.println(curr.getChild().getData());// r

		System.out.println(curr.getChild().getChild().getData());// o
		//System.out.println(test.finish(curr,"p",""));
		System.out.println(curr.getChild().getSibling().getData());// o

/*
		while(curr.getChild() != null){
			System.out.println(curr.getChild().getData());
			curr = curr.getChild();
		}


*/

	//	test.searchDict("t", 0, test.head.getChild());
/*
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~0");
		System.out.println(test.head.getChild().getSibling().toString());// b
		System.out.println(test.head.getChild().getSibling().getChild().toString());// a
		System.out.println(test.head.getChild().getSibling().getChild().getChild().toString());//n
		System.out.println(test.head.getChild().getSibling().getChild().getChild().getChild().toString());//^
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~1");
		System.out.println(test.head.getChild().getSibling().getSibling().toString());//c
		System.out.println(test.head.getChild().getSibling().getSibling().getChild().toString());//a
		System.out.println(test.head.getChild().getSibling().getSibling().getChild().getChild().toString());//k
		System.out.println(test.head.getChild().getSibling().getSibling().getChild().getChild().getChild().toString());//e
		System.out.println(test.head.getChild().getSibling().getSibling().getChild().getChild().getChild().getChild().toString());//^
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~2");

		System.out.println(test.head.getChild().getSibling().toString());// b
		System.out.println(test.head.getChild().getSibling().getChild().toString());// a
		System.out.println(test.head.getChild().getSibling().getChild().getChild().getSibling().toString());// d
		System.out.println(test.head.getChild().getSibling().getChild().getChild().getSibling().getChild().toString());// ^


System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~3");
		System.out.println(test.head.getChild().getSibling().toString());// b
		System.out.println(test.head.getChild().getSibling().getChild().getSibling().toString());// e
		System.out.println(test.head.getChild().getSibling().getChild().getSibling().getChild().toString());//n
		System.out.println(test.head.getChild().getSibling().getChild().getSibling().getChild().getChild().toString());//^
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~4");

		System.out.println(test.head.getChild().getChild().getChild().getSibling().toString());
		System.out.println(test.head.getChild().getChild().getChild().getSibling().getChild().toString());
		System.out.println(test.head.getChild().getChild().getChild().getSibling().getChild().getChild().toString());
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~5");
		System.out.println(test.head.getChild().toString());//l
		System.out.println(test.head.getChild().getChild().toString());//a
		System.out.println(test.head.getChild().getChild().getChild().toString());//k
		System.out.println(test.head.getChild().getChild().getChild().getChild().toString());//e
		System.out.println(test.head.getChild().getChild().getChild().getChild().getChild().toString());//^
		System.out.println(test.head.getChild().getChild().getChild().getChild().getChild().getSibling().toString());//s
		System.out.println(test.head.getChild().getChild().getChild().getChild().getChild().getSibling().getChild().toString());//^



	*/
	}

}