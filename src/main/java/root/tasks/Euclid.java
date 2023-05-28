package root.tasks;

/*
 * Task:
 *
 * Divide area of 1680x640 meters evenly into square plots.
 *
 */
public class Euclid {

    public static void main(String[] args) {
        int minSide = 640;
        int maxSide = 1680;
        int squarePlotSide = calculate(minSide, maxSide);
        System.out.println(
                "Area of " + maxSide + "x" + minSide + " can be evenly divided into " +
                        squarePlotSide + "x" + squarePlotSide + " square plots");
    }

    private static int calculate(int min, int max) {
        int remainder = max % min;
        if (remainder == 0) {
            return min;
        }
        return calculate(remainder, min);
    }
}
