public class CaesarCipher {
    private static final String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String RUSSIAN_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static String encrypt(String text, int shift) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Input text cannot be null or empty");
        }
        shift = normalizeShift(shift);
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append(shiftChar(c, shift));
        }
        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Input text cannot be null or empty");
        }
        return encrypt(text, -shift);
    }

    public static String decryptWithoutShift(String text) {
     
        String bestResult = text;
        int bestScore = -1;
        for (int shift = 1; shift < 26; shift++) {
            String candidate = decrypt(text, shift);
            int score = scoreText(candidate);
            if (score > bestScore) {
                bestScore = score;
                bestResult = candidate;
            }
        }
        return bestResult;
    }

    private static int normalizeShift(int shift) {
        shift = shift % 33; 
        if (shift < 0) {
            shift += 33;
        }
        return shift;
    }

    private static char shiftChar(char c, int shift) {
        String alphabet;
        boolean isUpperCase = Character.isUpperCase(c);
        char lowerC = Character.toLowerCase(c);

        if (ENGLISH_ALPHABET.indexOf(lowerC) != -1) {
            alphabet = ENGLISH_ALPHABET;
        } else if (RUSSIAN_ALPHABET.indexOf(lowerC) != -1) {
            alphabet = RUSSIAN_ALPHABET;
        } else {
            return c; 
        }

        int index = alphabet.indexOf(lowerC);
        int newIndex = (index + shift) % alphabet.length();
        if (newIndex < 0) {
            newIndex += alphabet.length();
        }
        char result = alphabet.charAt(newIndex);
        return isUpperCase ? Character.toUpperCase(result) : result;
    }

    private static int scoreText(String text) {
        int score = 0;
        for (char c : text.toLowerCase().toCharArray()) {
            if ("etaoоеа".indexOf(c) != -1) {
                score++;
            }
        }
        return score;
    }
}