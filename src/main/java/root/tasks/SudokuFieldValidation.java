package root.tasks;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/valid-sudoku/description/

public class SudokuFieldValidation {

    private static final String EMPTY_POSITION = ".";
    private static final int SQUARE_SIDE_SIZE = 3;
    private static final int LINE_SIZE = 9;
    private static final int[][] SQUARES_TOP_LEFT_POINTS = new int[][] {
            {0, 0}, {0, 3}, {0, 6},
            {3, 0}, {3, 3}, {3, 6},
            {6, 0}, {6, 3}, {6, 6}
    };

    public static void main(String[] args) {

        var sudokuField_9x9 = new String[][] {
                {"5", "3", ".", ".", "7", ".", ".", ".", "."},
                {"6", ".", ".", "1", "9", "5", ".", ".", "."},
                {".", "9", "8", ".", ".", ".", ".", "6", "."},
                {"8", ".", ".", ".", "6", ".", ".", ".", "3"},
                {"4", ".", ".", "8", ".", "3", ".", ".", "1"},
                {"7", ".", ".", ".", "2", ".", ".", ".", "6"},
                {".", "6", ".", ".", ".", ".", "2", "8", "."},
                {".", ".", ".", "4", "1", "9", ".", ".", "5"},
                {".", ".", ".", ".", "8", ".", ".", "7", "9"}
        };

        if (isValid(sudokuField_9x9)) {
            System.out.println("Sudoku field is valid");
        }
    }

    public static boolean isValid(String[][] sudokuField) {
        boolean validHorizontalLines = validateHorizontalLines(sudokuField);
        boolean validVerticalLines = validateVerticalLines(sudokuField);
        boolean validSquares = validateSquares(sudokuField);
        return validHorizontalLines && validVerticalLines && validSquares;
    }

    private static boolean validateHorizontalLines(String[][] sudokuField) {
        for (int horizontalLineIndex = 0; horizontalLineIndex < LINE_SIZE; horizontalLineIndex++) {
            String[] horizontalLineValues = sudokuField[horizontalLineIndex];
            if (hasDuplicates(horizontalLineValues)) {
                System.out.println("Wrong values in HORIZONTAL line " + horizontalLineIndex);
                return false;
            }
        }
        return true;
    }

    private static boolean validateVerticalLines(String[][] sudokuField) {
        for (int verticalLineIndex = 0; verticalLineIndex < LINE_SIZE; verticalLineIndex++) {
            String[] verticalLineValues = getVerticalLineValues(verticalLineIndex, sudokuField);
            if (hasDuplicates(verticalLineValues)) {
                System.out.println("Wrong values in VERTICAL line " + verticalLineIndex);
                return false;
            }
        }
        return true;
    }

    private static boolean validateSquares(String[][] sudokuField) {
        for (int[] squareTopLeftPoint : SQUARES_TOP_LEFT_POINTS) {
            String[] squareValues = getSquareValues(squareTopLeftPoint, sudokuField);
            if (hasDuplicates(squareValues)) {
                System.out.println("Wrong values in SQUARE [" + squareTopLeftPoint[0] + "," + squareTopLeftPoint[1] + "]");
                return false;
            }
        }
        return true;
    }

    private static boolean hasDuplicates(String[] values) {
        Map<String, Boolean> valuesInPlaceMap = new HashMap<>() {{
            put("1", Boolean.FALSE);
            put("2", Boolean.FALSE);
            put("3", Boolean.FALSE);
            put("4", Boolean.FALSE);
            put("5", Boolean.FALSE);
            put("6", Boolean.FALSE);
            put("7", Boolean.FALSE);
            put("8", Boolean.FALSE);
            put("9", Boolean.FALSE);
        }};
        for (String value : values) {
            if (value.equals(EMPTY_POSITION)) continue;
            if (!valuesInPlaceMap.get(value)) {
                valuesInPlaceMap.put(value, Boolean.TRUE);
                continue;
            }
            return true;
        }
        return false;
    }

    private static String[] getVerticalLineValues(int verticalLineIndex, String[][] sudokuField) {
        String[] verticalLineValues = new String[LINE_SIZE];
        int valueIndex = 0;
        for (String[] horizontalLine : sudokuField) {
            verticalLineValues[valueIndex++] = horizontalLine[verticalLineIndex];
        }
        return verticalLineValues;
    }

    private static String[] getSquareValues(int[] squareTopLeftPoint, String[][] sudokuField) {
        String[] squareValues = new String[SQUARE_SIDE_SIZE * SQUARE_SIDE_SIZE];
        int valueIndex = 0;
        int initialLineIndex = squareTopLeftPoint[0];
        for (int lineIndex = initialLineIndex; lineIndex < initialLineIndex + SQUARE_SIDE_SIZE; lineIndex++) {
            String[] line = sudokuField[lineIndex];
            int initialPositionIndex = squareTopLeftPoint[1];
            for (int positionIndex = initialPositionIndex; positionIndex < initialPositionIndex + SQUARE_SIDE_SIZE; positionIndex++) {
                squareValues[valueIndex++] = line[positionIndex];
            }
        }
        return squareValues;
    }
}
