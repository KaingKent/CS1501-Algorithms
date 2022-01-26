package sdfsdf;

import java.util.Scanner;
import java.io.*;
import java.util.*;


public class autocomplete
{
	private static Scanner reader = new Scanner(System.in);

	public static void main( String args[] ){

		DLB dict = new DLB(args[0]);

		String key = "";
		String user = "";
		int choice = 0;
		double average = 0;
		int count = 0;

		ArrayList<String> suggs = new ArrayList<String>();

		File userHistory = new File("user_history.txt");

		while(!user.equals("!")){
			
			user = "";

			System.out.print("Enter the character: ");
			if(reader.hasNextInt()){
				choice = reader.nextInt();
				reader.nextLine();

				if(choice > 0 || choice < 6){
					System.out.println("\nWORD COMPLETED: " + suggs.get(suggs.size() - choice) + "\n");
					addtoHistory(suggs.get(suggs.size() - choice), userHistory);
                    
				}
				key = "";

			}else{
				user = reader.next().substring(0,1);//check if int
				if(user.equals("$")){
					dict.addWord(key);
					System.out.println("\nADDED: " + key + "\n");
					addtoHistory(key, userHistory);
					key = "";
				}
				else if(!user.equals("!")){
					key += user;

					System.out.println();
					long startTime = System.nanoTime();
					suggs = dict.searchDict(key, 0, dict.head.getChild());
					long estTime = System.nanoTime() - startTime;

					average += estTime;
					count++;

					System.out.printf("%.6f",estTime/1000000000.0); //IF I CAN STOP IT FROM PUTTING EVERY SUGG IN THE ARRAY THIS SHUOLD DECREASE
					System.out.println("s");
					
					printSugg(suggs);
					
				}
			}
		}
		System.out.printf("\n%.6f",(average/count)/1000000000.0); //IF I CAN STOP IT FROM PUTTING EVERY SUGG IN THE ARRAY THIS SHUOLD DECREASE
		System.out.println("s");
		//System.out.println("YEET");
		
	}

	public static void printSugg(ArrayList<String> suggs){
		System.out.println("Predictions: \n");
		for(int i = 0; i < 5; i++){
			if(suggs.size()-i - 1 > -1){
				System.out.print("(" + (i+1) + ") " + suggs.get(suggs.size()-i - 1) + "		");
			}			
		}
		System.out.println("\n");
	}

	public static void addtoHistory(String word, File user){
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
	/*
	1. Get a DLB data structure that accepts some strings you hard code in and puts them in properly. Make a print function for it that will print strings inside of it. 
2. Start taking from the dictionary.txt file and put those strings into the DLB instead of the hard coded strings and make sure it works
3. Start the search process with user input without updating the DLB for user chosen words. Try to find a way to store those in such a way that you could reference back to them later
4. Implement weighted choosing (i.e., words that the user choose show up first when you start down the same path when doing autocomplete)
5. Find a way to store user data between runs of the program

	*/
}
