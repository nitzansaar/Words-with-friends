// Name: Nitzan Saar
// USC NetID: 8106373693
// CS 455 PA4
// Fall 2024

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {
   private HashMap<String, ArrayList<String>> anagramMap;

   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,
                                                    IllegalDictionaryException {
      anagramMap = new HashMap<>();
      try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
         String line;
         while ((line = reader.readLine()) != null) {
            // Convert word to char array, sort it, and convert back to string to use as key
            char[] chars = line.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);
            
            // Get or create ArrayList for this key
            ArrayList<String> anagrams = anagramMap.get(sortedWord);
            if (anagrams == null) {
               anagrams = new ArrayList<>();
               anagramMap.put(sortedWord, anagrams);
            }
            
            // Check for duplicates
            if (anagrams.contains(line)) {
               System.out.println("ERROR: Illegal dictionary: dictionary file has a duplicate word: " + line);
               throw new IllegalDictionaryException("ERROR: Illegal dictionary: dictionary file has a duplicate word:" + line);
            }
            
            // Add original word to list of anagrams
            anagrams.add(line);
         }
      } catch (IOException e) {
         throw new FileNotFoundException("Error reading dictionary file: " + fileName);
      }
   }
   
   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String string) {
      char[] chars = string.toCharArray();
      Arrays.sort(chars);
      String sortedString = new String(chars);
      
      ArrayList<String> anagrams = anagramMap.get(sortedString);
      return anagrams != null ? anagrams : new ArrayList<>();
   }
}