import java.util.*;

/*
   Handles Shiritori game mechanics.
*/
public class ShiritoriManager {

   private Map<String, List<String>> dict;
   
   // post: Takes a list of string as parameter.
   //       Creates a map between first letter and the words.
   public ShiritoriManager(List<String> list) {
      dict = new HashMap<String, List<String>>();
      for(String s : list) {
         String frontLetter = s.substring(0,1);
         if(!dict.containsKey(frontLetter)) {
            dict.put(frontLetter, new LinkedList<String>());
         }
         dict.get(frontLetter).add(s);
      }
   }
   
   // post: Returns random word from the map
   //       according to the char parameter.
   public String randomWord(char c) {
      String s = "" + c;
      if(dict.containsKey(s)) {
         List<String> list = dict.get(s);
         int size = list.size();
         Random rand = new Random();
         int chooseRand = rand.nextInt(size);
         String choosenWord = list.get(chooseRand);
         list.remove(chooseRand);
         return choosenWord;
      } else {
         return "N/A";
      }
   }
   
   // post: Checks whether the string is legal to the map
   //       (returns true)
   public boolean check(String s) {
      if(s.equals("N/A")) {
         return false;
      }
      String front = s.substring(0,1);
      List<String> list = dict.get(front);
      boolean check = list.contains(s);
      if(check) {
         remove(s);
         return true;
      } else {
         return false;
      }
   }
   
   public void remove(String word) {
      String front = word.substring(0,1);
      List<String> list = dict.get(front);
      list.remove(word);   
   }
}