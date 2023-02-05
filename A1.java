import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/** 
 * COMP 2503 Winter 2023 Assignment 1 
 * 
 * This program must read a input stream and keeps track of the 
 * frequency at which an avenger is mentioned either by name or alias.
 *
 * @author Maryam Elahi
 * @date Winter 2023
*/

public class A1 {

	public String[][] avengerRoster = { { "captainamerica", "rogers" }, { "ironman", "stark" },
			{ "blackwidow", "romanoff" }, { "hulk", "banner" }, { "blackpanther", "tchalla" }, { "thor", "odinson" },
			{ "hawkeye", "barton" }, { "warmachine", "rhodes" }, { "spiderman", "parker" },
			{ "wintersoldier", "barnes" } };

	private int topN = 4;
	private int totalwordcount = 0;
	private ArrayList<Avenger> avengersArrayList = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException {
		A1 a1 = new A1();
		a1.run();
	}

	public void run() throws FileNotFoundException {
		readInput();
		printResults();
	}

	/**
	 * read the input stream and keep track  
	 * how many times avengers are mentioned by alias or last name.
	 * @throws FileNotFoundException 
	 */
	
	
	private void readInput() throws FileNotFoundException {
		File file = new File("input1.txt");
		Scanner scan = new Scanner(file); 

		while (scan.hasNext()) {
			String word = scan.next().toLowerCase().replaceAll("[^a-z]", "");
			if (!word.isEmpty()) {
				totalwordcount++;
				boolean found = false;
				for (String[] avenger : avengerRoster) {
					if (word.equals(avenger[0]) || word.equals(avenger[1])) {
						found = true;
						boolean alreadyAdded = false;
						for (Avenger a : avengersArrayList) {
							if (a.toString().equals(avenger[0])) {
								alreadyAdded = true;
								a.incrementFrequency();
								break;
							}
						}
						if (!alreadyAdded) {
							avengersArrayList.add(new Avenger(avenger[0], avenger[1]));
							avengersArrayList.get(avengersArrayList.size() - 1).incrementFrequency();

						}
						break;
					}
				}
			}
		}
		scan.close();
	}

		/*
		In a loop, while the scanner object has not reached end of stream,
		 	- read a word.
		 	- clean up the word
		    - if the word is not empty, add the word count. 
		    - Check if the word is either an avenger alias or last name then
				- Create a new avenger object with the corresponding alias and last name.
				- if this avenger has already been mentioned, increase the frequency count for the object already in the list.
				- if this avenger has not been mentioned before, add the newly created avenger to the list, remember to set the frequency.
		*/ 

	/**
	 * print the results
	 */
	private void printResults() {
		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + avengersArrayList.size());
		System.out.println();

		System.out.println("All avengers in the order they appeared in the input stream:");
		for (Avenger a : avengersArrayList) {
			System.out.println(a.toString());
		}
		
		// Todo: Print the list of avengers in the order they appeared in the input
		// Make sure you follow the formatting example in the sample output
		System.out.println();
		
		System.out.println("Top " + topN + " most popular avengers:");
		// Todo: Print the most popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output
		System.out.println();

		System.out.println("Top " + topN + " least popular avengers:");
		// Todo: Print the least popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output		
		System.out.println();

		System.out.println("All mentioned avengers in alphabetical order:");
		// Todo: Print the list of avengers in alphabetical order
		// Make sure you follow the formatting example in the sample output
		System.out.println();
	}
}
