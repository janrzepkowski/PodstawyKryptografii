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

    @Test
    public void subBytesTest() {
        AES aes = new AES();
        String words = "dum spiro, spero – poki oddycham, nie trace nadziei."; // jezeli wiadomosc jest zbyt krotka to chyba (int)Math.sqrt(state1.length) = 0 i out of bounds error daje
        byte[] state1 = words.getBytes();
        byte[][] state2 = aes.array1Dto2D(state1, (int)Math.sqrt(state1.length), (int)Math.sqrt(state1.length));
        byte[][] state3 = aes.subBytes(state2);
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
        byte[][] state3 = aes.addRoundKey(state2, key2d, 0);
        printTable(state3);
    }
}