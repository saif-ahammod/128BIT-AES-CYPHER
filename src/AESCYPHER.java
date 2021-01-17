
import java.io.File;
import java.io.FilenameFilter;
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

	private static final String characterEncoding = "UTF-8";
	private static final String cipherTransformation = "AES/CBC/PKCS5PADDING";
	private static final String aesEncryptionAlgorithem = "AES";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); // System.in is a standard input stream.
		System.out.println("ENTER A 16 Digit Key: ");
		String encryptionKey = scanner.nextLine();
		System.out.println("Process Started.... ");
		encryptprocess(encryptionKey);
		decryptprocess(encryptionKey);
		System.out.println("Process Finished.... ");
	}

	private static void encryptprocess(String encryptionKey) {
		// TODO Auto-generated method stub
		System.out.println("Encryption process Started.... ");
		String cyphertext;
		System.out.println("Locating File...");
		String pathofinput = FileLocator();//getting the file name
		if(pathofinput==null) {

			System.out.println("No file found");
			return;
		}
		else {
			System.out.println("File Found");
		}

		Path fileName = Path.of(pathofinput);
		
		// Retrive plaintext from the java AES_Cipher filename.txt file
		String Stringplaintext = null;
		try {
			Stringplaintext = Files.readString(fileName);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Encrypt java AES_Cipher filename.txt
		cyphertext = encrypt(Stringplaintext, encryptionKey);

		// Save cyphertext into the crypto.txt file
		Path crypto = Path.of("crypto.txt");

		try {
			Files.writeString(crypto, cyphertext);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Encryption process Finished.... ");
	}

	// Main E
	private static String encrypt(String plainText, String encryptionKey) {
		Scanner scanner = new Scanner(System.in);

		String encryptedText = "";
		try {
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			byte[] key = encryptionKey.getBytes(characterEncoding);
			SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
			IvParameterSpec ivparameterspec = new IvParameterSpec(key);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
			byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
			Base64.Encoder encoder = Base64.getEncoder();
			encryptedText = encoder.encodeToString(cipherText);

		} catch (Exception E) {
			System.err.println("Encrypt Exception : " + E.getMessage());
		}
		return encryptedText;

	}

	private static void decryptprocess(String encryptionKey) {
		
		String pathofinput = FileLocator();//getting the file name
		if(pathofinput==null) {
			return;
		}
		// TODO Auto-generated method stub
		System.out.println("Decryption process Started.... ");
		String cyphertext;
		Path fileName = Path.of("crypto.txt");
		// Retrive plaintext from the givencleartext.txt file
		String Stringcyphertext = null;
		try {
			Stringcyphertext = Files.readString(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cyphertext = decrypt(Stringcyphertext, encryptionKey);
		// Save cleartext into the cleartext.txt file
				Path cleartext = Path.of("cleartext.txt");

				try {
					Files.writeString(cleartext, cyphertext);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Decryption process Finished.... ");
	}

	private static String decrypt(String stringcyphertext, String encryptionKey) {
		// TODO Auto-generated method stub
		
		String decryptedText = "";
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(stringcyphertext.getBytes("UTF8"));
            decryptedText = new String(cipher.doFinal(cipherText), "UTF-8");

        } catch (Exception E) {
            System.err.println("decrypt Exception : "+E.getMessage());
        }
        return decryptedText;
	}

	private static String FileLocator() {
		// TODO Auto-generated method stub
		
		File dir = new File(".");
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.startsWith("java AES_Cipher");
			}
		};
		String[] children = dir.list(filter);
		if (children == null) {
			System.out.println("No file found.");
			return null;
		} else {
			String filename = children[0];
			return filename;
		}
	}
}
