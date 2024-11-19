import java.util.HashMap;

public class ScoreTable {
    private static final HashMap<Character, Integer> LETTER_SCORES = new HashMap<>();
    
    static {
        // 1 point letters
        String onePoint = "AEIOULNSTR";
        for (char c : onePoint.toCharArray()) {
            LETTER_SCORES.put(c, 1);
        }
        
        // 2 point letters
        String twoPoints = "DG";
        for (char c : twoPoints.toCharArray()) {
            LETTER_SCORES.put(c, 2);
        }
        
        // 3 point letters
        String threePoints = "BCMP";
        for (char c : threePoints.toCharArray()) {
            LETTER_SCORES.put(c, 3);
        }
        
        // 4 point letters
        String fourPoints = "FHVWY";
        for (char c : fourPoints.toCharArray()) {
            LETTER_SCORES.put(c, 4);
        }
        
        // 5 point letters
        LETTER_SCORES.put('K', 5);
        
        // 8 point letters
        LETTER_SCORES.put('J', 8);
        LETTER_SCORES.put('X', 8);
        
        // 10 point letters
        LETTER_SCORES.put('Q', 10);
        LETTER_SCORES.put('Z', 10);
    }
    
    /**
     * Calculates the score for a given word based on letter values
     * @param word the word to score (case-insensitive)
     * @return the total score for the word
     */
    public static int getScore(String word) {
        int score = 0;
        for (char c : word.toUpperCase().toCharArray()) {
            score += LETTER_SCORES.getOrDefault(c, 0);
        }
        return score;
    }
}