import java.io.*;
import java.util.*;

public class DLB{
	public DLBnode head;
	private int count = 5;
	ArrayList<String> suggs = new ArrayList<String>();

	public DLB(){
		head = null;
	}


	public DLB(String dict){
		head = new DLBnode('$');
		
		//addWord(dict);
		
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( dict ) );
			while ( infile.ready() )
			{
				String string = infile.readLine();
				addWord(string);
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
		
		
	}

	public void addWord(String word){
		DLBnode curr = head;
		word = word + "^";
		
		if(head.getChild() == null){//first word
			curr = fillChildren(word,0, curr);
		}
		else{
			curr = head.getChild();

			if(curr.getData() != word.charAt(0)){

				while(curr.getSibling() != null){
					if(curr.getSibling().getData() != word.charAt(0)){
						curr = curr.getSibling();
					}
					else{
						checkChildren(curr.getSibling().getChild(), word, 1);//curr = 
						return;
					}
				}
				curr.setSibling(new DLBnode(word.charAt(0)));
				fillChildren(word,1, curr.getSibling()); //curr = !!!!!!!!!!!
			}
			else{
				checkChildren(curr.getChild(), word, 1); //curr = 
			}
		}
	}//end method
	public ArrayList<String> searchDict(String key, int index, DLBnode curr){//index starts at 0; curr starts at head 

		suggs.clear();
		count = 5;

		if(index > key.length()-1){
			suggs = finish(curr,key,"", suggs);
			return suggs;
		}

		if(curr.getData() == key.charAt(index)){
			searchDict(key, index + 1, curr.getChild());
		}
		else if(curr.getSibling() != null){
			searchDict(key, index, curr.getSibling());	
		}

		//System.out.println();
		return suggs;

	}

	public ArrayList<String> finish(DLBnode curr, String key, String sugg, ArrayList<String> five){
		
		

		if(count < 1){
			return five;
		}
		
		//if(curr.getData() == '^'){
			//count--;
			//curr.setData('*');
			//five.add(key+sugg);	
		//}

		while(curr.getChild() != null || (curr.getData() != '^' && curr.getData() != '*')){
			

			if(curr.getSibling() != null){
				count--;
				finish(curr.getSibling(), key, sugg, five);
			}

			sugg += ""+curr.getData();
			curr = curr.getChild();
		}
		
		
		if((curr.getData() == '^' || curr.getData() == '*') && curr.getSibling() != null){
			count--;
			finish(curr.getSibling(), key, sugg,five);
		}

		//System.out.println();
		//if(curr.getData() == '^'){
			//count--;
			//curr.setData('*');
		////	System.out.println(curr.getData()+"!!!!!!!!!!!!!!!!!!!");
		//}
		//if(count > 0){
			
		//}
		//count++;
		
		//System.out.println(key+sugg+"!!");
		
		five.add(key+sugg);
			//count--;	
		
		return five;
	}

	public void searchUserHistory(){//most likely just add word to dict


	}
	private DLBnode fillChildren(String word, int i, DLBnode curr){
		for(int j = i; j < word.length(); j++){

			curr.setChild(new DLBnode(word.charAt(j)));
			curr = curr.getChild();
		}
		return curr;
	}

	private DLBnode checkChildren(DLBnode curr, String word, int k){
		
		if(curr != null){
			for(int i = k; i < word.length(); i++){
				if(curr.getData() == word.charAt(i)){
					curr = curr.getChild();
				}
				else{
					if(curr.getSibling() == null){
						curr.setSibling(new DLBnode(word.charAt(i)));
						curr = curr.getSibling();
						return fillChildren(word, i+1, curr);
					}
					else{
						return checkChildren(curr.getSibling(), word, i);
					}
				}
			}
		}
		return curr;
	}

	public String toString(){
		String string = "";
		DLBnode curr = head.getChild();
		while(curr.getChild() != null){
			string += curr.getData() + "->";
			curr = curr.getChild();
		}

		return string;
	}

}