package pa7;

import java.util.*;
import java.io.*;


public class ShowTextFileDifferences {
  
  //takes names of input files as strings, searches for the files, and allows the files to be read by the Scanner
  //the input files must be located in the pa7 package
  public static void main(String[]args) throws FileNotFoundException {
    Scanner console = new Scanner(System.in);
    System.out.println("Enter first file name:");
    String file1 = console.next();
    System.out.println("Enter second file name:");
    String file2 = console.next();
    Scanner input1 = new Scanner(new File("src/pa7/"+file1));
    Scanner input2 = new Scanner(new File("src/pa7/"+file2));
    theFiles(input1, input2);
    console.close();
  }
    
 
  public static void theFiles(Scanner input1, Scanner input2) throws FileNotFoundException {
      int i=0;
      while (input1.hasNextLine() && input2.hasNextLine()) {  //This loop confirms that line numbers that are not in both files will not be read 
        String line1 = takeLine(input1);                      //and that both lines can be considered at the same time
        String line2 = takeLine(input2);
        i++;
        compareLines(line1, line2, i);
      }
      if (input1.hasNextLine()) {
        System.out.println("Possible Error:");
        System.out.println("The first file has additional line(s) that cannot be matched up with a line in file 2");
      }
      if (input2.hasNextLine()) {
        System.out.println("Possible Error:");
        System.out.println("The second file has additional line(s) that cannot be matched up with a line in file 1");
      }
  }
  
  
  //takes the next line from the Scanner and turns it into a String
  public static String takeLine(Scanner input) throws FileNotFoundException {
    String line = input.nextLine();
    return line;
  }
  
  
  //displays the number of the lines are being compared and prints both lines if the two lines are different
  public static void compareLines(String line1, String line2, int i) {
    if (!(line1.equals(line2))) {
      System.out.println("Difference in line "+i+" :");
      System.out.println(line1);
      System.out.println(line2);
    }  
  }     

}   
