package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AESTest {

    public void printTable(byte[][] table) {
        for (byte[] bytes : table) {
            for (byte aByte : bytes) {
                System.out.print(aByte + " ");
            }
            System.out.println();
        }
    }

    byte[][] data = {{0, 4, 8, 12}, {1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}};
    @Test
    public void shiftRowsTest() {
        AES aes = new AES();
        System.out.println(data.length);
        printTable(data);
        System.out.println();
        printTable(aes.ShiftRows(data));
        assertTrue(true);
    }

    @Test
    public void mixColumns() {
        AES aes = new AES();
//        printTable(data);
//        System.out.println();
//        aes.mixColumns(data[0]);
//        printTable(data);
//        char[] d = {45, 38, 49, 76};
//        char[] d = {1, 1, 1, 1};
        char[] d = {242, 10, 34, 92};
//        char[] d = {198, 198, 198, 198};
        for (char b : d) {
            int b1 = b;
            System.out.print(b1 + " ");
        }
        System.out.println();
        char[] da = aes.mix(d);
        for (char b : da) {
            int b1 = b;
            System.out.print(b1 + " ");
        }
        System.out.println();
        char[] f = aes.cols(da);
        for (char b : f) {
            int b1 = b;
            System.out.print(b1 + " ");
        }
    }

}