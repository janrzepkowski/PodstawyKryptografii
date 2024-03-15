package org.example;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class AES {

    public static void main(String[] args) {
        try {
            AES aes = new AES();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Podaj długość klucza w bitach (np. 128, 192, 256): ");
            int keySize = scanner.nextInt();
            String aesKey = aes.generateKey(keySize);

            // Wyświetlenie wygenerowanego klucza w formacie heksadecymalnym
            System.out.println("Wygenerowany klucz AES (w formacie heksadecymalnym):");
            System.out.println(aesKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // Funkcja do generowania klucza AES w formacie heksadecymalnym
    public String generateKey(int size) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(size);
        SecretKey secretKey = keyGen.generateKey();
        byte[] keyBytes = secretKey.getEncoded();

        // Konwersja bajtów klucza na format heksadecymalny
        StringBuilder sb = new StringBuilder();
        for (byte b : keyBytes) {
            sb.append(String.format("%02X", b));
        }

        return sb.toString();
    }
}