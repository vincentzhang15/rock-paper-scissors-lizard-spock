/**
Vincent Zhang
27 February 2019
Input output interaction question involving rock, paper, scissors, lizard, spock.
ICS3U7-02 Mr. Anthony 
*/

import java.util.Scanner;

// Comments in this code uses "user" and "player". These two words mean the same thing.
public class RockPaperScissorsLizardSpock
{
	// constants for either computer or player throw
	static final int USER_THROW_TYPE = 1;
	static final int COMPUTER_THROW_TYPE = 2;
	
	static void printThrow (int throwType, int whichThrow)
	{
		/*
			Method to print what player or computer throw is.
			"throwType" is either player(1) or computer(2).
			"whichThrow" is one of the following: scissors(1), paper(2), rock(3), lizard(4), or spock(5).
		*/
		
		System.out.print(throwType == USER_THROW_TYPE ? "Player throws " : "Computer throws ");
		// if "throwType" is type user throw (1), then print "Player throws ", else, meaning computer throw (2), print "Computer throws "
		
		switch (whichThrow) // find which throw is passed to this method
		{
			case 1: System.out.print("SCISSORS.\n"); break;
			case 2: System.out.print("PAPER.\n"); break;
			case 3: System.out.print("ROCK.\n"); break;
			case 4: System.out.print("LIZARD.\n"); break;
			case 5: System.out.print("SPOCK.\n"); break;
		}
	}
	
	public static void main (String [] args)
	{
		// input
		Scanner s = new Scanner (System.in);
		System.out.println("Enter your throw (1=Scissors, 2=Paper, 3=Rock, 4=Lizard, 5=Spock):");
		int userThrow = s.nextInt();
		int computerThrow = (int)Math.round(Math.random()*(5-1)) + 1;
		s.close();

		// call method "printThrow" and pass either player or computer throw and what they threw (scissors, paper, rock, lizard, spock)
		printThrow(USER_THROW_TYPE, userThrow);
		printThrow(COMPUTER_THROW_TYPE, computerThrow);
		
		if(userThrow == computerThrow)
		{
			// If user throw and computer throw are the same, then print result and exit program.
			// The importance of exiting the program will be explained further down.
			System.out.println("You Tie!");
			return;
		}
		
		// if user throw and computer throw are not the same, check the following
		String combinations = "12 23 34 45 53 31 14 42 25 51"; // spaces between numbers are to make searching in the string possible
		/*
			"combinations" have the ways the first number can win. These numbers are arranged in a way that
			the first number will defeat the second, that is, the throw that maches the first number
			will always win the game.
				12 --> scissors, paper 	--> first number wins --> scissors win
				23 --> paper, rock 		--> first number wins --> paper win
				34 --> rock, lizard 	--> first number wins --> rock win
				45 --> lizard, spock 	--> first number wins --> lizard win
				53 --> spock, rock 		--> first number wins --> spock win
				31 --> rock, scissors 	--> first number wins --> rock win
				14 --> scissors, lizard --> first number wins --> scissors win
				42 --> lizard, paper 	--> first number wins --> lizard win
				25 --> paper, spock 	--> first number wins --> paper win
				51 --> spock, scissors 	--> first number wins --> spock win
		*/
		
		boolean isFound = combinations.indexOf(Integer.toString(userThrow) + Integer.toString(computerThrow)) != -1 ? true : false;
		/*
				Knowing from "combinations" that the first number will always win, one way of doing this
			question is to always put the user throw first. Moreover, the integer throw, from the user
			and the computer, will be combined in a single string. For example, if the user plays scissors(1) and the
			computer plays paper(2), then the combined string would be "12".
				Next we look in the string "combinations" to see if the combined, user and computer throw, exists
			in the string "combinations". If it does exist, then "isFound" is true. If it doesn't exist then
			"isFound" is false.
				Finally, "String.indexOf()" returns "-1" if the substring is not contained in the string. This
			means as long as "indexOf" does not return "-1", then there is the substring in "combinations", thus,
			the player wins. If "indexOf" is not "-1" is false, in other words, "indexOf()" returns -1, then
			the computer wins because the combined substring cannot be found in the string "combinations". For
			example, if the user plays paper(2) and the computer plays scissors(1), then the substring would look
			like "21". "21" does not exist in "combinations". Since we eliminated all situations where user and
			computer throws are equal, and the substring does not exist in "combinations", meaning user doesn't win,
			then the only possibility left is computer wins.
				On a separate note, it is necessary to exit the program if user and computer throws are equal
			before this section is reached since two of the same numbers would result in "isFound" to be false,
			which if proceeded, would print two outputs when desired only one.
		*/
		
		// if "isFound" is true, then user wins, otherwise, computer wins
		System.out.println(isFound ? "You Win!" : "Computer Wins!");
		
		/*
			A final note, switching the order from
				rock, paper, scissors, lizard, spock --> scissors, paper, rock, lizard, spock
			does not change the concept of the code, it just provides a connected transition from each throw:
			scissors defeats paper, paper defeats rock, rock defeats lizard, etc.
		*/
	}
}