package root.tasks;

public class OctalToDecimalConversion
{
    public static void main(String[] args)
    {
        String octalNumber = "2074505";
        long decimalNumber = convert(octalNumber);
        System.out.println("Expected result: 555333");
        System.out.println("Actual result: " + decimalNumber);
    }

    //  result = s[n] * 8^n + ... + s[2] * 8^2 + s[1] * 8^1 + s[0] * 8^0
    private static long convert(String octalNumber)
    {
        long result = 0L;
        int basement = 8;
        int powerOfBasement = 0;
        for (int i = octalNumber.length() - 1; i >= 0; i--)
        {
            int memberOfOctalNumber = Character.getNumericValue(octalNumber.charAt(i));
            result += memberOfOctalNumber * Math.pow(basement, powerOfBasement++);
        }
        return result;
    }
}
