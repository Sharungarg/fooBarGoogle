public class QueueToDo {
    public static int solution(int start, int length) {
        int answer = 0;
        for (int i = 1; i <= length; i++) {
            answer ^= xor(start, start + (length - i));
            start = start + length;
        }
        return answer;
    }

    private static int xor(int start, int end) {
        // XOR has a repeating property (of interval 4) when ordered integers are taken into consideration.

        /* for odd numbers, the repetition is of order: num, num^(end), num-1, (num-1)^end
         * eg. starting with 7:
         * num = 7
         *
         * [7] = 7,          end = 7
         * [7^8] = 15,       end = 8    (7^8)
         * [7-1] = 6,        end = 9    (7^8^9)
         * [[7-1]^10] = 12,  end = 10   (7^8^9^10)
         * [7] = 7,          end = 11   (7^8^9^10^11)
         * [7^12] = 11,      end = 12   (7^8^9^10^11^12)
         * [7-1] = 6,        end = 13   (7^8^9^10^11^12^13)
         * [(7-1)^14] = 8    end = 14   (7^8^9^10^11^12^13^14)
         * [7] = 7           end = 15   (7^8^9^10^11^12^13^14^15)
         * .
         * .
         */
        int[] repetitiveOrder = {start, start ^ end, start - 1, (start - 1) ^ end};

        /* for even number, the repetition is of order: end, 1, end+1 (or end^1), 0
         * eg. starting with 6:
         * num = 6
         *
         * [6] = 6,          end = 6
         * [1] = 1,          end = 7    (6^7)
         * [8+1] = 9,        end = 8    (6^7^8)
         * [0] = 0,          end = 9    (6^7^8^9)
         * [10] = 10,        end = 10   (6^7^8^9^10)
         * [1] = 1,          end = 11   (6^7^8^9^10^11)
         * [12+1] = 13,      end = 12   (6^7^8^9^10^11^12)
         * [0] = 0,          end = 13   (6^7^8^9^10^11^12^13)
         * [14] = 14,        end = 14   (6^7^8^9^10^11^12^13^14)
         * .
         * .
         */

        if (start % 2 == 0) {
            repetitiveOrder = new int[]{end, 1, end + 1, 0};
        }
        return repetitiveOrder[(end - start) % 4];
    }

    public static void main(String[] args) {
        System.out.println(solution(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
    }
}
