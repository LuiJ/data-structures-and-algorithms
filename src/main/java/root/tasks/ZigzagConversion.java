package root.tasks;

// https://leetcode.com/problems/zigzag-conversion/description/

public class ZigzagConversion {

    private static final char DEFAULT_CHARACTER = '\u0000';

    public static void main(String[] args) {
        System.out.println("PAYPALISHIRING ---> " + convert("PAYPALISHIRING", 3) + " == PAHNAPLSIIGYIR");
        System.out.println("PAYPALISHIRING ---> " + convert("PAYPALISHIRING", 4) + " == PINALSIGYAHRPI");
        System.out.println("A ---> " + convert("A", 1) + " = A");
    }

    private static String convert(String string, int nRows) {
        if (string == null || string.length() <= nRows) {
            return string;
        }
        var matrix = createMatrix(string, nRows);
        populateMatrix(matrix, string, nRows);
        return buildString(matrix);
    }

    private static char[][] createMatrix(String string, int nRows) {
        var nColumns = calculateNColumns(string, nRows);
        var matrix = new char[nRows][nColumns];
        for (int i = 0; i < nRows; i++) matrix[i] = new char[nColumns];
        return matrix;
    }

    private static void populateMatrix(char[][] matrix, String string, int nRows) {
        int charIndex = 0;
        int currentX = 0;
        int currentY = 0;
        matrix[currentY][currentX] = string.charAt(charIndex++);
        while(charIndex < string.length()) {
            for (int i = 0; i < nRows - 1; i++) {
                matrix[++currentY][currentX] = string.charAt(charIndex++);
                if (charIndex == string.length()) {
                    return;
                }
            }
            for (int i = 0; i < nRows - 1; i++) {
                matrix[--currentY][++currentX] = string.charAt(charIndex++);
                if (charIndex == string.length()) {
                    return;
                }
            }
        }
    }

    private static String buildString(char[][] matrix) {
        var stringBuilder = new StringBuilder();
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] == DEFAULT_CHARACTER) {
                    continue;
                }
                stringBuilder.append(matrix[y][x]);
            }
        }
        return stringBuilder.toString();
    }

    private static int calculateNColumns(String string, int nRows) {
        int charIndex = 0;
        int currentX = 0;
        while(charIndex < string.length()) {
            for (int i = 0; i < nRows - 1; i++) {
                if (++charIndex == string.length()) {
                    return currentX + 1;
                }
            }
            for (int i = 0; i < nRows - 1; i++) {
                ++currentX;
                if (++charIndex == string.length()) {
                    return currentX + 1;
                }
            }
        }
        return currentX + 1;
    }
}
