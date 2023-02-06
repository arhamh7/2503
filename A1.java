import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
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

    private int topN = 4;
    private int totalwordcount = 0;
    private ArrayList<Avenger> avengersArrayList = new ArrayList<>();
    private String[][] avengerRoster = new String[][]{
        {"captainamerica", "rogers"}, {"ironman", "stark"},
        {"blackwidow", "romanoff"}, {"hulk", "banner"}, {"blackpanther", "tchalla"}, {"thor", "odinson"},
        {"hawkeye", "barton"}, {"warmachine", "rhodes"}, {"spiderman", "parker"},
        {"wintersoldier", "barnes"}
    };

    public static void main(String[] args) throws FileNotFoundException {
        A1 object = new A1();
        object.run();

    }

    public void run() throws FileNotFoundException {
        readInput();
        printResults();
    }

    public void readInput() throws FileNotFoundException {

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
        File file = new File("input1.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {

            String word = scan.next().toLowerCase().replace("[^a-a]", "");
            if (!word.isEmpty()) {
                totalwordcount++;
                for (String[] avenger : avengerRoster) {
                    if (word.equals(avenger[0]) || word.equals(avenger[1])) {
                        if (isAlreadyPresent(word)) {
                            Avenger avenger1 = null;
                            if ((avenger1 = getAvenger(word)) != null) {
                                avenger1.incrementFrequency();
                            }
                        } else {
                            avengersArrayList.add(new Avenger(avenger[0], avenger[1]));
                            avengersArrayList.get(avengersArrayList.size() - 1).incrementFrequency();
                        }
                    }
                }

            }

        }
    }

    private boolean isAlreadyPresent(String value) {
        for (Avenger avenger : avengersArrayList) {
            if (avenger.getHeroName().equals(value) || avenger.getHeroAlias().equals(value)) {
                return true;
            }
        }
        return false;
    }

    private Avenger getAvenger(String value) {
        for (Avenger avenger : avengersArrayList) {
            if (avenger.getHeroName().equals(value) || avenger.getHeroAlias().equals(value)) {
                return avenger;
            }
        }
        return null;
    }

    private void printResults() {
        System.out.println("Total number of words: " + totalwordcount);
        // Todo: Print the list of avengers in the order they appeared in the input
        // Make sure you follow the formatting example in the sample output
        System.out.println("Number of Avengers Mentioned: " + avengersArrayList.size());
        System.out.println();

        System.out.println("All avengers in the order they appeared in the input stream:");
        for (Avenger avenger : avengersArrayList) {
            System.out.println(avenger.toString());
        }
        System.out.println();

        System.out.println("Top " + topN + " most popular avengers:");
        // Todo: Print the most popular avengers, see the instructions for tie breaking
        // Make sure you follow the formatting example in the sample output

//        ArrayList<Avenger> firstFour = getFirstFour();
        Collections.sort(avengersArrayList, new AvengerComparator());
        for(int i = 0; i < avengersArrayList.size(); i++)
            if(i < 4){
                System.out.println(avengersArrayList.get(i));
            }
        System.out.println();

        System.out.println("Top " + topN + " least popular avengers:");
        // Todo: Print the least popular avengers, see the instructions for tie breaking
        // Make sure you follow the formatting example in the sample output	
        Collections.sort(avengersArrayList, new AvengerComparatorTwo());
        for(int i = 0; i < avengersArrayList.size(); i++)
            if(i < 4){
                System.out.println(avengersArrayList.get(i));
            }
        
        System.out.println();

        System.out.println("All mentioned avengers in alphabetical order:");
        
        // Todo: Print the list of avengers in alphabetical order
        // Make sure you follow the formatting example in the sample output
        Collections.sort(avengersArrayList);
        for (Avenger avenger : avengersArrayList) {
            System.out.println(avenger.toString());
        }
        System.out.println();
    }

    private ArrayList<Avenger> getFirstFour() {
        Collections.sort(avengersArrayList, new AvengerComparator());
        ArrayList<Avenger> firstFour = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (i < avengersArrayList.size()) {
                firstFour.add(avengersArrayList.get(i));
            }
        }
        return firstFour;
    }

    private ArrayList<Avenger> getLeastFour() {
        Collections.sort(avengersArrayList, new AvengerComparator());
        ArrayList<Avenger> firstFour = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (i < avengersArrayList.size()) {
                firstFour.add(avengersArrayList.get(i));
            }
        }
        return firstFour;
    }
}
