package utility;

public class Location {
    public int row;
    public int column;
    public double maxValue;

    public Location(int row, int column, double maxValue) {
        this.row = row;
        this.column = column;
        this.maxValue = maxValue;
    }

    public static Location locateLargest(double[][] a) {
        Location location = new Location(0, 0, a[0][0]);
        for (int r = 0; r < a.length; r++) {
            for (int c = 0; c < a[r].length; c++) {
                if (a[r][c] > location.maxValue)
                    location = new Location(r, c, a[r][c]);
            }
        }
        return location;
    }

    @Override
    public String toString() {
        String formattedMaxValue;
        if (maxValue % 1.0 != 0)
            formattedMaxValue = String.format("%s", maxValue);
        else
            formattedMaxValue = String.format("%.0f", maxValue);
        return String.format("The location of the largest element is %s at (%d, %d)",
                formattedMaxValue, row, column);
    }
}
