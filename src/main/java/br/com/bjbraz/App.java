package br.com.bjbraz;

/**
 * Hello world!
 */
public class App {
    final static char[] items = "abcdefghijklmnopqrstuvxywz".toCharArray();
    final static String NO = "NO";

    public static void main(String[] args) {
        System.out.println(testPalindrome(args[0]));
    }

    private static String testPalindrome(final String originalWord) {
        String wordWithoutMarkers    = removeMarkers(originalWord.toLowerCase());
        long count        = originalWord.chars().filter(ch -> ch == '?').count();
        int[] positions   = getIndexes(originalWord);

        for(int marker = 0; (marker < count) && (count > 0); marker++){
            for(int position = 0; position<positions.length; position++){
                for(int letters = 0 ; letters < items.length; letters++){
                    wordWithoutMarkers = changeChar(wordWithoutMarkers, positions[position], items[letters]);
                    if(isPalindrome(wordWithoutMarkers)){
                        return wordWithoutMarkers;
                    }
                }
            }
        }

        return isPalindrome(wordWithoutMarkers) ? wordWithoutMarkers: NO;
    }

    private static String changeChar(String withouMarkersWord, int position, char item) {
        char[] chars = withouMarkersWord.toCharArray();
        chars[position] = item;
        return new String(chars);
    }

    private static int[] getIndexes(String originalWord) {
        long count = originalWord.chars().filter(ch -> ch == '?').count();
        int[] indexes = new int[(int)count];

        char[] chars = originalWord.toCharArray();
        for(int i = 0; i < chars.length; i++){
            int position = 0;
            if(chars[i] == '?') indexes[position++] = i;
        }
        return indexes;
    }

    private static String removeMarkers(String wordWithMarkers) {
        return wordWithMarkers.replaceAll("\\?", String.valueOf(items[0]));
    }

    private static boolean isPalindrome(final String wordWithoutMarkers){
        String palindromo = "";
        for (int i = wordWithoutMarkers.length() - 1; i >= 0; i--) {
            palindromo += wordWithoutMarkers.charAt(i);
        }
        return palindromo.equals(wordWithoutMarkers);
    }


}
