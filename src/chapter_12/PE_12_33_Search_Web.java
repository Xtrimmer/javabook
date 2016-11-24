package chapter_12;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * (Search Web) Modify Listing 12.18 WebCrawler.java to search for the word
 * Computer Programming starting from the URL http://cs.armstrong.edu/liang.
 * Your program terminates once the word is found. Display the URL for the page
 * that contains the word.
 */
public class PE_12_33_Search_Web {
    private static final String URL = "http://cs.armstrong.edu/liang";
    private static final String LOOKUP_TEXT = "Computer Programming";

    public static void main(String[] args) {
        crawler(URL); // Traverse the Web from the a starting url
    }

    public static void crawler(String startingURL) {
        ArrayList<String> listOfPendingURLs = new ArrayList<>();
        ArrayList<String> listOfTraversedURLs = new ArrayList<>();
        listOfPendingURLs.add(startingURL);
        while (!listOfPendingURLs.isEmpty()) {
            String urlString = listOfPendingURLs.remove(0);
            if (!listOfTraversedURLs.contains(urlString)) {
                listOfTraversedURLs.add(urlString);
                System.out.println("Craw " + urlString);
                for (String s : getSubURLs(urlString)) {
                    if (!listOfTraversedURLs.contains(s))
                        listOfPendingURLs.add(s);
                }
            }
        }
    }

    public static ArrayList<String> getSubURLs(String urlString) {
        ArrayList<String> list = new ArrayList<>();
        try {
            java.net.URL url = new java.net.URL(urlString);
            Scanner input = new Scanner(url.openStream());
            int current = 0;
            while (input.hasNext()) {
                String line = input.nextLine();
                if (line.contains(LOOKUP_TEXT)) {
                    System.out.println("Text " + LOOKUP_TEXT + " found at:");
                    System.out.println("   " + urlString);
                    System.exit(0);
                }
                current = line.indexOf("http:", current);
                while (current > 0) {
                    int endIndex = line.indexOf("\"", current);
                    if (endIndex > 0) { // Ensure that a correct URL is found
                        list.add(line.substring(current, endIndex));
                        current = line.indexOf("http:", endIndex);
                    } else
                        current = -1;
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return list;
    }
}

