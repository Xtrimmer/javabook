package chapter_17;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * (BitOutputStream) Implement a class named BitOutputStream, as shown
 * in Figure 17.22, for writing bits to an output stream. The writeBit(char bit)
 * method stores the bit in a byte variable. When you create a BitOutputStream,
 * the byte is empty. After invoking writeBit('1'), the byte becomes 00000001.
 * After invoking writeBit("0101"), the byte becomes 00010101. The first
 * three bits are not filled yet. When a byte is full, it is sent to the output stream. Now
 * the byte is reset to empty. You must close the stream by invoking the close()
 * method. If the byte is neither empty nor full, the close() method first fills the
 * zeros to make a full 8 bits in the byte, and then outputs the byte and closes the
 * stream. For a hint, see Programming Exercise 5.44. Write a test program that
 * sends the bits 010000100100001001101 to the file named Exercise17_17.dat.
 */
public class PE_17_17_BitOutputStream {
    public static void main(String[] args) {
        File file = new File("resources/data/Exercise17_17.dat");
        try (BitOutputStream outputStream = new BitOutputStream(file)) {
            outputStream.writeBit("010000100100001001101");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class BitOutputStream implements AutoCloseable {
        private int bitCount = 0;
        private int byteValue = 0;
        private FileOutputStream outputStream;

        public BitOutputStream(File file) throws FileNotFoundException {
            outputStream = new FileOutputStream(file);
        }

        @Override
        public void close() throws IOException {
            if (bitCount > 0) {
                byteValue = byteValue << (8 - bitCount);
                outputStream.write(byteValue);
            }
            outputStream.close();
        }

        public void writeBit(String bit) throws IOException {
            for (int i = 0; i < bit.length(); i++) {
                writeBit(bit.charAt(i));
            }
        }

        public void writeBit(char bit) throws IOException {
            if (!(bit == '0' || bit == '1')) throw new IllegalArgumentException("only 0 or 1 allowed");
            addBitToByte(bit - '0');
        }

        private void addBitToByte(int i) throws IOException {
            bitCount++;
            byteValue = (byteValue << 1);
            byteValue += i;
            if (bitCount == 8) {
                bitCount = 0;
                outputStream.write(byteValue);
                byteValue = 0;
            }
        }
    }
}
