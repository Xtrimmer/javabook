package chapter_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * (Add package statement) Suppose you have Java source files under the directories
 * chapter1, chapter2, . . . , chapter34. Write a program to insert the
 * statement package chapteri; as the first line for each Java source file under
 * the directory chapteri. Suppose chapter1, chapter2, . . . , chapter34
 * are under the root directory srcRootDirectory. The root directory and
 * chapteri directory may contain other folders and files. Use the following
 * command to run the program:
 *
 *      java Exercise12_18 srcRootDirectory
 */
public class PE_12_18_Add_package_statement {
    public static void main(String[] args) {
        validateArgsCount(args, 1);
        File srcRootDirectory = getDirectory(args[0]);
        addPackageStatementsToRootDirectory(srcRootDirectory);
    }

    private static void addPackageStatementsToRootDirectory(File srcRootDirectory) {
        File[] fileListing = srcRootDirectory.listFiles();
        ArrayList<File> packageDirectories = getPackageDirectories(fileListing);
        for (File packageDirectory : packageDirectories) {
            addPackageStatementsToPackageDirectory(packageDirectory);
        }
    }

    private static void addPackageStatementsToPackageDirectory(File packageDirectory) {
        File[] fileListing = packageDirectory.listFiles();
        ArrayList<File> javaFiles = getJavaFiles(fileListing);
        String packageName = packageDirectory.getName();
        for (File javaFile : javaFiles) {
            addPackageStatementsToFile(javaFile, packageName);
        }
    }

    private static void addPackageStatementsToFile(File javaFile, String packageName) {
        StringBuilder code = readFile(javaFile);
        String packageStatement = "package " + packageName + ";";
        if (code.indexOf(packageStatement) < 0) {
            code.insert(0, packageStatement + "\n");
        }
        writeFile(javaFile, code);
    }

    private static void writeFile(File file, StringBuilder text) {
        try (PrintWriter fileOut = new PrintWriter(file)) {
            fileOut.print(text);
        } catch (FileNotFoundException e) {
            System.out.println("destination file is not found");
            System.exit(0);
        }
    }

    private static StringBuilder readFile(File file) {
        StringBuilder text = new StringBuilder();
        try (Scanner fileIn = new Scanner(file)) {
            while (fileIn.hasNext()) {
                text.append(fileIn.nextLine());
                if (fileIn.hasNext()) text.append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("source file is not found");
            System.exit(0);
        }
        return text;
    }

    private static ArrayList<File> getJavaFiles(File[] fileListing) {
        ArrayList<File> javaFiles = new ArrayList<>();
        for (File file : fileListing) {
            if (file.isFile()
                    && file.canRead()
                    && file.getName().matches(".+\\.java")) {
                javaFiles.add(file);
            }
        }
        return javaFiles;
    }

    private static ArrayList<File> getPackageDirectories(File[] fileListing) {
        ArrayList<File> packageDirectories = new ArrayList<>();
        for (File file : fileListing) {
            if (file.isDirectory()
                    && file.getName().matches("chapter\\d+")
                    && file.canRead()) {
                packageDirectories.add(file);
            }
        }
        return packageDirectories;
    }

    private static File getDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            System.out.println("Invalid directory.");
            System.exit(0);
        }
        return directory;
    }

    private static void validateArgsCount(String[] args, int argCount) {
        if (args.length != argCount) {
            System.out.println("Invalid parameter count");
            System.exit(0);
        }
    }
}
