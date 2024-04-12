package root.tasks;

// File name to search: crocodile.txt
// Search INPUT ---> Search RESULT
// crcdl ---> true
// rodle ---> true
// ccdre ---> false
// cdl ---> true
// codr ---> false

public class FileSearchInIDE {

    public static void main(String[] args) {
        System.out.println("'crcdl' ---> " + matches("crcdl", "crocodile.txt"));
        System.out.println("'rodle' ---> " + matches("rodle", "crocodile.txt"));
        System.out.println("'ccdre' ---> " + matches("ccdre", "crocodile.txt"));
        System.out.println("'cdl' ---> " + matches("cdl", "crocodile.txt"));
        System.out.println("'codr' ---> " + matches("codr", "crocodile.txt"));
    }

    private static boolean matches(String input, String fileName) {
        int inputPointer = 0;
        for (char fileNameChar : fileName.toCharArray()) {
            if (input.charAt(inputPointer) == fileNameChar) {
                inputPointer++;
            }
            if (inputPointer == input.length()) {
                return true;
            }
        }
        return false;
    }
}
