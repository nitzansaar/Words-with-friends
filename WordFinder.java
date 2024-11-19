/*
 * Nitzan Saar
 * 
 */
import java.util.HashMap;
import java.util.ArrayList;

public class WordFinder {
    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[]{"sowpods.txt"};
        }
        String dict = args[0];
        HashMap<String,ArrayList<String>> hash = new HashMap<>();
        preprocess(dict, hash);
        // now we have a hashmap with the stored anagrams
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("\nRack? ");
        
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            
            if (input.equals(".")) {
                break;
            }

            Rack rack = new Rack(input);
            ArrayList<String> subsets = rack.getAllSubsets(hash);

            System.out.println("We can make " + subsets.size() + " words from \"" + input + "\"");

            System.out.println("All of the words (sorted):");
            java.util.Collections.sort(subsets);
            for (String subset : subsets) {
                System.out.println(subset);
            }
            
            // // Convert input to char array, sort it, and convert back to string to use as key
            // char[] inputChars = input.toCharArray();
            // java.util.Arrays.sort(inputChars);
            // String sortedInput = new String(inputChars);
            
            // // Look up anagrams in hashmap
            
            // ArrayList<String> anagrams = hash.get(sortedInput);
            // int numAnagrams = 0;
            // if (anagrams != null) {
            //     numAnagrams = anagrams.size();
            // }c

            // if (anagrams != null && !anagrams.isEmpty()) {
            //     // System.out.println("\nAll of the words but not with their score yet");
            //     for (String anagram : anagrams) {
            //         System.out.println(anagram);
            //     }
            // } else {
            //     System.out.println("\nNo anagrams found for '" + input + "'");
            // }
            
            System.out.println("\nEnter another string (or '.' to exit):");
        }
        
        scanner.close();
    }

    private static void preprocess(String dict, HashMap<String,ArrayList<String>> hash) {
        System.out.println("Your dictionary is " + dict);
        // read through file and create sets based on sorted versions of words
        /*
         * hashmap implementation where the key is the sorted word
         * and the value is an arraylist of the words
         */
        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(dict));
            String line;
            while ((line = reader.readLine()) != null) {
                // Convert word to char array, sort it, and convert back to string to use as key
                char[] chars = line.toCharArray();
                java.util.Arrays.sort(chars);
                String sortedWord = new String(chars);
                
                // Get or create ArrayList for this key
                ArrayList<String> anagrams = hash.get(sortedWord);
                if (anagrams == null) {
                    anagrams = new ArrayList<>();
                    hash.put(sortedWord, anagrams);
                }
                
                // Add original word to list of anagrams
                anagrams.add(line);
            }
            reader.close();
        }
        catch (java.io.IOException e) {
            System.err.println("Error reading dictionary file: " + e.getMessage());
            System.exit(1);
        }

    }
}
