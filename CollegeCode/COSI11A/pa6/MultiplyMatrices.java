package pa6;

//This program takes in two 2-D arrays described by the user that represent matrices and creates a third 2-D
//array that represents the product of the first two arrays

import java.util.*;

public class MultiplyMatrices {
public static void main(String[]args) {
  System.out.println("Enter two matrices and I will multiply them together");
  Scanner console = new Scanner(System.in);
  double[][] xmatrix = firstMatrix(console);
  System.out.println("Here is your first matrix:");
  System.out.println(Arrays.deepToString(xmatrix)); 
  double[][] ymatrix = secondMatrix(xmatrix, console);
  System.out.println("Here is your second matrix:");
  System.out.println(Arrays.deepToString(ymatrix));
  combinedMatrix(xmatrix, ymatrix, console);
}

//creates the array for the first matrix based on the size given by the user and then fills it based on data given by the user
public static double[][] firstMatrix(Scanner console) {  
  System.out.println("How many columns do you want in the first matrix?");
  int xcn=console.nextInt();
  System.out.println("How many rows do you want in the first matrix?");
  int xrn=console.nextInt();
  double[][] xmatrix=new double[xrn][xcn];
  System.out.println("Which "+(xcn*xrn)+" numbers do you want your first matrix?");
  System.out.println("Hit enter after you type each number to register that number");
  System.out.println("The numbers will be inserted into the matrix going from left to right and go down a row after the row is completed");
  for (int r=0; r<xmatrix.length; r++) {
    for (int c=0; c<xmatrix[0].length; c++) {
      xmatrix[r][c]=console.nextDouble();
    }
  }    
  return xmatrix;
}

//essentially the same as the firstMatrix method except that there must be a certain number of rows for the matrices to be multiplied
public static double[][] secondMatrix(double[][] xmatrix, Scanner console) {
  System.out.println("How many columns do you want in the second matrix?");
  int ycn=console.nextInt();
  System.out.println("The number off rows in the second matrix must be the same as the number of columns in the first matrix");
  System.out.println("This number is "+xmatrix[0].length);
  int yrn=xmatrix[0].length;
  double[][] ymatrix=new double[yrn][ycn];
  System.out.println("Which "+(ycn*yrn)+" numbers do you want in your second matrix?");
  System.out.println("Hit enter after you type each number to register that number");
  for (int r=0; r<ymatrix.length; r++) {
    for (int c=0; c<ymatrix[0].length; c++) {
      ymatrix[r][c]=console.nextDouble();
    }  
  }
  return ymatrix;
}

//creates the third matrix    
public static void combinedMatrix(double[][] xmatrix, double[][] ymatrix, Scanner console) {
  int m = xmatrix.length;  //rows of third matrix
  int k = ymatrix[0].length;  //columns of third matrix
  double[][] zmatrix = new double[m][k];
  double[] elements = new double [ymatrix.length];  //stores the 3 terms (i.e. a02b20) that must be added together in order to create the elements for the third matrix
  int a=0;  //represents the row in the first matrix where the first half of a term is stored (i.e. a0_) 
  int b=0;  //represents the column in the first matrix where the first half of a term is stored (i.e. a_2)
  int c=0;  //represents the row in the second matrix...
  int d=0;  //represent the column in the second matrix...
  int e=0;  //represents the index in the elements matrix that the following loop is up to 
  int marker=0;  //allows the program to enter the following loop
    while (a!=0 || d!=0 || marker==0) {
      marker=1;
      while (e<ymatrix.length) {
        elements[e] = xmatrix[a][b]*ymatrix[c][d];
        b++;  //these incrementations allow the considered indices in the xmatrix, ymatrix, and elements arrays to all be updated simultaniously
        c++;
        e++;
      } 
      zmatrix[a][d] = sumDo(elements);  //creates the elements in the third matrix
      if (a<m-1 && d==0) {  //when these convoluted conditions are true...
        a++;  //the inner while loop is able to previously unseen sections of the first two matrices
      } else if (a==m-1 && d<k-1) {
        d++;  
      } else if (a!=0 && d==k-1) {
        a--;
      } else if (a==0 && d>0) {  //once the "d--" causes d to equal 0, the condition of the outer while loop becomes false and the whole loop is finished 
        d--;  
      }
      b=0;  //now that "a" and "d" have been modified, these values need to be reset
      c=0;
      e=0;  //the old values in the elements array are replaced in the next iteration of the inner while loop because those values have already been used to create elements for the third matrix
    }
  System.out.println("Here is the product of your two matrices:");
  System.out.println(Arrays.deepToString(zmatrix));  
}    
    


//this method adds the elements in any one-dimensional array of doubles
public static double sumDo(double[] numbers) {
  double sum=0;
  for(int i=0; i<numbers.length; i++) {
    double value=numbers[i];
    sum=sum+value;
  }
  return sum;
}

}      
    
    
