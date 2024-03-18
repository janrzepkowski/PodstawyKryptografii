package org.example;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class AES {

    public static void main(String[] args) throws NoSuchAlgorithmException {

            AES aes = new AES();
            Scanner scanner = new Scanner(System.in);

            System.out.print("Podaj długość klucza w bitach (np. 128, 192, 256): ");
            int keySize = scanner.nextInt();

            byte[] aesKeyBytes = aes.generateKey(keySize);
            String aesKeyHex = aes.byteArraytoString(aesKeyBytes);

            System.out.println("Wygenerowany klucz AES (w formacie heksadecymalnym):");
            System.out.println(aesKeyHex);
    }

    public byte[] generateKey(int size) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(size);
        SecretKey secretKey = keyGen.generateKey();
        return secretKey.getEncoded();
    }

    private String byteArraytoString(byte[] keyBytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : keyBytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    public int[][] ShiftRows(int[][] table) {
        int i = 1;
        int temp = table[i][0];
        table[i][0] = table[i][1];
        table[i][1] = table[i][2];
        table[i][2] = table[i][3];
        table[i][3] = temp;
        i++;

        temp = table[i][0];
        table[i][0] = table[i][2];
        table[i][2] = temp;
        temp = table[i][3];
        table[i][3] = table[i][1];
        table[i][1] = temp;
        i++;

        temp = table[i][0];
        table[i][0] = table[i][3];
        table[i][3] = table[i][2];
        table[i][2] = table[i][1];
        table[i][1] = temp;

        return table;
    }
}