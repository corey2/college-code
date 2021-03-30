package pa6;

//This program finds the min, max, sum, mean, median, mode, and standard
//deviation of 10 different integers inputed by the user


import java.util.*;


public class MeanMedianModeEtc {

//each number inputed by the user is taken and inserted into an array
public static void main(String[]args) { 
  Scanner console = new Scanner(System.in);
  System.out.println("Please enter 10 integers and press enter after typing each one:");
  int zero=console.nextInt();
  int one=console.nextInt();
  int two=console.nextInt();
  int three=console.nextInt();
  int four=console.nextInt();
  int five=console.nextInt();
  int six=console.nextInt();
  int seven=console.nextInt();
  int eight=console.nextInt();
  int nine=console.nextInt();
  double[] numbers={zero,one,two,three,four,five,six,seven,eight,nine}; //I use doubles in order to account for the average and the deviation and change the other values to int when I print them 
  bubbleSortOn(numbers);  //the array of input is then inserted into the bubblesort method
}  

public static void bubbleSortOn(double[] arr) {
  int didswap=1;  //ends bubblesort when the computer successfully checks that the numbers are in increasing order
  double tmp=0;
  while (didswap==1) {
    didswap=0;
    for (int i=1; i<arr.length; i++) {
      if (arr[i-1]>arr[i]) {
        tmp=arr[i-1];
        arr[i-1]=arr[i];
        arr[i]=tmp;
        didswap=1;
      }
    }
  }         
//this part of the method calls the other methods that do the calculations
minimum(arr);   
int max = maximum(arr);
System.out.println("max = "+(int)max);
double sum = sumDo(arr);
System.out.println("sum = "+(int)sum);  //I don't print sum everytime I use its method so I print it outside the method at this point in time
double average = mean(arr, sum);
median(arr);
mode(arr);
deviation(arr, average);
}

//checks each number in the array and saves it if it is smaller than the smallest one before it
public static void minimum(double[] numbers) {
  double min=numbers[0];
  for(int i=1; i<numbers.length; i++) {
    if (min>numbers[i]) {
      min=numbers[i];
    }
  }
  System.out.println("min = "+(int)min);
}   

//does the same thing as the min except that it saves the number when it is bigger 
public static int maximum(double[] numbers) {
  int max=(int)numbers[0];
  for(int i=1; i<numbers.length; i++) {
    if (max<numbers[i]) {
      max=(int)numbers[i];
    }
  }
  return max;
}    

//keeps track of all that has been summed so far with sum and goes through the array, adding each number to the sum
public static double sumDo(double[] numbers) {
  double sum=0;
  for(int i=0; i<numbers.length; i++) {
    double value=numbers[i];
    sum=sum+value;
  }
  return sum;
}
//divides sum by the number of numbers, which is always ten
public static double mean(double[] numbers, double sum) {
  double average=(double)sum/10;
  System.out.printf("mean = %.2f\n", average);  //print f allows me to round the final output
  return average;  //I will need this later to calculate the standard deviation
}  
  
//since the numbers are already in order due to bubblesort, I can arbitrarily print the bigger of the two numbers that are in the middle
public static void median(double[] numbers) {
  if (numbers[4]>=numbers[5]) {
    System.out.println("median = "+(int)numbers[4]);
  } else {
    System.out.println("median = "+(int)numbers[5]);
  }
}   

public static void mode(double[] numbers) {
  double[] tally=new double[10];  //first, i create a new array that keeps track of how many times each number has occured
  for (int i=0; i<numbers.length-1; i++) {  //for each number in the array...
    for (int j=i+1; j<numbers.length; j++) {  //I compare that number to the number after it...
      if (numbers[i]==numbers[j]) {  //and if they are the same...
        tally[i]++;  //I keep track of this in tally
      }
    }
  }
  int max = maximum(tally);  //I find the number in tally that has been tallied the most...
  System.out.println("mode = "+(int)numbers[max]);  //and since the indexes in tally match the indexes in number, I can easily print the index containing the correct number
}      

public static void deviation(double[] numbers, double average) {    
  for(int i=0; i<numbers.length; i++) {  //this loop goes through the array and finds the difference between each element and the average
    if (numbers[i]>average) {
      numbers[i] = numbers[i]-average;
    } else {
      numbers[i] = average-numbers[i];
    }
  }  
  for(int j=0; j<numbers.length; j++) {  //this loop doubles each number in the array
    numbers[j] = numbers[j]*numbers[j];
  }
  double sum=sumDo(numbers);  //I can add the numbers in the array together with this handy method
  double result=sum/(numbers.length-1);  //"then divide that sum by one less than the number of elements"...
  double dev=Math.sqrt(result);  //"and take the square-root of that result"
  System.out.printf("deviation = %.2f", dev);  
}


}      
    
