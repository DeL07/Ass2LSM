
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

public class PublicationListingProcess1 {

	// added constructor because class extends publication, will get error if
	// constructor isn't there - M
	
	public PublicationListingProcess1() {
		
	}
	
	public static Publication[] array;
	public static int arrayPosition = 0;
	
	// private static PublicationArray[] = new Publication[];
	enum PublicationTypes {
		PUBLICATIONCODE, PUBLICATIONNAME, PUBLICATIONYEAR, PUBLICATIONAUTHORNAME, PUBLICATIONCOST, PUBLICATIONNBPAGES
	}

	public static void main(String[] args) throws IOException {

		Scanner kb = new Scanner(System.in);

		// Ints

		// Strings
		String outputFileName = null;

		// Booleans
		boolean outputFileCheck = false;
		boolean fileExists = false;

		File f = null;
		File originalPublication = new File("PublicationData_Input.txt");
		PrintWriter pw = null;
		FileOutputStream outStream = null;
		FileInputStream inStream = null;

		System.out.println("Welcome to Mylène and Louis-Simon's publication inventory program");
		System.out.println(
				"Please enter the name of the output file that will be created to hold the modified/correct inventory");

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
				printFileItems(originalPublication);
			} catch (FileNotFoundException e) {
				System.out.println("File not found, closing program");
				e.printStackTrace();

				System.exit(0);
			} finally {
				fileIn.close();
			}

		}

		// Check number of items
		int items = numberOfItems(originalPublication);

		if (items == 0 || items == 1) {
			System.out.println("Input file is empty or only has one record");
			System.out.println("Closing file and exiting program");
			System.exit(0);
		} else {
			array = new Publication[items];
			correctListOfItems(originalPublication, outputFileName);
		}

	}

	public static void printFileItems(File aFile) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(aFile));
			String line;
			System.out.println();
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void correctListOfItems(File inFile, String outFile) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(inFile));
		PrintWriter output = new PrintWriter(outFile);
		
		try {
			String line = "";
		
			while ((line = br.readLine()) != null) {

				// PublicationListingProcess1(long code, String name, int year,
				// String author, double cost, int pages){
				// super(code, name, year, author, cost, pages);

				String[] tokens = line.split(" ");

				Publication pub1 = new Publication(Long.parseLong(tokens[0]), tokens[1],
						Integer.parseInt(tokens[2]), tokens[3], Double.parseDouble(tokens[4]),
						Integer.parseInt(tokens[5]));
				
				array[arrayPosition++] = pub1;
	
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

	public static int numberOfItems(File Filename) throws IOException {
		LineNumberReader reader = new LineNumberReader(new FileReader(Filename));
		int cnt = 0;
		String lineRead = "";
		while ((lineRead = reader.readLine()) != null) {
		}

		cnt = reader.getLineNumber();
		reader.close();
		return cnt;

	}

}
