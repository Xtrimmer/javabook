package chapter_04;

/**
 * (Geography: estimate areas) Find the GPS locations for Atlanta, Georgia;
 * Orlando, Florida; Savannah, Georgia; and Charlotte, North Carolina from
 * www.gps-data-team.com/map/ and compute the estimated area enclosed by these
 * four cities. (Hint: Use the formula in Programming Exercise 4.2 to compute the
 * distance between two cities. Divide the polygon into two triangles and use the
 * formula in Programming Exercise 2.19 to compute the area of a triangle.)
 */
public class PE_04_03_Geography_estimate_areas {
    public static void main(String[] args) {
        double xAG = Math.toRadians(33.7489954);
        double yAG = Math.toRadians(-84.3879824);
        double xOF = Math.toRadians(28.5383355);
        double yOF = Math.toRadians(-81.37923649999999);
        double xSG = Math.toRadians(32.0835407);
        double ySG = Math.toRadians(-81.09983419999998);
        double xCNC = Math.toRadians(35.2270869);
        double yCNC = Math.toRadians(-80.84312669999997);

        double radius = 6371.01;
        double AG_OF = radius * Math.acos(Math.sin(xAG) * Math.sin(xOF) + Math.cos(xAG) * Math.cos(xOF) * Math.cos(yAG - yOF));
        double SG_OF = radius * Math.acos(Math.sin(xSG) * Math.sin(xOF) + Math.cos(xSG) * Math.cos(xOF) * Math.cos(ySG - yOF));
        double SG_CNC = radius * Math.acos(Math.sin(xSG) * Math.sin(xCNC) + Math.cos(xSG) * Math.cos(xCNC) * Math.cos(ySG - yCNC));
        double AG_CNC = radius * Math.acos(Math.sin(xAG) * Math.sin(xCNC) + Math.cos(xAG) * Math.cos(xCNC) * Math.cos(yAG - yCNC));
        double CNC_OF = radius * Math.acos(Math.sin(xCNC) * Math.sin(xOF) + Math.cos(xCNC) * Math.cos(xOF) * Math.cos(yCNC - yOF));

        System.out.printf("AG_OF  = %10.2f km\n", AG_OF);
        System.out.printf("SG_OF  = %10.2f km\n", SG_OF);
        System.out.printf("SG_CNC = %10.2f km\n", SG_CNC);
        System.out.printf("AG_CNC = %10.2f km\n", AG_CNC);
        System.out.printf("CNC_OF = %10.2f km\n", CNC_OF);
        System.out.println();

        double s = (AG_CNC + CNC_OF + AG_OF) / 2;
        double area = Math.sqrt(s * (s - AG_CNC) * (s - CNC_OF) * (s - AG_OF));
        System.out.printf("Area1 is %10.2f km²", area);
        System.out.println();
        s = (SG_CNC + CNC_OF + SG_OF) / 2;
        System.out.printf("Area2 is %10.2f km²", Math.sqrt(s * (s - SG_CNC) * (s - CNC_OF) * (s - SG_OF)));
        System.out.println();
        area += Math.sqrt(s * (s - SG_CNC) * (s - CNC_OF) * (s - SG_OF));

        System.out.printf("Total is %10.2f km²", area);
    }
}
