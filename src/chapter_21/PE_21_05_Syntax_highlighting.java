package chapter_21;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * (Syntax highlighting) Write a program that converts a Java file into an HTML
 * file. In the HTML file, the keywords, comments, and literals are displayed in
 * bold navy, green, and blue, respectively. Use the command line to pass a Java file
 * and an HTML file. For example, the following command
 *
 *      java Exercise21_05 Welcome.java Welcome.html
 *
 * converts Welcome.java into Welcome.html. Figure 21.8a shows a Java file. The
 * corresponding HTML file is shown in Figure 21.8b.
 */
public class PE_21_05_Syntax_highlighting {

    private static final int JAVA_FILE_PATH = 0;
    private static final int HTML_FILE_PATH = 1;
    private static final String HTML_HEADER = "<!DOCTYPE html><html><body><pre>";
    private static final String HTML_FOOTER = "</pre></body></html>";
    private static final String DELIMITERS = "(?=[\\s\\[\\](){}:;,.\"'+\\-])|(?<=[\\s\\[\\](){}:;,.\"'+\\-])";
    private static final String NUMERIC_LITERAL = "\\d+([fFdD]|[eE]\\d+)*|0[xX][\\d[a-f][A-F]]*|0[Bb][01]+";
    private static final String[] JAVA_KEYWORDS = {"abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum",
            "extends", "for", "final", "finally", "float", "goto",
            "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private",
            "protected", "public", "return", "short", "static",
            "strictfp", "super", "switch", "synchronized", "this",
            "throw", "throws", "transient", "try", "void", "volatile",
            "while", "true", "false", "null"};

    public static void main(String[] args) {
        validateArgs(args);
        String text = getTextFromFilePath(args[JAVA_FILE_PATH]);
        createHtmlFile(text, args[HTML_FILE_PATH]);
    }

    private static String convertTextToHtml(String text) {
        text = text.replaceAll("\r", "");
        Set<String> keywords = new HashSet<>(Arrays.asList(JAVA_KEYWORDS));
        StringBuilder stringBuilder = new StringBuilder(HTML_HEADER);
        String[] tokens = text.split(DELIMITERS);
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.contains("//")) {
                stringBuilder.append("<font color='green'>");
                stringBuilder.append(token);
                while (!(token = tokens[++i]).contains("\n")) {
                    stringBuilder.append(token);
                }
                stringBuilder.append("</font>");
                stringBuilder.append(token);
            } else if (token.contains("/*")) {
                stringBuilder.append("<font color='green'>");
                stringBuilder.append(token);
                while (!(token = tokens[++i]).contains("*/")) {
                    stringBuilder.append(token);
                }
                stringBuilder.append(token);
                stringBuilder.append("</font>");
            } else if (token.contains("\"")) {
                stringBuilder.append("<font color='blue'>");
                stringBuilder.append(token);
                i++;
                while (!(token = tokens[i]).contains("\"")) {
                    stringBuilder.append(token);
                    i++;
                }
                stringBuilder.append(token);
                stringBuilder.append("</font>");
            } else if (token.contains("'")) {
                stringBuilder.append("<font color='blue'>");
                stringBuilder.append(token);
                i++;
                while (!(token = tokens[i]).contains("'")) {
                    stringBuilder.append(token);
                    i++;
                }
                stringBuilder.append(token);
                stringBuilder.append("</font>");
            } else if (isNumeric(token)) {
                stringBuilder.append("<font color='blue'>");
                stringBuilder.append(token);
                stringBuilder.append("</font>");
            } else if (keywords.contains(token)) {
                stringBuilder.append("<b><font color='navy'>");
                stringBuilder.append(token);
                stringBuilder.append("</font></b>");
            } else {
                stringBuilder.append(token);
            }
        }
        stringBuilder.append(HTML_FOOTER);
        return stringBuilder.toString();
    }

    private static void createHtmlFile(String text, String htmlFileName) {
        try (PrintWriter printWriter = new PrintWriter(htmlFileName)) {
            String html = convertTextToHtml(text);
            printWriter.append(html);
        } catch (FileNotFoundException e) {
            System.out.println("Html file not found.");
            System.exit(2);
        }
    }

    private static String getTextFromFilePath(String filePath) {
        Path path = Paths.get(filePath);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }

    private static boolean isNumeric(String token) {
        return token.matches(NUMERIC_LITERAL);
    }

    private static void validateArgs(String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong number of arguments.");
            System.exit(1);
        }
    }
}
