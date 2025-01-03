import java.math.BigInteger;

public class Task3 {

    private int sum;
    private BigInteger factorial;

    public Task3() {
        factorial = BigInteger.ONE;
    }

    // Calculating factorial of a given number
    private void calculate(int input) {
        for (long i = 2; i <= input; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        System.out.println(factorial);
    }

    public int factorialSumOfDigits(int factorial) {
        calculate(factorial);
        sumDigits();
        return sum;
    }

    // Calculating digits sum of a factorial
    private void sumDigits() {

        while (factorial.compareTo(BigInteger.ZERO) > 0) {
            sum += factorial.remainder(BigInteger.TEN).intValue();
            factorial = factorial.divide(BigInteger.TEN);
        }

    }

}
