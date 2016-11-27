package chapter_10;

/**
 * (Implement the Character class) The Character class is provided in the Java
 * library. Provide your own implementation for this class. Name the new class
 * MyCharacter.
 */
public class PE_10_24_Implement_the_Character_class {
    public static void main(String[] args) {
        MyCharacter myCharacter1 = new MyCharacter('a');
        MyCharacter myCharacter2 = new MyCharacter('A');
        MyCharacter myCharacter3 = new MyCharacter('0');

        //print charValue()
        System.out.println("myCharacter1.charValue() = " + myCharacter1.charValue());
        System.out.println("myCharacter2.charValue() = " + myCharacter2.charValue());
        System.out.println("myCharacter3.charValue() = " + myCharacter3.charValue());
        System.out.println();

        //compare MyCharacters
        System.out.println("MyCharacter.compare('a', 'A') = " + MyCharacter.compare('a', 'A'));
        System.out.println("MyCharacter.compare('0', '0') = " + MyCharacter.compare('0', '0'));
        System.out.println("MyCharacter.compare('0', 'a') = " + MyCharacter.compare('0', 'a'));
        System.out.println();

        System.out.println("myCharacter1.compareTo(myCharacter2) = " + myCharacter1.compareTo(myCharacter2));
        System.out.println("myCharacter3.compareTo(myCharacter3) = " + myCharacter3.compareTo(myCharacter3));
        System.out.println("myCharacter3.compareTo(myCharacter1) = " + myCharacter3.compareTo(myCharacter1));
        System.out.println();

        // print numericValue()
        System.out.println("myCharacter1.getNumericValue() = " + myCharacter1.getNumericValue());
        System.out.println("myCharacter2.getNumericValue() = " + myCharacter2.getNumericValue());
        System.out.println("myCharacter3.getNumericValue() = " + myCharacter3.getNumericValue());
        System.out.println();

        // print isLetter()
        System.out.println("myCharacter1.isLetter() = " + myCharacter1.isLetter());
        System.out.println("myCharacter2.isLetter() = " + myCharacter2.isLetter());
        System.out.println("myCharacter3.isLetter() = " + myCharacter3.isLetter());
        System.out.println();

        // print isDigit()
        System.out.println("myCharacter1.isDigit() = " + myCharacter1.isDigit());
        System.out.println("myCharacter2.isDigit() = " + myCharacter2.isDigit());
        System.out.println("myCharacter3.isDigit() = " + myCharacter3.isDigit());
        System.out.println();

        // print isLetterOrDigit()
        System.out.println("myCharacter1.isLetterOrDigit() = " + myCharacter1.isLetterOrDigit());
        System.out.println("myCharacter2.isLetterOrDigit() = " + myCharacter2.isLetterOrDigit());
        System.out.println("myCharacter3.isLetterOrDigit() = " + myCharacter3.isLetterOrDigit());
        System.out.println();

        // print isLowerCase()
        System.out.println("myCharacter1.isLowerCase() = " + myCharacter1.isLowerCase());
        System.out.println("myCharacter2.isLowerCase() = " + myCharacter2.isLowerCase());
        System.out.println("myCharacter3.isLowerCase() = " + myCharacter3.isLowerCase());
        System.out.println();

        // print isUpperCase()
        System.out.println("myCharacter1.isUpperCase() = " + myCharacter1.isUpperCase());
        System.out.println("myCharacter2.isUpperCase() = " + myCharacter2.isUpperCase());
        System.out.println("myCharacter3.isUpperCase() = " + myCharacter3.isUpperCase());
        System.out.println();

        // print toUpperCase()
        System.out.println("myCharacter1.toUpperCase() = " + myCharacter1.toUpperCase());
        System.out.println("myCharacter2.toUpperCase() = " + myCharacter2.toUpperCase());
        System.out.println("myCharacter3.toUpperCase() = " + myCharacter3.toUpperCase());
        System.out.println();

        // print toLowerCase()
        System.out.println("myCharacter1.toLowerCase() = " + myCharacter1.toLowerCase());
        System.out.println("myCharacter2.toLowerCase() = " + myCharacter2.toLowerCase());
        System.out.println("myCharacter3.toLowerCase() = " + myCharacter3.toLowerCase());
        System.out.println();

        // print toString()
        System.out.println("myCharacter1.toString() = " + myCharacter1.toString());
        System.out.println("myCharacter2.toString() = " + myCharacter2.toString());
        System.out.println("myCharacter3.toString() = " + myCharacter3.toString());
        System.out.println();

        // print toString()
        System.out.println("MyCharacter.valueOf('!') = " + MyCharacter.valueOf('!').toString());
        System.out.println("MyCharacter.valueOf('1') = " + MyCharacter.valueOf('1').toString());
        System.out.println("MyCharacter.valueOf('q') = " + MyCharacter.valueOf('q').toString());
        System.out.println();
    }

    private static class MyCharacter {
        private final char value;

        MyCharacter(char value) {
            this.value = value;
        }

        static int compare(char x, char y) {
            if (x < y) return -1;
            if (x > y) return 1;
            return 0;
        }

        static MyCharacter valueOf(char c) {
            return new MyCharacter(c);
        }

        char charValue() {
            return value;
        }

        int compareTo(MyCharacter anotherCharacter) {
            if (this.value < anotherCharacter.value) return -1;
            if (this.value > anotherCharacter.value) return 1;
            return 0;
        }

        boolean equals(MyCharacter anotherCharacter) {
            return this.value == anotherCharacter.value;
        }

        int getNumericValue() {
            if (isDigit()) return (this.value - '0');
            else return -1;
        }

        boolean isLetter() {
            return isUpperCase() || isLowerCase();
        }

        boolean isDigit() {
            return value >= '0' && value <= '9';
        }

        boolean isLetterOrDigit() {
            return isLetter() || isDigit();
        }

        boolean isLowerCase() {
            return value >= 'a' && value <= 'z';
        }

        boolean isUpperCase() {
            return value >= 'A' && value <= 'Z';
        }

        char toLowerCase() {
            if (isUpperCase()) return (char) (value + ('a' - 'A'));
            else return value;
        }

        char toUpperCase() {
            if (isLowerCase()) return (char) (value - ('a' - 'A'));
            else return value;
        }

        @Override
        public String toString() {
            return value + "";
        }
    }
}
