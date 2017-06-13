package chapter_21;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * (Web crawler) Rewrite Listing 12.18, WebCrawler.java, to improve the performance
 * by using appropriate new data structures for listOfPendingURLs and
 * listofTraversedURLs.
 */
public class PE_21_14_Web_crawler {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a URL: ");
        String url = input.nextLine();
        crawler(url); // Traverse the Web from the a starting url
    }

    public static void crawler(String startingURL) {
        LinkedList<String> listOfPendingURLs = new LinkedList<>();
        HashSet<String> listOfTraversedURLs = new HashSet<>();

        listOfPendingURLs.offer(startingURL);
        while (!listOfPendingURLs.isEmpty() &&
                listOfTraversedURLs.size() <= 100) {
            String urlString = listOfPendingURLs.poll();
            listOfTraversedURLs.add(urlString);
            System.out.println("Crawl " + urlString);

            for (String s : getSubURLs(urlString)) {
                if (!listOfTraversedURLs.contains(s) &&
                        !listOfPendingURLs.contains(s))
                    listOfPendingURLs.offer(s);
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
