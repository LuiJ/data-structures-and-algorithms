package root.tasks;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println("abcabcbb ---> " + lengthOfLongestSubstring("abcabcbb"));
        System.out.println("bbbbb ---> " + lengthOfLongestSubstring("bbbbb"));
        System.out.println("pwwkew ---> " + lengthOfLongestSubstring("pwwkew"));
    }

    private static int lengthOfLongestSubstring(String string) {
        int maxSubstringLength = 0;
        var set = new HashSet<Character>();
        for (int i = 0; i < string.length(); i++) {
            if (set.contains(string.charAt(i))) {
                maxSubstringLength = Math.max(maxSubstringLength, set.size());
                set.clear();
            }
            set.add(string.charAt(i));
        }
        return maxSubstringLength;
    }
}
