import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

/**
 * 
 * @author Chris Thierauf: Handled the comparison stuff
 * @author Shawn Toubeau: Handled the input stuff
 * @author Roman Yacik: Handled the output stuff
 * 
 * COMP-2000
 * bag-adt-group
 * Due September 25th
 * 
 * Reads and compares the words in the dictionary file and the given file to act as a spell-checker.
 *
 */

public class readAndCompare {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Enter path to file: ");
		
		Scanner userIn = new Scanner(System.in);
		String userInput = userIn.nextLine();
		userIn.close();
		
		doReadAndCompare(userInput);
	}
	public static void doReadAndCompare(String readFileString) throws FileNotFoundException {
		String dictionaryWordSource = "american-english-JL.txt"; //variable for accessing dictionary file
		
		checkIfValidFile(dictionaryWordSource);
		checkIfValidFile(readFileString);

		Scanner readFile = new Scanner(new File(readFileString));

        String lineReader = null; //reference one line at a time

        //creates the bag object for storing the dictionary strings
        ResizableArrayBag<String> dictionary = new ResizableArrayBag<String>();
        ResizableArrayBag<String> misspelledBag = new ResizableArrayBag<String>();

        try
        {
            //creates file reader to process the dictionary source
            FileReader fileReader = new FileReader(dictionaryWordSource);

            //wraps the file reader in a buffer reader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //loop through all the lines in the dictionary file
            while ((lineReader = bufferedReader.readLine()) != null)
            {
                //can the current string can be added to bag?
                if (dictionary.add(lineReader))
                {

                    //if not; print error
                } else
                {
                    System.out.println("Unable to add to bag object");
                }   //end if
            }   //end while
        }   //end try

        //throw exception if dictionary source file is unable to be found
        catch (FileNotFoundException ex)
        {
            System.out.println("Unable to locate file '"+dictionaryWordSource+"'");
        }

        //throw exception if there is error reading dictionary source file
        catch (IOException ex)
        {
            System.out.println("Error reading file '"+dictionaryWordSource+"'");
        }
		
		
		// TODO: Put the dictionary into the bag ADT here
		
		// Checking if the read word is in the bag:
        String currentWord = "";
        String currentWordTrimmed = "";
        String previousWord = "";
		char currentWordChar;
		int nonAlphabeticalCount = 0;
		int capitalizedCount = 0;
		boolean wasPreviousStringPunctuated = true;
		
		while(readFile.hasNext()) {
			
			previousWord = currentWord;
			
			if(previousWord.length() > 1) {
				wasPreviousStringPunctuated = isPunctuation( previousWord.charAt(previousWord.length()-1) );
			}

			currentWord = readFile.next();
			currentWordTrimmed = removeTerminatingPunctuation(currentWord);
			currentWordChar = currentWord.charAt(0);

			/* 
			 * If it's alphabetical or if it's not (capitalized and not following punctuation), then it needs to be checked.
			 * This means that numbers and other characters will be ignored. 
			 */
			if(!isAlphabetical(currentWordTrimmed)) {
				++nonAlphabeticalCount;
			} else if (isCapitalized(currentWordChar) && !wasPreviousStringPunctuated) {
				++capitalizedCount;
			} else {
				if(!dictionary.contains(currentWordTrimmed.toLowerCase())) {
					if(!misspelledBag.contains(currentWordTrimmed)) {
						misspelledBag.add(currentWordTrimmed);
					}
				}
			}
		}
		
		System.out.println("The following words are misspelled in "+readFileString+" according to "+dictionaryWordSource+":");
		System.out.println(Arrays.toString(misspelledBag.toArray()));
		System.out.println("Ignored: "+nonAlphabeticalCount+" non-alphabetical words, and "+capitalizedCount+" capitalized words");

		
	}

	private static String removeTerminatingPunctuation(String currentWord) {
		if(isPunctuation(currentWord.charAt(currentWord.length()-1))) {
			return currentWord.substring(0, currentWord.length()-1); // Remove last character, if it's punctuation
		}
		return currentWord;
	}
	
	private static void checkIfValidFile(String fileRead) {
		File f = new File(fileRead);
		if(!f.isFile()) {
			System.out.println("File not found: "+fileRead+"\nCheck your path and try again.");
			System.exit(1);
		}
		
	}

	private static boolean isCapitalized(char currentWordChar) {
		return (currentWordChar >= 'A' && currentWordChar <= 'Z');
	}

	private static boolean isPunctuation(char currentWordChar) {
		return currentWordChar == '.' ||
				currentWordChar == ';' ||
				currentWordChar == '!' ||
				currentWordChar == '?' ||
				currentWordChar == ':' ||
				currentWordChar == ',';
	}
	
	/**
	 * Checks to see if a word is made of alphabetical elements.
	 * @param currentWord
	 * @return
	 */
	private static boolean isAlphabetical(String currentWord) {
		char[] currentWordArray = currentWord.toCharArray();
		for(char currentChar : currentWordArray) {
			// Cycling through all characters in the array
			if( !(currentChar >= 'a' && currentChar <= 'z' || currentChar >= 'A' && currentChar <= 'Z') ) {
				// If the character isn't from a to z or A to Z, return false
				return false;
			}
			// Otherwise, continue through the loop.
		}
		// The loop has been run all the way through- this must be all alphabetical characters. Return true.
		return true;
	}
}
