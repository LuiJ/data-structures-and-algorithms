package root.tasks;

// https://leetcode.com/problems/longest-palindromic-substring/description/

import java.util.ArrayList;
import java.util.List;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println("babad ---> " + findLongestPalindrome("babad"));
        System.out.println("cbbd ---> " + findLongestPalindrome("cbbd"));
    }

    private static String findLongestPalindrome(String string) {
        var longestPalindrome = new ArrayList<Character>();
        for (int i = 0; i < string.length(); i++) {
            var substring = new ArrayList<Character>();
            for (int k = i; k < string.length(); k++) {
                substring.add(string.charAt(k));
                if (isPalindrome(substring) && substring.size() > longestPalindrome.size()) {
                    longestPalindrome = new ArrayList<>(substring);
                }
            }
        }
        return toString(longestPalindrome);
    }

    private static boolean isPalindrome(List<Character> substring) {
        for (int f = 0; f < substring.size(); f++) {
            int b = substring.size() - 1 - f;
            if (f >= b) {
                break;
            }
            if (substring.get(f) != substring.get(b)) {
                return false;
            }
        }
        return true;
    }

    private static String toString(List<Character> listOfChars) {
        var stringBuilder = new StringBuilder();
        listOfChars.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
