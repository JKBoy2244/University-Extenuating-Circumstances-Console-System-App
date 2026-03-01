import LogIn;
import java.util.Scanner;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class StaffLogin {

  public static String staffID() {

    Scanner sc = new Scanner(System.in);
    int maxAttempts = 5;

    while (maxAttempts > 0) {

      System.out.println("Enter your staff ID already registered in the app");
      String staffID = sc.nextLine();

      if (StaffData.registeredStaff.containsKey(staffID)) {
        return staffID;
      }

      maxAttempts -= 1;
      if (maxAttempts == 0) {
        System.out.println("Sorry, too many attempts. Please try again later!");
        break;
      }

      System.out.println("Sorry, the staff ID doesn't exist. Try again!");
      System.out.println("You have " + maxAttempts + " attempts left");

    }
    return null;
  }

  public static void staffPassword(String staffID) {

    Scanner sc = new Scanner(System.in);
    int maxAttempts = 5;

    while (maxAttempts != 0) {

      System.out.println("Enter your password!");
      String staffPassword = sc.nextLine();

      String saved = StaffData.registeredStaff.get(staffID);
      if (saved != null && saved.equals(staffPassword)) {
        System.out.println("Login successful!");
        System.out.println("Welcome, staff member " + staffID + "!");
        StaffDashBoard.staffdashboard(); 
        break;

      } else {

        maxAttempts -= 1;
        if (maxAttempts == 0) {
          System.out.println("Sorry, too many attempts. Please try again later!");
          break;
        }

        System.out.println("Sorry, the password is incorrect. Try again!");
        System.out.println("You have " + maxAttempts + " attempts left");

      }
    }
  }
}
