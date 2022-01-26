/*
 * Dylan Kaing
 * Computer Science 2
 * Iteration_DylanKaing
 * TBD // TODO CHANGE DUE DATE WHEN OUT
 */

import java.util.Scanner; // Imports the java scanner into the class

public class Iteration { // Creates the public class
	
	private static Scanner keyboard = new Scanner(System.in); // Sets the variable keyboard to the java scanner
	
	public static void main(String[] args) {  // Calls the methods
		
		int iterations, number, days; // Creates the integers needed in method
		
		/*-----surpriseSum*/
		
		//surpriseSum(); 
	
		/*-----surpriseSum overloaded set*/
		
		//System.out.print("Please enter a positive non-zero integer to act as the max number of iterations for surpriseSum :");
		
		//iterations = numbertakein();
		
		//surpriseSum(iterations);
		
		/*-----sentinalAverage*/
		
		//sentinalAverage();
		
		/*-----Set for isPrime*/
		
		//System.out.print("Please enter a non-zero integer to see if it is prime :");
		
		//System.out.println("True = Prime ; False = Not Prime: \n");
		
		//number = numbertakein();
		
		//isPrime(number);
		
		/*-----Set for calcSeries*/
		
		//System.out.print("Please enter a non-zero integer to act as the max number of iterations for calcSeries :");
		
		//iterations = numbertakein();
		
		//calcSeries(iterations);
		
		/*-----Set for pennies*/
		
		System.out.print("Please enter the amount of days worked:");
		
		days = numbertakein();
		
		pennies(days);
		
		/*-----stars*/
		
		//stars();
		
		/*-----Set for stars overloaded*/
		
		//System.out.print("Please enter a positive non-zero number ");
		
		//iterations = numbertakein();
		
		//stars(iterations);
		
		/*-----*/
		 
	}
	
/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	public static void surpriseSum() { // When started code will add 0.1 to 0.0 constantly 10 times
		
		surpriseSum(10); // Calls helper method (Overloaded surpriseSum) and iterates 10 times
		
	}
	
/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	public static void surpriseSum(int iterations) { // Will iterate the amount of times the user specifies
	
		double sum = 0.0; // Creates the double sum and sets it equal to 0.0
		
		for(int timer = 0; timer < iterations; timer++) { // For loop that ends when timer is equal to 10 
			sum = sum + 0.1; // Adds 0.1 to sum per loop
			System.out.println("sum = " + sum); // Prints out the statement of what sum is equal to
		}
		
	}

/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

// sentinelAverage basic foundation
	
		public static void sentinalAverage() { // Will take in all positive doubles that the user enters and averages it
			
			String name; // Creates String name
			
			int counter = 0, stop = 0; // Creates the necessary integers and sets counter and stop to 0
			
			double holder, total = 0, average; // Creates the doubles needed
			
			System.out.print("\nPlease enter your name: "); // Prints a statements and asks the user for their name
			name = keyboard.nextLine(); // Takes in what the user has entered and sets it as their name
			
			System.out.println("Please enter the numbers to average, enter -1 to end: \n \n" + name + "'s Numbers: "); // Gives the user instructions
			
			do { // Takes in the user's numbers until -1 is entered

				if(keyboard.hasNextDouble()) { // Checks if user entered a double
					holder = keyboard.nextDouble(); // Sets holder to the double entered
					if(holder == -1) { // If the double entered is -1 then "do" loop will stop
						stop = -1; // Sets stop to -1
					}
					else if(holder < 0 && holder != -1) { // If the double is not -1 then will check if it is a negative number
						System.out.println("*Bad Input Value"); // If the number is negative or less than zero, "Bad Input Value" will be printed
						keyboard.nextLine(); // Takes in new line character from when the holder was taking in a double value
						stop = 0; // Sets stop to 0 so loop doesn't end
					}
					else { // If positive will add the double to the total and increase the counter by 1.
						counter++; // Increases counter by 1
						total = holder + total; // Adds holder and total to get full total
						stop = 0; // Sets stop to 0
						keyboard.nextLine(); // Consumes new line character
					}
				
				}
				else { // If a number is not entered or if a string is entered
					System.out.println("*Bad Input Value*"); // If string is entered "Bad Input Value" will print
					keyboard.nextLine(); // Consumes new line character
					stop = 0; // Sets stop to 0
				}
				
			} while(stop != -1); // After loops through once will check is stop is equal to -1 and if it is will stop
			
			average = total / counter; // To calculate the average takes the total and divided by counter
			
			System.out.println("\nAverage: " + average); // Prints the average to the user
			
		}

/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

// isPrime basic foundation
		
		public static void isPrime(int number) { // Checks if the number the user entered is prime
			
			int counter = 2; // Creates the int counter and sets it to 2
			
			boolean prime = true; // creates the boolean prime and sets it to true since needed or line 149 won't work.
			
			if(number == 1) { // If the number is equal to 1 then sets prime to false, since if number is 1 it won't go through "while" loop
				
				prime = false; // Sets prime to false
					
			}
				
			while(counter < number) { // Determine if number is prime or not; if prime set boolean to true;
				
				if(number % counter == 0 ) { // Checks if the mod of number and counter is 0, if 0 counter goes evenly into number
					
					prime = false; // Sets prime to false for not a prime number
					
				}
				
				counter++; // Increases the counter by one after the if
				
			}
				
			System.out.println(prime); // Prints out true or false based on the boolean prime
			
		}

/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

// calcSeries basic foundation
		
		public static void calcSeries(int number) { // Takes in an integer and iterates that amount of time to figure out the sum of all going through the equation -1*(2*(-0.5^x)
			
			double sum = 0, exp = 0, holder = 1, eTotal = 0; // Creates the doubles need which are all initialized 
			
			while(holder <= number) { // While loop continues until holder is greater than number
				
				exp = Math.pow(-0.5, (holder)); // Calculates the -0.5 to the power of holder, which is increased by one after each iteration
				
				eTotal = -1*(2*exp); // Calculates eTotal
				
				holder++; // Increases holder by one
				
				sum = sum + eTotal; // Calculates the whole total of sum and eTotal for each iteration
				
			}
			
			System.out.println("sum = "+ sum); // Prints the final sum of all the calculations
			
		}

/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

// pennies basic foundation
		
		public static void pennies(int days) { // Prints a 3 column table with days, penny amount and total 
			
			int a = 1; // Creates the int a and sets it to 1 for the first day 
			
			double total = 0, holder = 0, exp = 0; // Creates the double total, holder, and exp which are all set to 0
			
			//System.out.print("| Days | Pennies | Total |");
			
			System.out.printf("%s %39s %35s", "Days", "Pennies", "Total \n");
			System.out.println("-------------------------------------------------------------------------------");
			
			while(a <= days) { // Continues until a is greater than days
				
				exp = Math.pow(2, (a-1)); // The double exp is equal to the base of 2 to the power of a - 1
				
				holder = 0.01*exp; // The double holder is equal to the double exp times 0.01
				
				total = holder + total; // Takes holder and adds it onto the total
				
				//System.out.printf("\n| " + a + " | $%.2f | $%.2f |", holder, total);

				String hold = String.format("$%.2f", holder);
				String tot = String.format("$%.2f", total);
				
				System.out.printf("%-5s  |%35s|%35s", a, hold, tot + "\n");
				
				a++; // Increases a by 1 after each iteration
				
			}
				
			//System.out.printf("Total = $%.2f", total); // Test to make sure value prints
			
		}

/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

// stars basic foundation
		
		public static void stars() { // Prints a star pyramid with a max of 5 in middle row 
			
			stars(5); // Calls helper method (stars overloaded) and iterates 5 times
				
		}
		
/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

// stars ; overloaded basic foundation
		
		public static void stars(int iterations) { // Prints a star pyramid with a max that is determined by the user
			
			for(int i = 0; i < iterations; i++) { // For loop iterates until i is greater than iterations, controls amount of rows, build up of pyramid on top ( increases stars )
				for(int j = 0; j < i; j++) { // Nested For loop iterates till j is greater than i, controls amount of stars in a row
					System.out.print("*"); // Prints one star
				}
				System.out.println(); // Creates a new line after nested for loop is done iterating
			}
			for(int i = iterations; i > 0; i--) { // After first for loop is done will activate, controls amount of rows, decrease of pyramid on bottom ( decreases stars)
				for(int j = 0; j < i; j++) { // Nested For loop iterates till j is greater than i, controls amount of stars in the row
					System.out.print("*"); // Prints one star
				}
				System.out.println(); // Creates a new line after nested for loop is done with iterations
			}
		}

/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
		
		public static int numbertakein() { // Helper method to gather the positive integer that the user enters that multiple methods need
			
			int number = -5, holder = 0; // Creates two integers number and holder setting them both to a value
			
			while (number == -5) { // Will iterate while number is set to -5
				if(keyboard.hasNextInt()) { // Checks if the user has entered an integer
					holder = keyboard.nextInt(); // If it is an integer will take in the int and store it in holder
					keyboard.nextLine(); // consumes new line character
				
					if(holder > 0) { // If holder is greater than 0 will
						number = holder; // Sets number to holder to end the while loop
					}
				
					else { // This will activate only if holder is less than or equal to 0
						System.out.println("\n(A negative integer or 0 has been entered)"); // Prints error message
						number = -5; // Sets number to -5 so while loop will continue till a valid integer is entered
					}
				
				}
				else { // If an string is entered
					keyboard.nextLine(); // Consumes the new line
					System.out.println("\n(A non-zero positive number has not been entered)"); // Prints error message
					number = -5; // Sets number to -5 so the while loop will continue
				}
			}
			
			return number; // Returns the value of number back to be set to a variable
			
		}
		
}
