import java.math.BigInteger;

public class BombBaby {
    public static String solution(String x, String y) {
        BigInteger mach = new BigInteger(x);
        BigInteger facula = new BigInteger(y);
        BigInteger result = BigInteger.ZERO;

        while (!min(mach, facula).equals(BigInteger.ONE)) {
            BigInteger[] division = max(mach, facula).divideAndRemainder(min(mach, facula));
            BigInteger quotient = division[0];
            BigInteger remainder = division[1];
            if (remainder.equals(BigInteger.ZERO)) {
                return "impossible";
            }
            result = result.add(quotient);
            facula = min(mach, facula);
            mach = remainder;
        }
        result = result.add(max(mach, facula)).subtract(BigInteger.ONE);
        return result.toString();
    }

    private static BigInteger min(BigInteger a, BigInteger b) {
        if (b.compareTo(a) < 0) {
            return b;
        }
        return a;
    }

    private static BigInteger max(BigInteger a, BigInteger b) {
        if (b.compareTo(a) > 0) {
            return b;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(solution(args[0], args[1]));
    }
}
