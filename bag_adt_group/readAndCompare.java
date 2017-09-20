import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class readAndCompare {
	public static void main(String[] args) {
		
		try {
			Scanner dictionary = new Scanner(new File("./Dictionary.txt"));
			Scanner readFile = new Scanner(new File("./File.txt"));
			ResizableArrayBag<String> dictionaryBag = new ResizableArrayBag<String>();
			String currentWord = "";
			
			// TODO: Put the dictionary into the bag ADT here
			
			// Checking if the read word is in the bag:
			char currentWordChar;
			boolean wasPreviousStringPunctuation = true;
			
			while(readFile.hasNext()) {
				currentWord = readFile.next();
				currentWordChar = currentWord.charAt(0);
				
				if(isPunctuation(currentWordChar)) {
					wasPreviousStringPunctuation = true;
				}

				// (If it's alphabetical), or  (if it's capitalized and following punctuation), then it needs to be checked. 
				else if(isAlphabetical(currentWord) || (wasPreviousStringPunctuation && isCapitalized(currentWordChar))) {
					if(!dictionaryBag.contains(currentWord)) {
					/* TODO: Handle the not having a word thing
					 * - Add it to the bag (if not already in there)
					 */ 
					}
					wasPreviousStringPunctuation = false;
				}
			}
			
			dictionary.close();
			readFile.close();
			
			/* TODO: Inform the user about the whole thing
			 * - Read from the array 
			 */
			
		} catch (FileNotFoundException e) {
			System.out.println("Sorry, couldn't find that file. Please check your path before trying again.");
		}
		
	}

	private static boolean isCapitalized(char currentWordChar) {
		return (currentWordChar >= 'A' && currentWordChar <= 'Z');
	}

	private static boolean isPunctuation(char currentWordChar) {
		return currentWordChar == '.' || currentWordChar == ';' || currentWordChar == '!' || currentWordChar == '?' || currentWordChar == ':';
	}
	
	/**
	 * Checks to see if a word is alphabetical.
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