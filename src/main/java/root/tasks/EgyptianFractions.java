package root.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class EgyptianFractions
{
    private static final String FRACTION_FORMAT = "%d/%d";
    private static final String UNIT_FRACTION_FORMAT = "1/%d";
    private static final String FRACTION_DELIMITER = "/";
    private static final String EGYPTIAN_FRACTIONS_DELIMITER = " + ";

    public static void main(String[] args)
    {
        System.out.println("Egyptian fractions of 3/9 is " + build("3/9"));
        System.out.println("Egyptian fractions of 2/3 is " + build("2/3"));
        System.out.println("Egyptian fractions of 6/14 is " + build("6/14"));
        System.out.println("Egyptian fractions of 3/7 is " + build("3/7"));
    }

    private static String build(String fraction)
    {
        List<String> egyptianFractions = new ArrayList<>();
        extractEgyptianFractions(fraction, egyptianFractions);
        return egyptianFractions.stream()
                .collect(Collectors.joining(EGYPTIAN_FRACTIONS_DELIMITER, "[", "]"));
    }

    private static void extractEgyptianFractions(String fraction, List<String> egyptianFractions)
    {
        String[] fractionParts = fraction.split(FRACTION_DELIMITER);
        int numerator = Integer.parseInt(fractionParts[0]);
        int denominator = Integer.parseInt(fractionParts[1]);
        if (denominator % numerator == 0)
        {
            egyptianFractions.add(format(UNIT_FRACTION_FORMAT, denominator / numerator));
            return;
        }
        numerator *= 2;
        denominator *= 2;
        int nextNumerator = numerator;
        while (nextNumerator > 0)
        {
            nextNumerator--;
            if (denominator % nextNumerator == 0)
            {
                egyptianFractions.add(format(UNIT_FRACTION_FORMAT, denominator / nextNumerator));
                int remainingNumerator = numerator - nextNumerator;
                String remainingFraction = format(FRACTION_FORMAT, remainingNumerator, denominator);
                extractEgyptianFractions(remainingFraction, egyptianFractions);
                break;
            }
        }
    }
}
