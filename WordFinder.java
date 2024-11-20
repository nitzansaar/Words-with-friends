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
        AnagramDictionary anagramDict = null;
        try {
            String dict = args[0];
            anagramDict = new AnagramDictionary(dict);
        } catch (FileNotFoundException e) {
            System.out.println("Exiting program.");
            System.exit(1);
        } catch (IllegalDictionaryException e) {
            System.out.println("Invalid dictionary format: " + args[0]);
            System.exit(1);
        }

        
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        // System.out.print("Rack? ");
        System.out.println("Type . to quit.");
        System.out.print("Rack? ");
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
            // Remove words with score 0 and sort remaining by score (highest to lowest) and alphabetically
            validWords.removeIf(word -> ScoreTable.getScore(word) == 0);
            Collections.sort(validWords, (a, b) -> {
                int scoreA = ScoreTable.getScore(a);
                int scoreB = ScoreTable.getScore(b);
                if (scoreA != scoreB) {
                    return scoreB - scoreA;  // Higher scores first
                }
                return a.compareTo(b);       // Alphabetical for same score
            });
            System.out.println("We can make " + validWords.size() + " words from \"" + input + "\"");
            System.out.println(validWords.size());
           if (validWords.size() > 1) {
              System.out.println("All of the words with their scores (sorted by score):");

           }
            int count = 0;
            for (String word : validWords) {
                int score = ScoreTable.getScore(word);
                if (score > 0) {
                    System.out.println(score + ": " + word);
                    count++;
                }
            }
            System.out.println(count);
            System.out.print("Rack? ");
            // System.out.println("Enter another string (or '.' to exit):");
        }
        scanner.close();
    }
}