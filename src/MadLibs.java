package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class reads in a selected MadLibs story, prompts the user for the 
 * various parameters and then prints out the resulting story.
 * 
 * @author Jim Williams
 * inspired by Sean McClanahan's MadLibs project
 * http://weekendstubble.blogspot.com/2007/02/edwardian-mad-libs.html
 */
public class MadLibs {

	
	/**
	 * Reads the contents of the specified file and returns as one
	 * long string.
	 * @param fileName  Name of the file to read from.
	 * @return A string containing the contents of the file.
	 * @throws IOException
	 */
	static String readFile( String fileName) throws IOException {
	    InputStream ins = new FileInputStream(fileName);
        Scanner sc = new Scanner(ins);
        //read line by line
        



        // Open file

        // output values read from file --> string (result)

        // done with file, so close it
        
        
        
        String result = "";
        


        return result; 
	}
	
	/**
	 * Writes the contents to the specified file.
	 * @param fileName Name of the file to create or overwrite.
	 * @param contents The text to put in the file.
	 * @throws IOException
	 */
	static void writeFile( String fileName, String contents) throws IOException {
	    //TODO
	}
	
	/**
	 * Finds each parameter (e.g., [Noun]) in the madlibStory,
	 * and prompts the user for the value.  Returns a string
	 * containing the complete story with the values. 
	 * @param input The scanner for getting input from the user.
	 * @param madlibStory A story containing parameters (e.g., [Adjective]).
	 * @return The madLibStory with parameters replaced with values entered by a 
	 * user.
	 */
    static String promptForReplacements(Scanner input, String madlibStory) {
        String completedStory = "";
        
        int fragmentBegin = 0;
        int fragmentEnd;
        int storyParameterBegin;
        int storyParameterEnd;
        
        //look for first story parameter. 
        fragmentEnd = madlibStory.indexOf( '[', fragmentBegin);
        while ( fragmentEnd > fragmentBegin){
            
            //add the fragment of the story text to the completed story.
            completedStory += madlibStory.substring( fragmentBegin, fragmentEnd);
            
            //beginning of parameter is after the [          
            storyParameterBegin = fragmentEnd + 1;  
            
            //find the end of the parameter text
            storyParameterEnd = madlibStory.indexOf( ']', storyParameterBegin);
            
            //if ending ] is not found then an error.
            if ( storyParameterEnd <= storyParameterBegin) {
                System.err.println("Error, unmatched [ at index " + storyParameterBegin);
                fragmentBegin = storyParameterBegin;
                break;
            }
            
            //retrieve the parameter from between [ and ]
            String parameter = madlibStory.substring( storyParameterBegin,  storyParameterEnd);
            
            //display the parameter to the user, to get the user's replacement (argument).
            System.out.print( parameter + ": ");
            String argument = input.nextLine();

            //append the argument to the completed story text 
            completedStory += argument;
            
            //find the text fragment between the parameters
            fragmentBegin = storyParameterEnd + 1;
            fragmentEnd = madlibStory.indexOf( '[', fragmentBegin);
        }
        
        //get last fragment of story
        completedStory += madlibStory.substring(fragmentBegin);
        return completedStory;
    }
	
	/**
	 * The main method that prompts the user for the name of 
	 * a Madlib's file, reads the file, prompts for parameter 
	 * values and replaces the parameters with the values.
	 * @param args unused
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner( System.in);
		System.out.print("Enter the name of a Madlibs file: " );
		String filename = input.nextLine();
		
		String contents = readFile( filename);
		if ( contents != null) {
			String completedStory = promptForReplacements( input, contents);
			System.out.println("Press Enter to see completed story.");
			input.nextLine();
			System.out.println( completedStory);
			System.out.println();
			System.out.print("Enter filename to save story (Just Enter to not save): ");
			String saveFilename = input.nextLine().trim();
			if ( saveFilename.length() > 0) {
				writeFile( saveFilename, completedStory);
			}
		}
		input.close();
	}
}