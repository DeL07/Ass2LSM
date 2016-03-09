
//==============================================================================
// COMP249/4 - Winter 2016
// Assignment #2 - Written by Mylène Haurie & Louis-Simon Carle
// 
// Program prompts user for a filename and checks that there 
// are no existing files with the same name, until a correct 
// filename is entered, then the file is created.
// 
// Program reads input file Publication_Input.txt and stores all
// publications as objects in array[], then checks for duplicate
// publication codes and prompts user to update them. 
//
// Update loops and checks against itself until there are no 
// duplicates left. Finally, the program prints out to screen
// the original file as well as the updated file specified by
// the user
//==============================================================================

import java.io.*;
import java.util.*;

public class PublicationListingProcess1 {

	// Default constructor
	public PublicationListingProcess1() {
	}

	public static Publication[] array;
	public static int arrayPosition = 0;
	public static int numberOfItems;

	// enum types
	enum PublicationTypes {
		PUBLICATIONCODE, PUBLICATIONNAME, PUBLICATIONYEAR, PUBLICATIONAUTHORNAME, PUBLICATIONCOST, PUBLICATIONNBPAGES
	}

	public static void main(String[] args) throws IOException {

		Scanner kb = new Scanner(System.in);

		String outputFileName = null;

		boolean outputFileCheck = false;
		boolean fileExists = false;

		File f = null;
		File originalPublication = new File("PublicationData_Input.txt");

		// Welcome message
		System.out.println("Welcome to Mylène and Louis-Simon's publication inventory program");
		System.out.println(
				"Please enter the name of the output file that will be created to hold the modified/correct inventory");

		// Loop for creating file and filename check
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
					System.out.println("File created: " + outputFileName + ".txt\n");
					outputFileCheck = true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			Scanner fileIn = null;

			try {
				fileIn = new Scanner(new FileReader(originalPublication));
			} catch (FileNotFoundException e) {
				System.out.println("File not found, closing program");
				e.printStackTrace();
				fileIn.close();
				System.exit(0);
			} finally {
				fileIn.close();
			}
		}
		// Check number of records in file
		numberOfItems = numberOfItems(originalPublication);
		if (numberOfItems <= 1) {
			System.out.println("Input file is empty or only has one record");
			System.out.println("Closing file and exiting program");
			System.exit(0);
		} else {
			// Create array based on number of records and invoke the
			// correctListOfItems method to check for publication code copies
			array = new Publication[numberOfItems];
			correctListOfItems(originalPublication, outputFileName);
		}
		// Print original records file to screen
		printFileItems(originalPublication);
		// Print updated records to screen
		printFileItems(f);
	} // END OF MAIN

	// Print items from file method
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

	// Correct Publication Code method
	public static void correctListOfItems(File inFile, String outFile) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(inFile));
		Scanner kb = new Scanner(System.in);

		try {
			String line = "";
			int split = 0;
			// Reads line until reader returns null
			while ((line = br.readLine()) != null) {
				// Takes input as a String and splits it into tokens array via
				// each whitespace
				String[] tokens = line.split(" ");
				// Creates new publication object via tokens array position and
				// parses to correct type
				Publication pub1 = new Publication(Long.parseLong(tokens[0]), tokens[1], Integer.parseInt(tokens[2]),
						tokens[3], Double.parseDouble(tokens[4]), Integer.parseInt(tokens[5]));
				// Adds object to array of publication objects
				array[arrayPosition++] = pub1;
				System.out.println("Added item #" + arrayPosition + ":\t" + array[arrayPosition - 1].toString());
			}
		} catch (IOException e) {
			// e.printStackTrace();
		}
		// Duplicate check - goes through publication objects and checks for
		// duplicates
		for (int i = 0; i < array.length; i++) {
			long tempCheck;
			tempCheck = array[i].getPublication_code();
			for (int j = 0; j < array.length; j++) {
				// Verifies object is not detecting itself and displays record
				// which contains duplicate
				if (array[j].getPublication_code() == tempCheck & j != i) {
					System.out.println("\nError: Duplicate Publication Code found!");
					System.out.println(
							"\tDuplicate:" + "\t" + array[j].toString() + "\n\tMatches:" + "\t" + array[i].toString());
					try {
						System.out.print("Please enter a new Publication Code for the duplicate: ");
						boolean loop = false;
						while (loop == false) {

							long newCode = kb.nextLong();
							boolean newCodeCheck = duplicateCode(newCode);
							if (newCodeCheck == false) {
								throw new CopyCodeException("Error");
							} else {
								array[j].setPublication_code(newCode);
								System.out.println("New Publication Code set");
								loop = true;
							}

						}
					} catch (CopyCodeException e) {
						e.printStackTrace();
					}
				}
			}
		}

		// Output updated results to file passed in method
		PrintWriter output = new PrintWriter(outFile);

		for (int i = 0; i < array.length; i++)
			output.println(array[i]);
		// Close files
		br.close();
		output.close();
	}

	// Counts number of records in file
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

	public static boolean duplicateCode(long userEntry) {
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i].getPublication_code() == userEntry) {
				count++;
			}
		}
		
		if (count >= 1) {
			return false;
		} else {
			return true;
		}
	}

}
