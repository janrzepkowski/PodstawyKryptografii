package org.example;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

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

    AES aes = new AES();
//    byte[][] data = {{0, 4, 8, 12}, {1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}};
    byte[][] data = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11,} ,{12, 13, 14, 15}};
    @Test
    public void shiftRowsTest() {
        System.out.println(data.length);
        printTable(data);
        System.out.println();
        aes.shiftRows(data);
        printTable(data);
        assertTrue(true);
    }

    @Test
    public void mixColumns() {
//        printTable(data);
//        System.out.println();
//        aes.mixColumns(data[0]);
//        printTable(data);
//        char[] d = {45, 38, 49, 76};
//        byte[] d = {1, 1, 1, 1};
        byte[] d = {(byte) 242, 10, 34, 92};
//        char[] d = {198, 198, 198, 198};
        for (byte b : d) {
            int b1 = b;
            System.out.print(b1 + " ");
        }
        System.out.println();
        byte[] da = aes.mix(d);
        for (byte b : da) {
            int b1 = b;
            System.out.print(b1 + " ");
        }
        System.out.println();
        byte[] f = aes.cols(da);
        for (byte b : f) {
            int b1 = b;
            System.out.print(b1 + " ");
        }
    }

    @Test
    public void subBytesTest() {
        String words = "dum spiro, spero – poki oddycham, nie trace nadziei."; // jezeli wiadomosc jest zbyt krotka to chyba (int)Math.sqrt(state1.length) = 0 i out of bounds error daje
        byte[] state1 = words.getBytes();
        byte[][] state2 = aes.array1Dto2D(state1, (int)Math.sqrt(state1.length), (int)Math.sqrt(state1.length));
        byte[][] state3 = aes.subBytes1(state2);
        assertEquals(67, state3[0][0]);
    }

    @Test
    void addRoundKeyTest() throws NoSuchAlgorithmException {
        AES aes = new AES();
        byte[] key = aes.generateKey(128);
        byte[][] key2d = aes.array1Dto2D(key, (int)Math.sqrt(key.length), (int)Math.sqrt(key.length));
        printTable(key2d);

        String words = "dum spiro, spero – poki oddycham, nie trace nadziei.";
        byte[] state1 = words.getBytes();
        byte[][] state2 = aes.array1Dto2D(state1, (int)Math.sqrt(state1.length), (int)Math.sqrt(state1.length));
        printTable(state2);
        byte[][] state3 = aes.addRoundKey1(state2, key2d, 0);
        printTable(state3);
    }

    @Test
    void subBytes() {
        printTable(data);
        aes.subBytes(data);
        printTable(data);
        aes.invSubBytes(data);
        printTable(data);
    }

    @Test
    void rCon() {
        int[] values = {1, 2, 4, 8, 16, 32, 64, -128, 27, 54};
        int[] xoredValues = {3, 1, 5, 13, 29, 61, 125, -3, -26, -48};
        byte val = 0;
        for (byte i = 0; i < 10; i++) {
            val = aes.rCon(data[3], val);
            assertEquals(values[i], val);
            assertEquals(data[3][0], xoredValues[i]);
        }
        for (byte i = 0; i < 10; i++) {
            assertEquals(values[9 - i], val);
            assertEquals(xoredValues[9 - i], data[3][0]);
            val = aes.invRCon(data[3], val);
        }
    }

    @Test
    void addKeyRound() {
        byte[][] key = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        printTable(key);
        byte round = 1;
        round = aes.keySchedule(key, round);
        printTable(key);
        aes.invAddRoundKey(key, round);
        printTable(key);
    }
}