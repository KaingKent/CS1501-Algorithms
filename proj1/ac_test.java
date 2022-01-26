import java.util.Scanner;
import java.io.*;
import java.util.*;


public class ac_test
{
	private static Scanner reader = new Scanner(System.in);

	public static void main( String args[] ){

		//initialize variables needed
		DLB dict = new DLB();

		String key = "";
		String user = "";
		int choice = 0;
		double average = 0;
		int count = 0;

		ArrayList<String> suggs = new ArrayList<String>();

		File userHistory = new File("user_history.txt");

		dict = load(dict, userHistory); //load the dictionary file and user history file(if possible)


		while(!user.equals("!")){//controls the user interaction process
			user = "";
			
			System.out.print("Enter the first character: ");
			if(reader.hasNextInt()){
				choice = reader.nextInt();
				reader.nextLine();

				if(choice > 0 || choice < 6){//user picks a word
					System.out.println("\nWORD COMPLETED: " + suggs.get(choice-1) + "\n");
					dict.addWordHist(suggs.get(choice-1));
					addtoHistory(suggs.get(choice-1), userHistory);
				}
				key = "";

			}else{
				user = reader.next().substring(0,1);//check if int
				if(user.equals("$")){//user enters a new word
					dict.addWordHist(key);
					System.out.println("\nADDED: " + key + "\n");
					addtoHistory(key, userHistory);
					key = "";
				}
				else if(!user.equals("!")){//enter a valid char and searches for it
					key += user;

					System.out.println();
					long startTime = System.nanoTime();
					suggs = dict.searchDict(key, 0, dict.head.getChild());
					long estTime = System.nanoTime() - startTime;

					average += estTime;
					count++;

					System.out.printf("(%.6f",estTime/1000000000.0);//print time
					System.out.println("s)");
					printSugg(suggs);//print suggestions
					
				}
			}
			while(!user.equals("!")){//similiar to code above 
			
				user = "";
			
				System.out.print("Enter the next character: ");
				if(reader.hasNextInt()){
					choice = reader.nextInt();
					reader.nextLine();

					if(choice > 0 || choice < 6){
						System.out.println("\nWORD COMPLETED: " + suggs.get(choice-1) + "\n");
						dict.addWordHist(suggs.get(choice-1));
						addtoHistory(suggs.get(choice-1), userHistory);
						key = "";
						break;
					}
				}else{
					user = reader.next().substring(0,1);//check if int
					if(user.equals("$")){
						addtoHistory(key, userHistory);
						dict.addWordHist(key);
						System.out.println("\nADDED: " + key + "\n");
						
						key = "";
						break;
					}
					else if(!user.equals("!")){
						key += user;

						System.out.println();
						long startTime = System.nanoTime();
						suggs = dict.searchDict(key, 0, dict.head.getChild());
						long estTime = System.nanoTime() - startTime;

						average += estTime;
						count++;

						System.out.printf("(%.6f",estTime/1000000000.0); 
						System.out.println("s)");
							
						printSugg(suggs);
					}
				}
			}
		}
				
		System.out.printf("\nAverage time: (%.6f",(average/count)/1000000000.0);//print average time
		System.out.println("s)");
	}

	private static DLB load(DLB dict, File userHistory){//loads the files and returns the filled trie

		try{
			BufferedReader dictionary = new BufferedReader( new FileReader( "dictionary.txt" ) );

			while ( dictionary.ready() )
			{
				dict.addWord(dictionary.readLine());
			}
			dictionary.close();
		}
		catch(FileNotFoundException ex) {
            System.out.println("Could not open file 'dictionary.txt'");
            System.exit(0);                
        }
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}


		try{
			userHistory.createNewFile();
			BufferedReader infile = new BufferedReader( new FileReader( userHistory ) );
			while ( infile.ready() )
			{
				String string = infile.readLine();
				dict.addWordHist(string);
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}

		return dict;
	}

	private static void printSugg(ArrayList<String> suggs){//prints the suggestions
		System.out.println("Predictions: \n");
		if(suggs.size() == 0){
			System.out.println("None were found");
		}
		else{
			for(int i = 0; i < suggs.size(); i++){
				System.out.print("(" + (i+1) + ") " + suggs.get(i) + "		");			
			}
			System.out.println("\n");
		}
		
	}

	private static void addtoHistory(String word, File user){//adds the words chosen to the history file
		try{
			BufferedWriter write = new BufferedWriter(new FileWriter(user, true)); 
            write.write(word);
            write.newLine();
            write.close();
		}
		catch(IOException ex){
			ex.printStackTrace();	
		}
	}
}