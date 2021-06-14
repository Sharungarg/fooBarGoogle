public class ThreeDivisible {
    public static int solution(int[] l) {
        int[] countArray = new int[11];
        int digitsSum = 0;

        for (int i : l) {
            countArray[i]++;
            digitsSum += i;
        }

        countArray[10] = l.length - 1;

        int remainder = digitsSum % 3;
        if (deleteRemainderElement(countArray, remainder)) {
            return generateNumber(countArray);
        } else {
            return 0;
        }
    }

    private static int generateNumber(int[] countArray) {
        int result = 0;
        int place = countArray[10];
        for (int i = 9; i >= 1; i--) {
            int count = countArray[i];
            while (count > 0) {
                result += i * Math.pow(10, place);
                place--;
                count--;
            }
        }
        return result;
    }

    private static boolean deleteRemainderElement(int[] countArray, int rem) {
        if (rem == 0) return true;
        boolean elementsRemoved = false;

        // Search and remove the smallest element that give the same remainder as input "rem"
        // if input "rem" is 1, elements 1, 4, 7 can be removed.
        for (int i = rem; i <= rem + 6; i += 3) {
            if (countArray[i] != 0) {
                countArray[i]--;
                elementsRemoved = true;
                countArray[10]--;
                break;
            }
        }
        if (elementsRemoved) return true;

        // If there's no single element that can be removed, remove 2 elements that will give that remainder.
        // eg. if we want to remove a combination that gives the input "rem"
        // 2 and 5 give remainder as 2, but 2+5 gives remainder 1.
        if (rem == 1) rem = 2;
        else rem = 1;

        outerLoop:
        for (int i = rem; i <= rem + 6; i += 3) {
            if (countArray[i] != 0) {
                for (int j = i; j <= rem + 6; j += 3) {
                    if (countArray[j] != 0) {
                        if ((i == j) && countArray[i] < 2) continue;
                        countArray[i]--;
                        countArray[j]--;
                        elementsRemoved = true;
                        countArray[10] -= 2;
                        break outerLoop;
                    }
                }
            }
        }

        return elementsRemoved;
    }
}
