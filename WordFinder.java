/*
 * Nitzan Saar
 * 
 */
import java.util.HashMap;
import java.util.ArrayList;

public class WordFinder {
    public static void main(String[] args) {
        String dict = args[0];
        HashMap<String,ArrayList<String>> hash = new HashMap<>();
        preprocess(dict, hash);
    }

    private static void preprocess(String dict, HashMap<String,ArrayList<String>> hash) {
        System.out.println("Your dictionary is " + dict);
        // read through file and create sets based on sorted versions of words
        /*
         * Possible hashmap implementation where the key is the sorted word
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
        // Print out hashmap contents for testing
        System.out.println("\nHashmap contents:");
        for (String key : hash.keySet()) {
            if (hash.get(key).size() >= 3) {
                System.out.println("Key: " + key + " -> Values: " + hash.get(key));
            }
        }

    }
}
