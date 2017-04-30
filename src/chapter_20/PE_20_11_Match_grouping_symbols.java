package chapter_20;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;

/**
 * (Match grouping symbols) A Java program contains various pairs of grouping
 * symbols, such as:
 *
 * - Parentheses: ( and )
 * - Braces: { and }
 * - Brackets: [ and ]
 *
 * Note that the grouping symbols cannot overlap. For example, (a{b)} is illegal.
 * Write a program to check whether a Java source-code file has correct pairs of
 * grouping symbols. Pass the source-code file name as a command-line argument.
 */
public class PE_20_11_Match_grouping_symbols {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
        File file = new File(args[0]);
        String fileData = new String(Files.readAllBytes(file.toPath()));
        String result = isGroupedCorrectly(fileData) ? "has " : "does not have ";
        System.out.println("The file " + result + "the correct pairs of grouping symbols");
    }

    private static boolean isCloser(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    private static boolean isGroupedCorrectly(String fileData) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < fileData.length(); i++) {
            Character character = fileData.charAt(i);
            if (isOpener(character)) {
                stack.push(character);
            } else if (isCloser(character)) {
                if (stack.isEmpty()) return false;
                if (isMatch(stack.peek(), character)) {
                    stack.pop();
                } else return false;
            }
        }
        return stack.isEmpty();
    }

    private static boolean isMatch(char opener, char closer) {
        return (opener == '(' && closer == ')') ||
                (opener == '[' && closer == ']') ||
                (opener == '{' && closer == '}');
    }

    private static boolean isOpener(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    private static void validateArgs(String[] args) {
        if (args.length != 1) {
            System.out.println("Wrong number of arguments");
            System.exit(0);
        }
    }
}
