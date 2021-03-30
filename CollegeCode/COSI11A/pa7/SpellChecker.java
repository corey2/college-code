package pa7;

import java.util.*;
import java.io.*;

public class SpellChecker {

	public static void main(String[] args) {
		
		//Create Scanner for interaction with the user
		Scanner console = new Scanner(System.in);
		
		//Call getFile, which returns a Scanner, twice.
		Scanner text = getFile("Please enter a file to spellcheck:", console);
		Scanner dict = getFile("Please enter a file to use as the dictionary:", console);
		
		//Make sure the Scanners initialized properly. Exit if not.
		if (text == null || dict == null) {
			System.out.println("Failed to load either text file or dictionary file");
			System.out.println("Exiting.");
		} else {
			//If everything is okay, check the spelling.
			spellcheck(text, dict);
		}
	}
	
	
	
	
	
	
	//Asks the user for a file until user types q or gives a valid file path.
	//Returns a Scanner that is initialized to read from the file.
	public static Scanner getFile(String prompt, Scanner console) {
		
		Scanner s = null;
		
		while(s == null) {
			System.out.println(prompt);
			String filename = "src/pa7/"+console.next();
			if (filename.equals("q")) { break; }
			try{
				s = new Scanner(new File(filename));
			} catch(FileNotFoundException e) {
				System.out.println("File not found. Try again or q to quit.");
			}
		}
		return s;
	}
	
	
	
	
	
	public static void spellcheck(Scanner text, Scanner dict) {
		//initialize an array with more elements than the 
		//143091 words in the dictionary
		String[] dictArr = new String[143091];
		
		//reads dictionary one line at a time and puts each 
		//line into the array, in order.
		System.out.println("loading dictionary...");

		//this loop takes a line from the dictionary and puts it
		//into the index represented by i, then concatenates i so
		//the next line is put into the next index.
		//the loop's condition ends the loop when there are no more additional lines
		int i = 0;
		while (dict.hasNextLine()) {
			dictArr[i] = dict.nextLine();
			i++;
		}
	  linearcheck(text, dictArr);
	  //binarycheck(text, dictArr);	
	
	}
	
	
	
	
	
	public static void linearcheck(Scanner text, String[] dictArr) {  
		System.out.println("checking spelling...");

		//start timer
		long tStart = System.currentTimeMillis();
		
		
		
		//Most of your code should go in this while loop and in additional methods. 
		boolean found;
		int e = 0;     //keeps track of which element in dictArr the loop is up to
		String word="";
		while (text.hasNext()) {   //ends the program when all the words in the text have been considered
	    word = text.next();
	    found = false;    
	    e = 0;
	    while (e<dictArr.length && found==false) {  //checks each word in dictArr.  
        if ((word.equalsIgnoreCase(dictArr[e]))) {
        found = true;  
        } else {    
            e++;
        }      
        if (e==dictArr.length) {  //if the word is not found in the last element, it means the word from the text is not in the dictionary so the word is printed
          System.out.println(word);
        }      
		  }
		}	
			
			//System.out.println(text.nextLine());
		
		//end timer
		long tEnd = System.currentTimeMillis() - tStart;
		System.out.println("Done!\n Execution time: " + tEnd / 1000.0 + " seconds");  
	  
	  //With this method, the program takes about 162.476 seconds to find each misspelling in Sherlock Holmes 
	  
	  //This method checks every word in the dictionary until it finds a match
	  //Theoretically, if not a single word in the text were spelled correctly, this method would check every possible comparison 
	}
	
	
	
	
	
	
	
	
	public static void binarycheck(Scanner text, String[] dictArr) {
		
	//start timer
	long tStart = System.currentTimeMillis();  
    
    
    //controls the word from the text that the method is checking
    while (text.hasNext()) {
      String target = text.next();
      boolean found = false;
      int min = 0;  
      int max = dictArr.length - 1;
      String temp = "";
      
      //the first condition is for when the loop finds the target and the words don't match, meaning the word is not in the dictionary and needs to be printed
      //the second condition is for when a match is found, meaning the word is in the dictionary and does not need to be printed  
      while (min <= max && found==false) {  
        int mid = (min+max)/2;
        if (target.endsWith("?")||target.endsWith(".")||target.endsWith("!")||target.endsWith(",")||target.endsWith(";")||target.endsWith(":")||target.startsWith("\"")||target.endsWith("\"")||target.endsWith(")")||target.endsWith("'")) {
            int q=0;
            while (q<target.length()-1) {
              temp=temp+target.charAt(q);
              q++;
            }
            target=temp;
        }
        if (target.equalsIgnoreCase(dictArr[mid])) {  
            found=true;
        } else {  
            char[] letters=compareLetters(target, dictArr[mid]);  
          if (letters[0]<letters[1]) {    
              max = mid - 1;      //if the original letter is before the letter in the dictionary, the max will now be less than the mid
          } else {  
              min = mid + 1;      //if the original letter is after the letter in the dictionary, the min will now be more than the mid 
          }
        }
      }
      if (found==false) {  //the above "found=true" statement is never reached if the word is never found
      System.out.println(target);
      }  
    } 
	
	
	//end timer
	  long tEnd = System.currentTimeMillis() - tStart;
		System.out.println("Done!\n Execution time: " + tEnd / 1000.0 + " seconds");

  //With this method, the program takes about 21.672 seconds to find and display each misspelling in Sherlock Holmes
  //Each word from the text should never be compared to much more than 30 words from the dictionary when using "words.txt"
  
  }

  public static char[] compareLetters(String tWord, String dWord ) {
    tWord=tWord.toLowerCase();
    dWord=dWord.toLowerCase();
    int i=0;
    char[] letters=new char[2];           //holds letters since to seperate chars cannot be returned in the same method  
      if (tWord.length()>dWord.length()) {  //avoids stringoutofbounds exception
          while (i<dWord.length()) {         //compares letters
            letters[0]=tWord.charAt(i);      
            letters[1]=dWord.charAt(i);
            if (letters[0]==letters[1]) {    //if the first letters are the same, all the letters after that are checked
                i++;
            } else {
                return letters;
            }
          }
      } else {
           while (i<tWord.length()) {        
            letters[0]=tWord.charAt(i);      
            letters[1]=dWord.charAt(i);
            //System.out.println("letters[0]="+letters[0]);
            //System.out.println("letters[1]="+letters[1]);
            if (letters[0]==letters[1]) {    
                i++;
            } else {
                return letters;
            }
          }
      }    
      //if one word has all the same letters as the other word but the other word is longer...
      if (tWord.length()>dWord.length()) {  //if the actual word is longer than the dictionary word, you know that the word in the dictionary is smaller
          letters[0]='b';  //so by making the actual word a greater letter than the dictionary word, 
          letters[1]='a';  //it causes the previous method to make "mid" the new "min"
          return letters;
    } else {
          letters[0]='a';  //vice versa
          letters[1]='b';
          return letters;
    }    
  }



}
