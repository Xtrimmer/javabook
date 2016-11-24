package chapter_05;

/**
 * (Display the ASCII character table) Write a program that prints the characters in
 * the ASCII character table from ! to ~. Display ten characters per line. The ASCII
 * table is shown in Appendix B. Characters are separated by exactly one space.
 */
public class PE_05_15_Display_the_ASCII_character_table {
    public static void main(String[] args) {
        for (int i = '!', c = 1; i <= '~'; i++, c++) {
            System.out.print((char) i + " ");
            if (c == 10){
                c = 0;
                System.out.println();
            }
        }
    }
}
