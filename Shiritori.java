import java.util.*;
import java.io.*;

/*
   Simple Word chain game. Handles User Interface, use dict.txt
   as list of words.
*/
public class Shiritori {

   // pre: File name is legal (Throws FileNotFoundException if not)
   // post: Creates a list of words from txt file. Passes it to 
   //       ShiritoriManager.
   public static void main(String[] args) throws FileNotFoundException {
      intro();
      Scanner input = new Scanner(new File("dict.txt"));
      List<String> list = new ArrayList<String>();
      while(input.hasNextLine()) {
         list.add(input.nextLine());
      }
      ShiritoriManager sm = new ShiritoriManager(list);
      Scanner console = new Scanner(System.in);
      versusComp(console, sm);
   }
   
   // post: Prints introduction of the game
   public static void intro() {
      System.out.println("Shiritori!");
      System.out.println();
      System.out.println("This is the english version of shiritori");
      System.out.println();
   }
   
   // post: Takes a scanner and ShiritoriManager as parameter. 
   //       Handles game cycle.
   public static void versusComp(Scanner console, ShiritoriManager sm) {
      System.out.println("\t\t Player Vs. Computer ");
      System.out.println();
      boolean gameOver = false;
      Random rand = new Random();
      char randChar = (char)(97 + rand.nextInt(26));
      System.out.println("\t\t\tStart!");
      while(!gameOver) {
         System.out.println();
         System.out.println("Player's Turn : ");
         System.out.println();
         System.out.println("Starting Letter : " + randChar);
         System.out.println("Word : ? (Type \"N/A\" to Give Up)");
         String word = console.next();
         boolean check = sm.check(word);
         while(!check) {
            if(word.equals("N/A")) {
               gameOver = true;
               System.out.println();
               System.out.println("\t\t\tPlayer Has Run Out Of Words!");
               System.out.println("\t\t\tComputer Wins!");
               check = true;
            } else {
               System.out.println("No Such Word!!");
               System.out.println("Try Again!");
               System.out.println("Word : ? ");
               word = console.next();
               check = sm.check(word);
            }
         }
         if(gameOver) {
            break;
         }
         int wordSize =	word.length();
			randChar	= word.charAt(wordSize - 1);
			System.out.println();
			System.out.println("\t\t\t\tComputer's Turn : ");
			System.out.println();
			System.out.println("\t\t\tStarting Letter : " +	randChar);
			System.out.println("\t\t\tWord : ? ");
			word = sm.randomWord(randChar);
			if(word.equals("N/A")) {
				gameOver	= true;
				System.out.println();
				System.out.println("\t\t\tComputer Has Ran Out Of Words!");
				System.out.println("\t\t\tPlayer Wins!");
			} else {
				System.out.println("\t\t\t" +	word);
				wordSize =	word.length();
				randChar	= word.charAt(wordSize - 1);
			}
      }   
   }
   
}