// Name: 
// USC NetID: 
// CS 455 PA4
// Fall 2024

import java.util.ArrayList;
import java.util.HashMap;
/**
   A Rack of Scrabble tiles
 */

public class Rack {
   private final String rack;
   
   public Rack(String input) {
      this.rack = input;
   }

   /**
    * Gets all possible subsets of letters from the rack that form valid words.
    * This method processes the rack string into the format needed by allSubsets,
    * gets all possible combinations, and filters for only valid dictionary words.
    * @param dictionary HashMap containing valid words (sorted characters as key, list of words as value)
    * @return ArrayList<String> containing all valid words that can be made from the rack
    */
   public ArrayList<String> getAllSubsets(HashMap<String,ArrayList<String>> dictionary) {
      // Create string of unique chars and array of their multiplicities
      StringBuilder unique = new StringBuilder();
      ArrayList<Character> seen = new ArrayList<>();
      int[] mult = new int[26]; // max possible unique letters
      
      // Count occurrences of each character
      for (char c : rack.toCharArray()) {
         if (!seen.contains(c)) {
            seen.add(c);
            unique.append(c);
         }
         mult[seen.indexOf(c)]++;
      }
      
      // Get all possible letter combinations
      ArrayList<String> allCombos = allSubsets(unique.toString(), mult, 0);
      ArrayList<String> validWords = new ArrayList<>();
      
      // Filter for valid words
      for (String combo : allCombos) {
         if (!combo.isEmpty()) {  // Skip empty string
            char[] chars = combo.toCharArray();
            java.util.Arrays.sort(chars);
            String sorted = new String(chars);
            
            if (dictionary.containsKey(sorted)) {
               validWords.addAll(dictionary.get(sorted));
            }
         }
      }
      
      return validWords;
   }


   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

   
}
