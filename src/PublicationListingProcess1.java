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
import java.util.Scanner;

public class PublicationListingProcess1 extends Publication {
	
	//private static PublicationArray[] = new Publication[];
	enum PublicationTypes {
		PUBLICATIONCODE,
		PUBLICATIONNAME,
		PUBLICATIONYEAR,
		PUBLICATIONAUTHORNAME,
		PUBLICATIONCOST,
		PUBLICATIONNBPAGES
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		// Ints
		
		
		// Strings
		String outputFileName;
		
		// Booleans
		boolean outputFileCheck = false;
		
		Formatter x;
		
		System.out.println("Welcome to Mylène and Louis-Simon's publication inventory program");
		System.out.println("Please enter the name of the output file that will be created to hold the modified/correct inventory");
		System.out.print("Output file name: ");
		
		outputFileName = in.nextLine();
		
			try{
				x = new Formatter(outputFileName);
				System.out.println("File created: " + outputFileName + ".txt");
			} catch(IOException e) {
				System.out.println("you fucked nigg");
			}
			
			//this all makes no sense
			
			
			
		
		
	}
	
	/*public void correctListOfItems(input file stream, output file stream) {
		
	}
	
	public void printFileItems(input file stream name) {
		
	}*/
}
