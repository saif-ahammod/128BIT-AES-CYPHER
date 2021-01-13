
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCYPHER {
	 
    private static final String characterEncoding       = "UTF-8";
    private static final String cipherTransformation    = "AES/CBC/PKCS5PADDING";
    private static final String aesEncryptionAlgorithem = "AES";
    

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in); // System.in is a standard input stream.

		while (true) {
			System.out.println("Encrypt: A ");
			System.out.println("Decrypt: B ");
			System.out.println("Readme: C ");
			System.out.println("EXIT: E ");
			System.out.print("Choose Your Option: ");
			char option = scanner.next().charAt(0); // reads char.
			System.out.flush();

			if (option == 'A' || option == 'a') {

				encryptprocess();
			} else if (option == 'B' || option == 'b') {

				decryptprocess();

			} else if (option == 'C' || option == 'c') {
				readme();

			} else if (option == 'E' || option == 'e') {
				return;
			} else {
				System.out.println("Wrong Key pressed");
			}
		}
	}

	private static void encryptprocess() {
		// TODO Auto-generated method stub
		System.out.println("Encrypt Plaintext into Cyphertext:");
		System.out.println("Encryption Started....");
		String cyphertext ;

		Path fileName = Path.of("givencleartext.txt");
		// Retrive plaintext from the givencleartext.txt file
		String Stringplaintext = null;
		try {
			Stringplaintext = Files.readString(fileName);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Encrypt cleartexthere
		cyphertext = encrypt(Stringplaintext);
		
		

		// Save cyphertext into the crypto.txt file
		Path crypto = Path.of("crypto.txt");

		try {
			Files.writeString(crypto, cyphertext);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Encryption Completed Successfully....");
	}
	
	//Main E
	private static String encrypt(String plainText) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("ENTER A 16 Digit Key: ");
		String encryptionKey = scanner.nextLine();
		String encryptedText = "";
        try {
            Cipher cipher   = Cipher.getInstance(cipherTransformation);
            byte[] key      = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedText = encoder.encodeToString(cipherText);

        } catch (Exception E) {
             System.err.println("Encrypt Exception : "+E.getMessage());
        }
        return encryptedText;
		
	}

	

	private static void decryptprocess() {
		// TODO Auto-generated method stub
		System.out.println("Decrypt Cyphertext into Plaintext:");
		System.out.println("Decription Started....");
		String cyphertext ;

		Path fileName = Path.of("givencyphertext.txt");
		// Retrive plaintext from the givencleartext.txt file
		String Stringcyphertext = null;
		try {
			Stringcyphertext = Files.readString(fileName);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void readme() {
		// TODO Auto-generated method stub
		System.out.println("Readme File:");
		Path readme = Path.of("readme.txt");
		// Retrive file from the .txt file
		String ReadmeString;
		try {
			ReadmeString = Files.readString(readme);
			System.out.println(ReadmeString);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
