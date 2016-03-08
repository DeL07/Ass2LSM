
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
	public static int numberOfItems;

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
					System.out.println("File created: " + outputFileName + ".txt\n");
					outputFileCheck = true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			Scanner fileIn = null;

			try {
				fileIn = new Scanner(new FileReader(originalPublication));
				//printFileItems(originalPublication);
			} catch (FileNotFoundException e) {
				System.out.println("File not found, closing program");
				e.printStackTrace();
				System.exit(0);
			} finally {
				fileIn.close();
			}

		}

		// Check number of items
		numberOfItems = numberOfItems(originalPublication);
		if (numberOfItems <= 1) {
			System.out.println("Input file is empty or only has one record");
			System.out.println("Closing file and exiting program");
			System.exit(0);
		} else {
			array = new Publication[numberOfItems];
			correctListOfItems(originalPublication, outputFileName);
		}

		printFileItems(originalPublication);
		System.out.println();
		printFileItems(f);
	}

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
			while ((line = br.readLine()) != null) {				
									
					String[] tokens = line.split(" ");
					Publication pub1 = new Publication(Long.parseLong(tokens[0]), tokens[1], Integer.parseInt(tokens[2]),
						tokens[3], Double.parseDouble(tokens[4]), Integer.parseInt(tokens[5]));
					array[arrayPosition++] = pub1;
					System.out.println("Added item #" + arrayPosition + ":\t" + array[arrayPosition-1].toString());
			}
		} catch (IOException e) {
			//e.printStackTrace();
		}
			
		for (int i = 0; i < array.length; i++) {
			long tempCheck;
			tempCheck = array[i].getPublication_code();
			for (int j = 0; j < array.length; j++) {
				if (array[j].getPublication_code() == tempCheck & j != i) {
					System.out.println("\nError: Duplicate Publication Code found!");
					System.out.println("\tDuplicate:"+ "\t" + array[j].toString() + "\n\tMatches:" + "\t" + array[i].toString());
					System.out.print("Please enter a new Publication Code for the duplicate: ");
					boolean newCodeCheck = true;
					while (newCodeCheck) {
						long newCode = kb.nextLong();
								try {
									
									for (int l = 0; l < array.length; l++) {
										if (array[l].getPublication_code() == newCode) {
											throw new CopyCodeException();
										} else {
											array[j].setPublication_code(newCode);
											newCodeCheck = false;
										}
									}
								} catch (CopyCodeException e) {
									e.getMessage();
									//e.printStackTrace();
								}
							
						}
							
					}
					
				}
			}
		
		PrintWriter output = new PrintWriter(outFile);
		
		for (int i = 0; i < array.length; i++)
			output.println(array[i]);
		
		br.close();
		output.close();
		
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
