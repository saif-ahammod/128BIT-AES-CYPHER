
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
public class AESCYPHER {

	public static void main(String[] args) {
		
		Scanner scanner= new Scanner(System.in); //System.in is a standard input stream.
		
		
		System.out.println("Encrypt: A ");
		System.out.println("Decrypt: B ");
		System.out.println("Readme: C ");
		System.out.print("Choose Your Option: ");
		char option= scanner.next().charAt(0);  //reads char.	
		System.out.flush();

		if(option == 'A' || option == 'a') {
			
			encrypt();
		}
		else if(option == 'B' || option == 'b') {
			
			decrypt();
		}
		else if(option == 'C' || option == 'c') {
			readme();
		}
		else {
			System.out.println("Wrong Key pressed");
		}
		
		
		// Save file into the .txt file
		Path fileName = Path.of("demo.txt");
        String content  = "hello world !!";
        try {
			Files.writeString(fileName, content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
     // Retrive file from the .txt file
        String actual;
		try {
			actual = Files.readString(fileName);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	
	private static void encrypt() {
		// TODO Auto-generated method stub
		System.out.println("Encrypt A Cyphertext into Plaintext");
	}
	private static void decrypt() {
		// TODO Auto-generated method stub
		System.out.println("Decrypt A Plaintext into Cyphertext");
	}

	
	private static void readme() {
		// TODO Auto-generated method stub
		System.out.println("Readme File:");
	}
	

}
