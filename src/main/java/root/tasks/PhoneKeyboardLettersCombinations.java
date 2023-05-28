package root.tasks;

import java.util.*;
import java.util.stream.Collectors;

//    Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
//
//    A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
//             ------------------------
//            | 1(none) 2(abc) 3(def)  |
//            | 4(ghi)  5(jkl) 6(mno)  |
//            | 7(pqrs) 8(tuv) 9(wxyz) |
//            |  *(+)   0(" ")    #    |
//            -------------------------
//
//    Constraints:
//            -> 0 <= digits.length <= 4
//            -> digits[i] is a digit in the range ['2', '9'].
//
//    //----------------------------------------------------------
//    Example:
//    Input: digits = "23"
//    Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
//
//    Input: digits = ""
//    Output: []
//
//    Input: digits = "2"
//    Output: ["a","b","c"]
//
//    Input: digits = "233"
//    Output: ["add","ade", "adf","aed", "aee","aef","afd","afe","aff", "bdd","bde", "bdf","bed", "bee","bef","bfd","bfe","bff", "cdd","cde", "cdf","ced", "cee","cef","cfd","cfe","cff"]

public class PhoneKeyboardLettersCombinations {

    private static final Map<String, List<String>> KEY_TO_POSSIBLE_LETTERS_MAP = Map.of(
            "2", List.of("a", "b", "c"),
            "3", List.of("d", "e", "f"),
            "4", List.of("g", "h", "i"),
            "5", List.of("j", "k", "l"),
            "6", List.of("m", "n", "o"),
            "7", List.of("p", "q", "r", "s"),
            "8", List.of("t", "u", "v"),
            "9", List.of("w", "x", "y", "z")
    );

    public static void main(String[] args) {
        var expectedResult = List.of("add","ade","adf","aed","aee","aef","afd","afe","aff","bdd","bde","bdf","bed","bee","bef","bfd","bfe","bff","cdd","cde","cdf","ced","cee","cef","cfd","cfe","cff");
        var actualResult = calculateLetterCombinations(List.of("2", "3", "3"));

        assert actualResult.size() == expectedResult.size() : "wrong result set size";
        assert expectedResult.containsAll(actualResult) : "wrong result";

        if (actualResult.size() != expectedResult.size() || !expectedResult.containsAll(actualResult)) {
            System.out.println("WRONG RESULT: " + actualResult);
            return;
        }

        System.out.println("CORRECT RESULT: " + actualResult);
    }

    private static List<String> calculateLetterCombinations(List<String> keys) {
        if (keys.isEmpty()) {
            return List.of();
        }
        if (keys.size() > 4) {
            throw new IllegalArgumentException("Input size should not exceed 4 keys, were provided " + keys.size());
        }
        if (!KEY_TO_POSSIBLE_LETTERS_MAP.keySet().containsAll(keys)) {
            throw new IllegalArgumentException("Invalid key among provided keys " + keys);
        }

        Stack<List<String>> stackOfLetterGroups = new Stack<>();
        keys.forEach(key -> stackOfLetterGroups.push(KEY_TO_POSSIBLE_LETTERS_MAP.get(key)));

        List<String> combinations = stackOfLetterGroups.pop();
        while (!stackOfLetterGroups.empty()) {
            List<String> groupOfLetters = stackOfLetterGroups.pop();
            List<String> previousCombinations = new ArrayList<>(combinations);
            combinations = groupOfLetters.stream()
                    .flatMap(letter -> previousCombinations.stream().map(combination -> letter.concat(combination)))
                    .collect(Collectors.toList());
        }

        return combinations;
    }
}
