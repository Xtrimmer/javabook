package chapter_04;
/**
 * Created by jtrimmer on 1/5/2016.
 */
public class PE_04_25_Generate_vehicle_plate_numbers {
    public static void main(String[] args) {
        String plateNumber = "";
        plateNumber += (char)(int)(Math.random() * 26 + 65);
        plateNumber += (char)(int)(Math.random() * 26 + 65);
        plateNumber += (char)(int)(Math.random() * 26 + 65);
        plateNumber += "-";
        plateNumber += (int)(Math.random() * 10);
        plateNumber += (int)(Math.random() * 10);
        plateNumber += (int)(Math.random() * 10);
        plateNumber += (int)(Math.random() * 10);

        System.out.println("New Plate Number: " + plateNumber);
    }
}
