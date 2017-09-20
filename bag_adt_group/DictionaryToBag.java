import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ToubeauShawnD on 9/19/2017.
 */
public class DictionaryToBag {
    public static void main(String[] args){

        //variable for accessing dictionary file
        String dictionaryWordSource = "american-english-JL.txt";

        //reference one line at a time
        String lineReader = null;

        //creates the bag object for storing the dictionary strings
        ResizableArrayBag<String> dictionary = new ResizableArrayBag<String>();

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
    }
}
