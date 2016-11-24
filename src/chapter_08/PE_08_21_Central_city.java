package chapter_08;

import java.util.Scanner;

/**
 * (Central city) Given a set of cities, the central city is the city that has the shortest
 * total distance to all other cities. Write a program that prompts the user to enter
 * the number of the cities and the locations of the cities (coordinates), and finds
 * the central city and its total distance to all other cities.
 *
 *      Enter the number of cities: 5 (enter)
 *      Enter the coordinates of the cities:
 *      2.5 5 5.1 3 1 9 5.4 54 5.5 2.1 (enter)
 *      The central city is at (2.5, 5.0)
 *      The total distance to all other cities is 60.81
 */
public class PE_08_21_Central_city {
    public static void main(String[] args) {
        int numberOfCities = getNumberOfCities();
        double[][] cityCoordinates = getCityCoordinates(numberOfCities);
        double[] centralCityInfo = getCentralCityInfo(cityCoordinates);
        printCityInfo(centralCityInfo);
    }

    private static void printCityInfo(double[] centralCityInfo) {
        final int X_COORDINATE = 0;
        final int Y_COORDINATE = 1;
        final int DISTANCE = 2;
        System.out.println("The central city is at (" + centralCityInfo[X_COORDINATE] + ", " +
                centralCityInfo[Y_COORDINATE] + ")");
        System.out.printf("The total distance to all other cities is %.2f", centralCityInfo[DISTANCE]);
    }

    private static double[] getCentralCityInfo(double[][] cityCoordinates) {
        double totalDistanceBetweenCities = 0;
        final int X_COORDINATE = 0;
        final int Y_COORDINATE = 1;
        final int DISTANCE = 2;
        double[] centralCityInfo =  new double[3];
        for (double[] cityCoordinate : cityCoordinates) {
            totalDistanceBetweenCities += Math.sqrt(Math.pow(cityCoordinate[X_COORDINATE] - cityCoordinates[0][X_COORDINATE], 2) +
                    Math.pow(cityCoordinate[Y_COORDINATE] - cityCoordinates[0][Y_COORDINATE], 2));
        }
        centralCityInfo[X_COORDINATE] = cityCoordinates[0][X_COORDINATE];
        centralCityInfo[Y_COORDINATE] = cityCoordinates[0][Y_COORDINATE];
        centralCityInfo[DISTANCE] = totalDistanceBetweenCities;
        for (int i = 1; i < cityCoordinates.length; i++) {
            totalDistanceBetweenCities = 0;
            for (double[] cityCoordinate : cityCoordinates) {
                totalDistanceBetweenCities += Math.sqrt(Math.pow(cityCoordinate[X_COORDINATE] - cityCoordinates[i][X_COORDINATE], 2) +
                        Math.pow(cityCoordinate[Y_COORDINATE] - cityCoordinates[i][Y_COORDINATE], 2));
            }
            if (centralCityInfo[DISTANCE] > totalDistanceBetweenCities) {
                centralCityInfo[X_COORDINATE] = cityCoordinates[i][X_COORDINATE];
                centralCityInfo[Y_COORDINATE] = cityCoordinates[i][Y_COORDINATE];
                centralCityInfo[DISTANCE] = totalDistanceBetweenCities;
            }
        }
        return  centralCityInfo;
    }

    private static double[][] getCityCoordinates(int numberOfCities) {
        final int X_COORDINATE = 0;
        final int Y_COORDINATE = 1;
        double[][] cityCoordinates = new double[numberOfCities][2];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the coordinates of the cities:");
        for (int i = 0; i < numberOfCities; i++) {
            cityCoordinates[i][X_COORDINATE] = scanner.nextDouble();
            cityCoordinates[i][Y_COORDINATE] = scanner.nextDouble();
        }
        return cityCoordinates;
    }

    private static int getNumberOfCities() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of cities: ");
        return scanner.nextInt();
    }
}
