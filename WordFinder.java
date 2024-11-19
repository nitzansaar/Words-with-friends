/*
 * Nitzan Saar
 */
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class WordFinder {
    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[]{"sowpods.txt"};
        }
        String dict = args[0];
        AnagramDictionary anagramDict = null;
        
        try {
            anagramDict = new AnagramDictionary(dict);
            System.out.println("Your dictionary is " + dict);
        } catch (FileNotFoundException | IllegalDictionaryException e) {
            System.err.println("Dictionary file not found: " + e.getMessage());
            System.exit(1);
        }    
        
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("\nRack? ");
        
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            
            if (input.equals(".")) {
                break;
            }

            Rack rack = new Rack(input);
            ArrayList<String> subsets = rack.getAllSubsets();
            ArrayList<String> validWords = new ArrayList<>();
            
            for (String subset : subsets) {
                ArrayList<String> anagrams = anagramDict.getAnagramsOf(subset);
                if (anagrams != null) {
                    validWords.addAll(anagrams);
                }
            }

            System.out.println("We can make " + validWords.size() + " words from \"" + input + "\"");

            System.out.println("All of the words (sorted):");
            java.util.Collections.sort(validWords);
            for (String word : validWords) {
                System.out.println(word);
            }
            
            System.out.println("\nEnter another string (or '.' to exit):");
        }
        
        scanner.close();
    }
}