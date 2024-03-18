package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AESTest {

    public void printTable(int[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void shiftRowsTest() {
        AES aes = new AES();
        int[][] data = {{0, 4, 8, 12}, {1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}};
        System.out.println(data.length);
        printTable(data);
        System.out.println();
        printTable(aes.ShiftRows(data));
        assertTrue(true);
    }

}