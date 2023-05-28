package root.tasks;

/*
 * Task:
 *
 * Find same number in provided arrays
 * 
 * Input: [1,2,4,5], [5,3,4], [2,2,3,3,4,5,6]
 * Output: 5
 *
 */
public class FindSameNumberInArrays {

    public static void main(String[] args) {

        int[] a1 = new int[] {1,2,4,5};
        int[] a2 = new int[] {5,3,4};
        int[] a3 = new int[] {2,2,3,3,4,5,6};

        int a1Pointer = 0;
        int a2Pointer = 0;
        int a3Pointer = 0;

        int maxArrayLength = a1.length;
        if (a2.length > maxArrayLength) maxArrayLength = a2.length;
        if (a3.length > maxArrayLength) maxArrayLength = a3.length;

        int maxMember = Integer.MIN_VALUE;

        for (int i = 0; i < maxArrayLength; i++) {
            int a1Member = a1[a1Pointer];
            int a2Member = a2[a2Pointer];
            int a3Member = a3[a3Pointer];

            if (a1Member == a2Member && a2Member == a3Member) {
                System.out.println("Target: " + a1Member);
                break;
            }

            if (a1Member > maxMember) maxMember = a1Member;
            if (a2Member > maxMember) maxMember = a2Member;
            if (a3Member > maxMember) maxMember = a3Member;

            if (a1Member < maxMember) a1Pointer++;
            if (a2Member < maxMember) a2Pointer++;
            if (a3Member < maxMember) a3Pointer++;
        }
    }
}
