/**
 * Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.
 * Note:
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class AddString {
    public static void main(String[] args) {
        String num1 = "9";
        String num2 = "99";
        String result = addStrings(num1, num2);
        System.out.println(result);
    }

    public static String addStrings(String num1, String num2) {
        String number1 = num1.length() > num2.length() ? num1 : num2;
        String number2 = num1.length() > num2.length() ? num2 : num1;
        StringBuilder stringBuilder = new StringBuilder();
        int carry = 0;

        for (int i = 0; i < number2.length(); i++) {
            int sum = (number1.charAt(number1.length() - 1 - i) - '0')
                    + (number2.charAt(number2.length() - 1 - i) - '0');
            stringBuilder.append((sum + carry) % 10 );
            carry = (sum + carry) / 10;
        }

        for (int i = number1.length() - number2.length() - 1; i >= 0; i--) {
            int sum = (number1.charAt(i) - '0') + carry;
            stringBuilder.append(sum % 10);
            carry = sum / 10;
        }

        if (carry > 0) stringBuilder.append(carry);

        stringBuilder = stringBuilder.reverse();
        return stringBuilder.toString();
    }


}
