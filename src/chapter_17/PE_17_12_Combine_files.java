package chapter_17;

import java.io.*;

/**
 * (Combine files) Write a utility program that combines the files together into a
 * new file using the following command:
 *
 *      java Exercise17_12 SourceFile1 . . . SourceFilen TargetFile
 *
 * The command combines SourceFile1, . . . , and SourceFilen into TargetFile.
 */
public class PE_17_12_Combine_files {

    public static void main(String[] args) {
        File[] sourceFiles = getSourceFiles(args);
        File destinationFile = new File(args[args.length - 1]);
        combineFiles(sourceFiles, destinationFile);
    }

    private static void combineFiles(File[] sourceFiles, File destinationFile) {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destinationFile))) {
            for (File sourceFile : sourceFiles) {
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(sourceFile));
                int data;
                while ((data = inputStream.read()) != -1) {
                    outputStream.write(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File[] getSourceFiles(String[] args) {
        int sourceFileCount = args.length - 1;
        File[] sourceFiles = new File[sourceFileCount];
        for (int fileIndex = 0; fileIndex < sourceFileCount; fileIndex++) {
            sourceFiles[fileIndex] = new File(args[fileIndex]);
            if (!sourceFiles[fileIndex].exists()) {
                System.out.println("Not all source files can be found.");
                System.exit(1);
            }
        }
        return sourceFiles;
    }
}
