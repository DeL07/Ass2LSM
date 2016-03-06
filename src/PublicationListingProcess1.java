//================================================================
// COMP249/4 - Winter 2016
// Assignment #2 - Written by Mylène Haurie & Louis-Simon Carle
// 
//
//
//
//
//
//
//================================================================

import java.nio.*;
import java.io.*;
import java.util.*;

public class PublicationListingProcess1 extends Publication {
	
	// added constructor because class extends publication, will get error if constructor isn't there - M
	
	public PublicationListingProcess1(long code, String name, int year, String author, double cost, int pages){
		super(code, name, year, author, cost, pages);
	}
	
	//private static PublicationArray[] = new Publication[];
	enum PublicationTypes {
		PUBLICATIONCODE,
		PUBLICATIONNAME,
		PUBLICATIONYEAR,
		PUBLICATIONAUTHORNAME,
		PUBLICATIONCOST,
		PUBLICATIONNBPAGES
	}
	
	int n = 0;
	Publication[] PublicationArray = new Publication[n];

	public static void main(String[] args) throws IOException {
		
		Scanner kb = new Scanner(System.in);

		
		// Ints
		
		
		// Strings
		String outputFileName = null;
		
		// Booleans
		boolean outputFileCheck = false;
		boolean fileExists = false;
		
		File f = null;
		File originalPublication = new File("PublicationData_Input");
		PrintWriter pw = null;
		FileOutputStream outStream = null;
		FileInputStream inStream = null;
		
		System.out.println("Welcome to Mylène and Louis-Simon's publication inventory program");
		System.out.println("Please enter the name of the output file that will be created to hold the modified/correct inventory");
	
		while (!outputFileCheck) {
			
			try {
				System.out.print("Enter the output filename: ");
				outputFileName = kb.nextLine();
				f = new File(outputFileName);
				fileExists = f.exists();
				
				if (fileExists) {
					System.out.println("Error: There is an existing file called: " + outputFileName + ".");
					System.out.println("That file already has a size of " + f.length() + " bytes.");
				} else {
					f.createNewFile();
					System.out.println("File created: " + outputFileName + ".txt");
					outputFileCheck = true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Scanner fileIn = null;
			
			try {
				fileIn = new Scanner(new FileReader(originalPublication));
//				printFileItems(originalPublication);
			} catch (FileNotFoundException e) {
				System.out.println("File not found, closing program");
				e.printStackTrace();
				
				System.exit(0);
			} finally {
				fileIn.close();
			}

		}
	}
	
//	public static void printFileItems(File inputFile) throws IOException {
//		Scanner input = new Scanner(new BufferedReader(inputFile));
//		int x = 0;
//		while ((x = input.read()) != -1) {
//			System.out.print((((BufferedReader) input)).readLine());
//		}
//	}
	
	public static void correctListOfItems(File inFile, File outFile) throws FileNotFoundException, IOException {
		Reader input = new FileReader(inFile);
		PrintWriter output = new PrintWriter(outFile);	
	}
	
	public static int numberOfItems(String Filename) throws IOException {
		LineNumberReader in = new LineNumberReader(new FileReader(Filename));
		int count = 0;
		String Line = "";
		while ((Line = in.readLine()) != null) {
			
		}
		count = in.getLineNumber();
		in.close();
		return count;
	}
	
	
}
	
	

