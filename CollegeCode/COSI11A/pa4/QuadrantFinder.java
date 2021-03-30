package pa4;

//This program takes a point, and tells the user 
//which quadrant or axis the point is in/on/at

import java.util.Scanner;

public class QuadrantFinder {
public static void main(String[]args) { //this method gathers the x and y coordinates from the user seperately and inserts them both into the calculations method
 Scanner console = new Scanner (System.in);
 System.out.println("Enter x-coordinate:");
 double xcoordinate = console.nextDouble();
 System.out.println("Enter y-coordinate:");
 double ycoordinate = console.nextDouble();
 calculations(xcoordinate,ycoordinate);
 } 
public static void calculations(double x, double y) {
  String point = "("+x+","+y+")"; // eliminates redundency by making it unneccessary to type this several times
  if (x>0&&y>0) {
      System.out.println(point+" is in Quadrant I."); //condition 1
  } else if (x<0&&y>0) {
      System.out.println(point+" is in Quadrant II."); //condition 2
  } else if (x<0&&y<0) {
      System.out.println(point+" is in Quadrant III."); //etc.
  } else if (x>0&&y<0) {
      System.out.println(point+" is in Quadrant IV.");
  } else if (x==0&&y==0) {
      System.out.println(point+" is at the origin.");
  } else if (x==0&&y!=0) {
      System.out.println(point+" is on the y-axis.");
  } else {
      System.out.println(point+" is on the x-axis."); 
  }
}  
}  
