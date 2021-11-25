import java.util.Scanner;

public class ShortMadLibs {

    static String promptForReplacements(Scanner input, String madlibStory) {
        String completedStory = "";
        
        int fragmentBegin = 0;
        int fragmentEnd;
        int storyParameterBegin;
        int storyParameterEnd;
        
        //TODO add comment
        fragmentEnd = madlibStory.indexOf( '[', fragmentBegin);
        while ( fragmentEnd > fragmentBegin){
            
            //TODO add comment
            completedStory += madlibStory.substring( fragmentBegin, fragmentEnd);
            
            //TODO add comment         
            storyParameterBegin = fragmentEnd + 1;  
            
            //TODO add comment
            storyParameterEnd = madlibStory.indexOf( ']', storyParameterBegin);
            
            //TODO add comment
            if ( storyParameterEnd <= storyParameterBegin) {
                System.err.println("Error, unmatched [ at index " + storyParameterBegin);
                fragmentBegin = storyParameterBegin;
                break;
            }
            
            // parameter
            // This assigns a particular word to a parameter (i.e., noun, verb, past-tense ver, etc.)
            String parameter = madlibStory.substring( storyParameterBegin,  storyParameterEnd);
            
            // Asks the user to input the required parameter and takes it in as the next argument
            System.out.print( parameter + ": ");
            String argument = input.nextLine();

            // adds the input argument to the end of the completedStory String
            completedStory += argument;
            
            //TODO add comment
            fragmentBegin = storyParameterEnd + 1;
            fragmentEnd = madlibStory.indexOf( '[', fragmentBegin);
        }
        
        //TODO add comment
        completedStory += madlibStory.substring(fragmentBegin);
        return completedStory;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in);
        String story = "I almost showed up to school today with a [noun], "
                + "but [noun] stepped in and [past tense verb] the day.";
        String completedStory = promptForReplacements( input, story);
        System.out.println(completedStory);
        input.close();
    }
}