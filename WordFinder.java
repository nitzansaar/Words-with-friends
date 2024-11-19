/*
 * Nitzan Saar
 * 8106373693
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

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

            System.out.println("All of the words (sorted by score):");
            // Sort words by score (highest to lowest) and then alphabetically
            Collections.sort(validWords, (a, b) -> {
                int scoreA = ScoreTable.getScore(a);
                int scoreB = ScoreTable.getScore(b);
                if (scoreA != scoreB) {
                    return scoreB - scoreA;  // Higher scores first
                }
                return a.compareTo(b);       // Alphabetical for same score
            });
            
            for (String word : validWords) {
                int score = ScoreTable.getScore(word);
                System.out.println(score + ": " + word);
            }
            
            System.out.println("\nEnter another string (or '.' to exit):");
        }
        
        scanner.close();
    }
}