package chapter_17;

import java.io.*;

/**
 * (Split files) Suppose you want to back up a huge file (e.g., a 10-GB AVI file) to a
 * CD-R. You can achieve it by splitting the file into smaller pieces and backing up
 * these pieces separately. Write a utility program that splits a large file into smaller
 * ones using the following command:
 *
 *      java Exercise17_10 SourceFile numberOfPieces
 *
 * The command creates the files SourceFile.1, SourceFile.2, . . . , SourceFile.n,
 * where n is numberOfPieces and the output files are about the same size.
 */
public class PE_17_10_Split_files {
    private static File sourceFile;
    private static int pieces;

    public static void main(String[] args) {
        createSourceFile();
        validateArguments(args);
        splitFile();
        showFileSizes();
    }

    private static void createSourceFile() {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("resources/data/Megabyte.dat"))) {
            for (int i = 0; i < 1000000; i++) {
                outputStream.write((int) (Math.random() * 128));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showFileSizes() {
        System.out.println("InputFile size: " + sourceFile.length() + " bytes");
        for (int i = 1; i <= pieces; i++) {
            File file = new File(sourceFile.getAbsolutePath() + "." + i);
            System.out.println("File " + i + " size: " + file.length() + " bytes");
        }
    }

    private static void splitFile() {
        long pieceSize = (long) Math.ceil(1.0 * sourceFile.length() / pieces);
        String fileName = sourceFile.getAbsolutePath();
        try (
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(sourceFile))
        ) {
            int value;
            for (long i = 1; i <= pieces; i++) {
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileName + "." + i));
                long bytesWritten = 0;
                while (bytesWritten++ < pieceSize && (value = inputStream.read()) != -1) {
                    outputStream.write(value);
                }
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateArguments(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Copy sourceFile targetFile");
            System.exit(1);
        }
        sourceFile = new File(args[0]);
        if (!sourceFile.exists()) {
            System.out.println("Source file \"" + args[0] + "\" does not exist");
            System.exit(2);
        }
        try {
            pieces = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing piece count from \"" + args[1] + "\"");
        }
        if (pieces < 2 || pieces > sourceFile.length()) {
            System.out.println("Invalid piece count: \"" + pieces + "\"");
        }
    }
}
