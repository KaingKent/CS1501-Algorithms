import java.io.*;
import java.util.*;

public class DLB{
	public DLBnode head;
	private ArrayList<String> suggs = new ArrayList<String>();
	private TreeSet<String> uses = new TreeSet<String>();

	public DLB(){
		head = new DLBnode('$');//arbituary head
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
					if(curr.getSibling().getData() != word.charAt(0)){//keep checking the siblings
						curr = curr.getSibling();
					}
					else{
						checkChildren(curr.getSibling().getChild(), word, 1);
						return;
					}
				}
				curr.setSibling(new DLBnode(word.charAt(0)));
				fillChildren(word,1, curr.getSibling());
			}
			else{
				checkChildren(curr.getChild(), word, 1);//checks if the first char of the key is equal to letter
			}
		}
	}//end method

	public void addWordHist(String word){//same ass addWord but with special helper methods
		DLBnode curr = head;
		word = word + "^";
		
		if(head.getChild() == null){//first word
			curr = fillChildrenHist(word,0, curr);
		}
		else{
			curr = head.getChild();

			if(curr.getData() != word.charAt(0)){
				while(curr.getSibling() != null){
					
					if(curr.getSibling().getData() != word.charAt(0)){
						curr = curr.getSibling();
					}
					else{
						checkChildrenHist(curr.getSibling().getChild(), word, 1);
						return;
					}
				}
				curr.setSibling(new DLBnode(word.charAt(0)));

				fillChildrenHist(word,1, curr.getSibling());
			}
			else{
				checkChildrenHist(curr.getChild(), word, 1);
			}
		}
	}//end method
	public ArrayList<String> searchDict(String key, int index, DLBnode curr){//index starts at 0; curr starts at head 

		suggs.clear();
		uses.clear();

		if(index > key.length()-1){//stops once all the chars of the key have been used
			finish(curr,key,"", suggs);
			addSugg();

			return suggs;
		}
		if(curr.getData() == key.charAt(index)){//keep checking the children if its equal
			searchDict(key, index + 1, curr.getChild());
		}
		else if(curr.getSibling() != null){//then check the children
			searchDict(key, index, curr.getSibling());	
		}

		return suggs;

	}

	public void finish(DLBnode curr, String key, String sugg, ArrayList<String> five){//gets all possible suggestions

		while(curr.getChild() != null || curr.getData() != '^' ){
			
			if(curr.getSibling() != null){
				finish(curr.getSibling(), key, sugg, five);
			}

			sugg += ""+curr.getData();
			curr = curr.getChild();
		}
		
		
		if(curr.getData() == '^' && curr.getSibling() != null){//if a word is a prefix to another word
			finish(curr.getSibling(), key, sugg,five);
		}

		if(curr.getData() == '^'){//concatenate the count with suggestion with the same amount of digits so that the treeset sorts it automatically
			uses.add(String.format("%1$07d",curr.getUses())+key+sugg);
		}
	}

	private void addSugg(){//adds the 5 or less suggestions to arraylist
		int count = 5;
		int use =0;

		LinkedHashSet<String> five = new LinkedHashSet<String>();
		for(int i = 0; i < 5;i++){
			String string = uses.pollLast();
			if(string != null){
				five.add(string);
			}
			else{
				break;
			}
		}

		for(String word: five){//gets all the words that have been used more than 0 times
			if(count > 0){
				use = Integer.parseInt(word.replaceAll("[^0-9]", ""));
				if(use > 0){
					suggs.add(word.replaceAll("[^a-zA-z']", ""));
				}
				else{
					break;
				}
			}
			else{
				break;
			}
			count--;
		}

		for(String word: uses){//fills in the rest of the suggestions
			if(count > 0){
				if(!suggs.contains(word.replaceAll("[^a-zA-z']", ""))){
					suggs.add(word.replaceAll("[^a-zA-z']", ""));
				}
			}
			else{
				break;
			}
			count--;
		}
	}

	private DLBnode checkChildren(DLBnode curr, String word, int k){// helper to add words from dictionary; doesnt count since the words havent been used yet
		
		if(curr != null){
			for(int i = k; i < word.length(); i++){
				if(curr.getData() == word.charAt(i)){//keep checking each char
					curr = curr.getChild();
				}
				else{
					if(curr.getSibling() == null){//add new sibling since it stopped finding matching chars
						curr.setSibling(new DLBnode(word.charAt(i)));
						curr = curr.getSibling();
						return fillChildren(word, i+1, curr);//fills the rest of the letters in
					}
					else{
						return checkChildren(curr.getSibling(), word, i);//since it has a sibling check that siblings children
					}
				}
			}
		}

		return curr;
	}

	private DLBnode fillChildren(String word, int i, DLBnode curr){//adds the rest of the chars in
		for(int j = i; j < word.length(); j++){
			curr.setChild(new DLBnode(word.charAt(j)));
			curr = curr.getChild();
		}
		return curr;
	}

	private DLBnode checkChildrenHist(DLBnode curr, String word, int k){// helper for adding words to history that checks all the children; also counts the words if it exists already
		
		if(curr != null){
			for(int i = k; i < word.length(); i++){
				if(curr.getData() == word.charAt(i)){
					
					if(curr.getData() == '^'){//found word
						curr.setUses(curr.getUses()+1);
					}
					curr = curr.getChild();
				}
				else{
					if(curr.getSibling() == null){
						curr.setSibling(new DLBnode(word.charAt(i)));
						curr = curr.getSibling();
						if(curr.getData() == '^'){//found word
							curr.setUses(curr.getUses()+1);
						}else{
							return fillChildrenHist(word, i+1, curr);//brand new word
						}
					}
					else{
						return checkChildrenHist(curr.getSibling(), word, i);
					}
				}
			}
		}
		return curr;
	}

	private DLBnode fillChildrenHist(String word, int i, DLBnode curr){//Helper for adding words from user history; counts the words
		for(int j = i; j < word.length(); j++){

			curr.setChild(new DLBnode(word.charAt(j)));

			curr = curr.getChild();
			if(curr.getData() == '^'){
				curr.setUses(curr.getUses()+1);
			}
		}
		return curr;
	}
}